package com.tmg.data.generator;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class DataBaseMetaDataImpl implements DataBaseMetaData {

	private static Logger logger = Logger.getLogger(DataBaseMetaDataImpl.class);

	public Connection getConnection() throws DataAccessException {
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(
					"jdbc:sqlserver://192.168.100.46;database=UIFramework",
					"ebs", "ebs@123");
		} catch (SQLException e) {
			logger.error("Error creating connection" + e.getMessage(), e);
			throw new DataAccessException("Error creating connection"
					+ e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			logger.error("Driver Class Not Found " + e.getMessage(), e);
			throw new DataAccessException("Driver Class Not Found "
					+ e.getMessage(), e);
		}
		return conn;
	}

	public List<String> getCatalogs() throws DataAccessException {
		List<String> catalogs = new ArrayList<String>();
		try {
			DatabaseMetaData data = getConnection().getMetaData();
			ResultSet rs = data.getCatalogs();
			while (rs.next()) {
				catalogs.add(rs.getString("TABLE_CAT"));
			}
			rs.close();
		} catch (SQLException e) {
			logger.error("Error Retrieving Catalogs" + e.getMessage(), e);
			throw new DataAccessException("Error Retrieving Catalogs"
					+ e.getMessage(), e);
		}
		return catalogs;
	}

	public List<String> getSchemas(String catalog, String schemaPattern)
			throws DataAccessException {
		List<String> schemas = new ArrayList<String>();
		try {
			DatabaseMetaData data = getConnection().getMetaData();
			ResultSet rs = data.getSchemas(catalog, schemaPattern);
			while (rs.next()) {
				schemas.add(rs.getString("TABLE_SCHEM"));
			}
			rs.close();
		} catch (SQLException e) {
			logger.error("Error Retrieving Schemas" + e.getMessage(), e);
			throw new DataAccessException("Error Retrieving Schemas"
					+ e.getMessage(), e);
		}
		return schemas;
	}

	public List<Table> getTables(String catalog, String schemaPattern,
			String tableNamePattern, String[] types) throws DataAccessException {
		List<Table> tables = new ArrayList<Table>();
		try {
			DatabaseMetaData data = getConnection().getMetaData();
			ResultSet rs = data.getTables(catalog, schemaPattern,
					tableNamePattern, types);
			while (rs.next()) {
				Table table = new Table();
				table.setTableSchema(rs.getString("TABLE_SCHEM"));
				table.setTableName(rs.getString("TABLE_NAME"));
				table.setReferenceTables(getExportedKeys(catalog, rs.getString("TABLE_SCHEM"),
						rs.getString("TABLE_NAME")));
				table.setAttributes(getColumns(catalog, rs.getString("TABLE_SCHEM"),
						rs.getString("TABLE_NAME"), null));
				tables.add(table);
			}
			rs.close();
		} catch (SQLException e) {
			logger.error("Error Retrieving Tables" + e.getMessage(), e);
			throw new DataAccessException("Error Retrieving Tables"
					+ e.getMessage(), e);
		}
		return tables;
	}

	public List<Attribute> getColumns(String catalog, String schemaPattern,
			String tableNamePattern, String columnNamePattern)
			throws DataAccessException {
		List<Attribute> attributes = new ArrayList<Attribute>();
		try {
			DatabaseMetaData data = getConnection().getMetaData();
			ResultSet rs = data.getColumns(catalog, schemaPattern,
					tableNamePattern, columnNamePattern);
			List<ForeignKey> foreignKeys = getImportedKeys(catalog,
					schemaPattern, tableNamePattern);
			List<String> primaryKeys = getPrimaryKeys(catalog, schemaPattern,
					tableNamePattern);

			while (rs.next()) {
				Attribute attribute = new Attribute();
				attribute.setName(rs.getString("COLUMN_NAME"));
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
								.getColumnName()
								.trim()
								.equalsIgnoreCase(
										rs.getString("COLUMN_NAME").trim())) {
							attribute.setForeignKey(true);
							attribute.setReferenceTableName(foreignKey
									.getTableName());
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
		}
		return attributes;
	}

	public List<String> getExportedKeys(String catalog, String schema,
			String table) throws DataAccessException {
		List<String> keys = new ArrayList<String>();
		try {
			DatabaseMetaData data = getConnection().getMetaData();
			ResultSet rs = data.getExportedKeys(catalog, schema, table);
			while (rs.next()) {
				keys.add(rs.getString("FKTABLE_NAME"));
			}
			rs.close();
		} catch (SQLException e) {
			logger.error("Error Retrieving Foreign Key Table" + e.getMessage(),
					e);
			throw new DataAccessException("Error Retrieving  Foreign Key Table"
					+ e.getMessage(), e);
		}
		return keys;
	}

	public List<ForeignKey> getImportedKeys(String catalog, String schema,
			String table) throws DataAccessException {
		List<ForeignKey> keys = new ArrayList<ForeignKey>();
		try {

			DatabaseMetaData data = getConnection().getMetaData();
			ResultSet rs = data.getImportedKeys(catalog, schema, table);
			while (rs.next()) {
				ForeignKey key = new ForeignKey();
				key.setTableName(rs.getString("PKTABLE_NAME"));
				key.setColumnName(rs.getString("FKCOLUMN_NAME"));
				keys.add(key);
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(
					"Error Retrieving Foreign Key Columns" + e.getMessage(), e);
			throw new DataAccessException(
					"Error Retrieving Foreign Key Columns" + e.getMessage(), e);
		}

		return keys;
	}

	public List<String> getPrimaryKeys(String catalog, String schema,
			String table) throws DataAccessException {
		List<String> keys = new ArrayList<String>();
		try {
			DatabaseMetaData data = getConnection().getMetaData();
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

		if (dataType.equalsIgnoreCase("bit")) {
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
				|| dataType.equalsIgnoreCase("xml")) {
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
				|| dataType.equalsIgnoreCase("int identity")) {
			dataType = "int";
		}
		return dataType;
	}
}
