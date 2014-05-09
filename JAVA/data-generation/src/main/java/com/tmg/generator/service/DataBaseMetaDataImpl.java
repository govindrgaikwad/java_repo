package com.tmg.generator.service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.tmg.gererator.domain.Attribute;
import com.tmg.gererator.domain.ForeignKey;
import com.tmg.gererator.domain.Relationship;
import com.tmg.gererator.domain.Table;

@Service("DataBaseMetaData")
public class DataBaseMetaDataImpl implements DataBaseMetaData {

	private static Logger logger = Logger.getLogger(DataBaseMetaDataImpl.class);

	public List<String> getCatalogs() throws DataAccessException {
		List<String> catalogs = new ArrayList<String>();
		Connection connection = null;
		try {
			connection = ExternalConnection.getConnection();
			DatabaseMetaData data = connection.getMetaData();
			ResultSet rs = data.getCatalogs();
			while (rs.next()) {
				catalogs.add(rs.getString("TABLE_CAT"));
			}
			rs.close();
		} catch (SQLException e) {
			logger.error("Error Retrieving Catalogs" + e.getMessage(), e);
			throw new DataAccessException("Error Retrieving Catalogs"
					+ e.getMessage(), e);
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				throw new DataAccessException("Error closing Connection"
						+ e.getMessage(), e);
			}
		}
		return catalogs;
	}

	public List<String> getSchemas(String catalog, String schemaPattern)
			throws DataAccessException {
		List<String> schemas = new ArrayList<String>();
		Connection connection = null;
		try {
			connection = ExternalConnection.getConnection();
			DatabaseMetaData data = connection.getMetaData();
			ResultSet rs = data.getSchemas(catalog, schemaPattern);
			while (rs.next()) {
				schemas.add(rs.getString("TABLE_SCHEM"));
			}
			rs.close();
		} catch (SQLException e) {
			logger.error("Error Retrieving Schemas" + e.getMessage(), e);
			throw new DataAccessException("Error Retrieving Schemas"
					+ e.getMessage(), e);
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				throw new DataAccessException("Error closing Connection"
						+ e.getMessage(), e);
			}
		}
		return schemas;
	}

	public List<Table> getTables(String catalog, String schemaPattern,
			String tableNamePattern, String[] types) throws DataAccessException {
		List<Table> tables = new ArrayList<Table>();
		Connection connection = null;
		try {
			connection = ExternalConnection.getConnection();
			DatabaseMetaData data = connection.getMetaData();
			ResultSet rs = data.getTables(catalog, schemaPattern,
					tableNamePattern, types);
			while (rs.next()) {
				Table table = new Table();
				table.setTableSchema(rs.getString("TABLE_SCHEM").trim());
				String tableName = rs.getString("TABLE_NAME").trim();
				table.setTableName(tableName);
				tableName = tableName.replaceAll("\\s", "");
				table.setName(tableName);
				tableName = tableName.substring(0, 1).toLowerCase().trim()
						+ tableName.substring(1);
				table.setCamelCaseName(tableName);
				List<Relationship> relationships = getExportedKeys(catalog,
						rs.getString("TABLE_SCHEM"), rs.getString("TABLE_NAME"));
				if (relationships != null && relationships.size() > 1) {
					for (int i = 0; i < relationships.size() - 1; i++) {
						for (int j = i + 1; j < relationships.size() - 1; j++) {
							int k = 1;
							Relationship relationship1 = relationships.get(i);

							Relationship relationship2 = relationships.get(j);

							if (relationship1.getCamelCaseName()
									.equalsIgnoreCase(
											relationship2.getCamelCaseName())) {
								System.out.println(relationship1
										.getCamelCaseName());
								System.out.println();
								System.out.println(relationship2
										.getCamelCaseName());
								relationship2.setCamelCaseName(relationship2
										.getCamelCaseName() + i);
								System.out.println();
								System.out.println(relationship2
										.getCamelCaseName() + k);
								k = k + 1;
							}
						}
					}
				}
				table.setRelationships(relationships);
				table.setAttributes(getColumns(catalog,
						rs.getString("TABLE_SCHEM"),
						rs.getString("TABLE_NAME"), null));
				tables.add(table);
				int i = 0;
				for (Attribute attribute : table.getAttributes()) {

					if (attribute.isPrimaryKey()) {
						i++;
					}
				}
				if (i > 2) {
					table.setEmbeddable(true);
				}
			}

			rs.close();
		} catch (SQLException e) {
			logger.error("Error Retrieving Tables" + e.getMessage(), e);
			throw new DataAccessException("Error Retrieving Tables"
					+ e.getMessage(), e);
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				throw new DataAccessException("Error closing Connection"
						+ e.getMessage(), e);
			}
		}
		return tables;
	}

	public List<Attribute> getColumns(String catalog, String schemaPattern,
			String tableNamePattern, String columnNamePattern)
			throws DataAccessException {
		List<Attribute> attributes = new ArrayList<Attribute>();
		Connection connection = null;
		try {
			connection = ExternalConnection.getConnection();
			DatabaseMetaData data = connection.getMetaData();
			ResultSet rs = data.getColumns(catalog, schemaPattern,
					tableNamePattern, columnNamePattern);
			List<ForeignKey> foreignKeys = getImportedKeys(catalog,
					schemaPattern, tableNamePattern);

			if (foreignKeys != null && foreignKeys.size() > 1) {
				for (int i = 0; i < foreignKeys.size() - 1; i++) {
					int k = 1;
					ForeignKey key1 = foreignKeys.get(i);
					for (int j = i + 1; j < foreignKeys.size() - 1; j++) {						

						ForeignKey key2 = foreignKeys.get(j);
						if (tableNamePattern
								.equalsIgnoreCase("MasterListLimitVal"))
							System.out.println(tableNamePattern);
						if (key1.getCamelCaseName().equalsIgnoreCase(
								key2.getCamelCaseName())) {
							System.out.println(key1.getCamelCaseName());
							System.out.println();
							System.out.println(key2.getCamelCaseName());
							key2.setCamelCaseName(key2.getCamelCaseName() + k);
							System.out.println();
							System.out.println(key2.getCamelCaseName());
							k = k + 1;
						}
					}
				}
			}
			List<String> primaryKeys = getPrimaryKeys(catalog, schemaPattern,
					tableNamePattern);

			while (rs.next()) {
				Attribute attribute = new Attribute();
				String columnName = rs.getString("COLUMN_NAME").trim();
				attribute.setColumnName(columnName);
				columnName = columnName.replaceAll("\\s", "");
				attribute.setName(columnName);
				columnName = columnName.substring(0, 1).toLowerCase().trim()
						+ columnName.substring(1);
				attribute.setCamelCaseName(columnName);
				attribute.setDataType(convertSQLTypesToJava(rs
						.getString("TYPE_NAME")));

				if (primaryKeys != null && primaryKeys.size() > 0) {
					for (String primaryKey : primaryKeys) {
						if (primaryKey.trim().equalsIgnoreCase(
								rs.getString("COLUMN_NAME"))) {
							attribute.setPrimaryKey(true);
						}
					}
				}

				if (foreignKeys != null && foreignKeys.size() > 0) {
					for (ForeignKey foreignKey : foreignKeys) {
						if (foreignKey
								.getCompareColumn()
								.trim()
								.equalsIgnoreCase(
										rs.getString("COLUMN_NAME").trim())) {
							attribute.setForeignKey(true);
							attribute.setReferenceTableName(foreignKey
									.getTableName());
							attribute.setCamelCaseTableName(foreignKey
									.getCamelCaseName());
						}
					}
				}
				attributes.add(attribute);
			}
			rs.close();
		} catch (SQLException e) {
			logger.error("Error Retrieving Columns" + e.getMessage(), e);
			throw new DataAccessException("Error Retrieving Columns"
					+ e.getMessage(), e);
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				throw new DataAccessException("Error closing Connection"
						+ e.getMessage(), e);
			}
		}
		return attributes;
	}

	public List<Relationship> getExportedKeys(String catalog, String schema,
			String table) throws DataAccessException {
		List<Relationship> relationships = new ArrayList<Relationship>();
		Connection connection = null;
		try {
			connection = ExternalConnection.getConnection();
			DatabaseMetaData data = connection.getMetaData();
			ResultSet rs = data.getExportedKeys(catalog, schema, table);
			while (rs.next()) {
				Relationship relationship = new Relationship();
				relationship.setType(rs.getString("FKTABLE_NAME"));
				String name = rs.getString("FKTABLE_NAME").trim();
				name = name.replaceAll("\\s", "");
				relationship.setName(name + "s");
				name = name.substring(0, 1).toLowerCase().trim()
						+ name.substring(1);
				relationship.setCamelCaseName(name + "s");
				relationships.add(relationship);
			}
			rs.close();
		} catch (SQLException e) {
			logger.error("Error Retrieving Foreign Key Table" + e.getMessage(),
					e);
			throw new DataAccessException("Error Retrieving  Foreign Key Table"
					+ e.getMessage(), e);
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				throw new DataAccessException("Error closing Connection"
						+ e.getMessage(), e);
			}
		}
		return relationships;
	}

	public List<ForeignKey> getImportedKeys(String catalog, String schema,
			String table) throws DataAccessException {
		List<ForeignKey> keys = new ArrayList<ForeignKey>();
		Connection connection = null;
		try {
			connection = ExternalConnection.getConnection();
			DatabaseMetaData data = connection.getMetaData();
			ResultSet rs = data.getImportedKeys(catalog, schema, table);
			while (rs.next()) {
				ForeignKey key = new ForeignKey();
				String tableName = rs.getString("PKTABLE_NAME").trim();
				tableName = tableName.replaceAll("\\s", "");
				key.setTableName(tableName);
				key.setCompareColumn(rs.getString("FKCOLUMN_NAME"));
				String name = rs.getString("PKTABLE_NAME").trim();
				name = name.replaceAll("\\s", "");
				name = name.substring(0, 1).toLowerCase().trim()
						+ name.substring(1);
				key.setCamelCaseName(name);

				keys.add(key);
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(
					"Error Retrieving Foreign Key Columns" + e.getMessage(), e);
			throw new DataAccessException(
					"Error Retrieving Foreign Key Columns" + e.getMessage(), e);
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				throw new DataAccessException("Error closing Connection"
						+ e.getMessage(), e);
			}
		}
		return keys;
	}

	public List<String> getPrimaryKeys(String catalog, String schema,
			String table) throws DataAccessException {
		List<String> keys = new ArrayList<String>();
		Connection connection = null;
		try {
			connection = ExternalConnection.getConnection();
			DatabaseMetaData data = connection.getMetaData();
			ResultSet rs = data.getPrimaryKeys(catalog, schema, table);
			while (rs.next()) {
				keys.add(rs.getString("COLUMN_NAME"));
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(
					"Error Retrieving Primary Key columns" + e.getMessage(), e);
			throw new DataAccessException(
					"Error Retrieving  Primary Key columns" + e.getMessage(), e);
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				throw new DataAccessException("Error closing Connection"
						+ e.getMessage(), e);
			}
		}

		return keys;

	}

	public String convertSQLTypesToJava(String dataType) {
		if (dataType.equalsIgnoreCase("bigint")) {
			dataType = "long";
		}

		if (dataType.equalsIgnoreCase("binary")
				|| dataType.equalsIgnoreCase("timestamp")
				|| dataType.equalsIgnoreCase("udt")
				|| dataType.equalsIgnoreCase("varbinary")
				|| dataType.equalsIgnoreCase("image")) {
			dataType = "byte[]";
		}

		if (dataType.equalsIgnoreCase("bit")
				|| dataType.equalsIgnoreCase("Flag")) {
			dataType = "boolean";
		}

		if (dataType.equalsIgnoreCase("char")
				|| dataType.equalsIgnoreCase("nchar")
				|| dataType.equalsIgnoreCase("ntext")
				|| dataType.equalsIgnoreCase("nvarchar")
				|| dataType.equalsIgnoreCase("bit")
				|| dataType.equalsIgnoreCase("text")
				|| dataType.equalsIgnoreCase("uniqueidentifier")
				|| dataType.equalsIgnoreCase("varchar")
				|| dataType.equalsIgnoreCase("xml")
				|| dataType.equalsIgnoreCase("sysname")
				|| dataType.equalsIgnoreCase("Name")
				|| dataType.equalsIgnoreCase("Email")) {
			dataType = "String";
		}

		if (dataType.equalsIgnoreCase("date")
				|| dataType.equalsIgnoreCase("datetime")
				|| dataType.equalsIgnoreCase("datetimeoffset (2)")
				|| dataType.equalsIgnoreCase("datetime2")
				|| dataType.equalsIgnoreCase("smalldatetime")
				|| dataType.equalsIgnoreCase("time")) {
			dataType = "Date";
		}
		if (dataType.equalsIgnoreCase("decimal")
				|| dataType.equalsIgnoreCase("float")
				|| dataType.equalsIgnoreCase("money")
				|| dataType.equalsIgnoreCase("numeric")
				|| dataType.equalsIgnoreCase("real")
				|| dataType.equalsIgnoreCase("smallmoney")) {
			dataType = "double";
		}
		if (dataType.equalsIgnoreCase("int")
				|| dataType.equalsIgnoreCase("smallint")
				|| dataType.equalsIgnoreCase("tinyint")
				|| dataType.equalsIgnoreCase("int identity")
				|| dataType.equalsIgnoreCase("phone")) {
			dataType = "int";
		}
		return dataType;
	}
}
