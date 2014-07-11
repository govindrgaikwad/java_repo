package com.empresa.marco.services;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empresa.marco.data.AttributeData;
import com.empresa.marco.data.EmbaddableData;
import com.empresa.marco.data.ForeignKey;
import com.empresa.marco.data.ObjectDefinationData;
import com.empresa.marco.data.RelationshipData;
import com.empresa.marco.exceptions.DataAccessException;
import com.empresa.marco.repository.GenericRepository;

@Service("CollectorService")
public class CollectorServiceImpl implements CollectorService {

	private static Logger logger = Logger.getLogger(CollectorServiceImpl.class);

	@Autowired
	GenericRepository repository;

	@Autowired
	DataSource dataSource;

	@Autowired
	ObjectDefinationService objectDefinationService;

	@Autowired
	EmbaddableService embaddableService;

	@Autowired
	DataTypeService dataTypeService;

	@Autowired
	AttributeService attributeService;

	@Autowired
	RelationshipService relationshipService;

	@Override
	@Transactional
	public Boolean collectObjectDefination(String catalog,
			String schemaPattern, String tableNamePattern, String[] types)
			throws DataAccessException {
		Connection connection = null;
		Boolean result = true;
		try {
			connection = dataSource.getConnection();
			DatabaseMetaData data = connection.getMetaData();
			ResultSet rs = data.getTables(catalog, schemaPattern,
					tableNamePattern, types);
			while (rs.next()) {
				ObjectDefinationData defination = new ObjectDefinationData();
				defination.setSchemaName(rs.getString("TABLE_SCHEM"));
				defination.setDataBaseName(rs.getString("TABLE_CAT"));
				String tableName = rs.getString("TABLE_NAME").trim();
				defination.setName(tableName);
				tableName = tableName.replaceAll("\\s", "");
				tableName = tableName.substring(0, 1).toUpperCase().trim()
						+ tableName.substring(1);
				defination.setUserDefinedName(tableName);
				tableName = tableName.substring(0, 1).toLowerCase().trim()
						+ tableName.substring(1);
				defination.setCamelCaseName(tableName);
				List<String> primaryKeys = collectPrimaryKeys(
						rs.getString("TABLE_CAT"), rs.getString("TABLE_SCHEM"),
						rs.getString("TABLE_NAME"));
				if (primaryKeys.size() > 1) {
					defination.setEmbaddable(true);
				} else {
					defination.setEmbaddable(false);
				}
				if (primaryKeys.size() > 0) {
					defination.setPrimaryKey(true);
				} else {
					defination.setPrimaryKey(false);
				}
				defination.setUpdated(false);
				objectDefinationService.saveObjectDefination(defination);
			}
			rs.close();
		} catch (PersistenceException | SQLException e) {
			result = false;
			logger.error(
					"Error Collecting Object Definations" + e.getMessage(), e);
			throw new DataAccessException("Error Collecting Object Definations"
					+ e.getMessage(), e);
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				throw new DataAccessException("Error closing connection"
						+ e.getMessage(), e);
			}
		}

		return result;
	}

	@Override
	@Transactional
	public Boolean collectEmbaddable() throws DataAccessException {
		Boolean result = true;
		List<ObjectDefinationData> definationDatas = null;
		try {
			definationDatas = objectDefinationService
					.getObjectDefinationByEmbaddable(true, 1, 1);
			if (definationDatas != null && definationDatas.size() > 0) {
				for (ObjectDefinationData objectDefinationData : definationDatas) {
					EmbaddableData embaddable = new EmbaddableData();
					embaddable.setEmbaddableId(objectDefinationData
							.getObjectId());
					embaddable.setName(objectDefinationData
							.getUserDefinedName() + "Embaddable");
					embaddable.setCamelCaseName(objectDefinationData
							.getCamelCaseName() + "Embaddable");
					embaddableService.saveEmbaddable(embaddable);
				}
			}

		} catch (PersistenceException e) {
			result = false;
			logger.error("Error Collecting Embaddable" + e.getMessage(), e);
			throw new DataAccessException("Error Collecting Embaddable"
					+ e.getMessage(), e);
		}
		return result;
	}

	@Override
	@Transactional
	public Boolean collectAttributes() throws DataAccessException {

		Boolean result = true;
		Connection connection = null;
		List<ObjectDefinationData> datas = null;
		try {
			datas = objectDefinationService.getAllObjectDefination(1, 1);
			connection = dataSource.getConnection();
			DatabaseMetaData data = connection.getMetaData();
			if (datas != null && datas.size() > 0) {
				for (ObjectDefinationData objectDefinationData : datas) {
					ResultSet rs = data.getColumns(
							objectDefinationData.getDataBaseName(),
							objectDefinationData.getSchemaName(),
							objectDefinationData.getName(), null);
					while (rs.next()) {
						AttributeData attribute = new AttributeData();
						attribute.setObjectId(objectDefinationData
								.getObjectId());
						String columnName = rs.getString("COLUMN_NAME").trim();
						attribute.setName(columnName);
						columnName = columnName.replaceAll("[^a-zA-Z 0-9_]+",
								"").replaceAll("\\s+", "");
						attribute.setUserDefinedName(columnName);
						columnName = columnName.substring(0, 1).toLowerCase()
								.trim()
								+ columnName.substring(1);
						attribute.setCamelCaseName(columnName);
						int sqlValue = rs.getInt("DATA_TYPE");
						attribute.setDatatype(dataTypeService
								.getDataTypeByConstantValue(sqlValue)
								.getSQLType());
						attribute.setJavaDataType(dataTypeService
								.getDataTypeByConstantValue(sqlValue)
								.getJavaType());
						attribute.setPrimaryKey(false);
						attribute.setEmbaddable(false);
						attribute.setForeignKey(false);
						List<String> primarykeys = collectPrimaryKeys(
								objectDefinationData.getDataBaseName(),
								objectDefinationData.getSchemaName(),
								objectDefinationData.getName());
						for (String primarykey : primarykeys) {
							if (primarykey
									.equalsIgnoreCase(attribute.getName())) {
								attribute.setPrimaryKey(true);
								if (objectDefinationData.getEmbaddable()) {
									attribute.setEmbaddable(true);
								}
							}

						}
						List<ForeignKey> foreignKeys = collectForeignKey(
								objectDefinationData.getDataBaseName(),
								objectDefinationData.getSchemaName(),
								objectDefinationData.getName());

						if (foreignKeys != null && foreignKeys.size() > 1) {
							for (int i = 0; i < foreignKeys.size() - 1; i++) {
								int k = 1;
								ForeignKey key1 = foreignKeys.get(i);
								for (int j = i + 1; j < foreignKeys.size(); j++) {
									ForeignKey key2 = foreignKeys.get(j);
									if (key1.getReferenceName()
											.equalsIgnoreCase(
													key2.getReferenceName())) {
										key2.setReferenceName(key2
												.getReferenceName() + k);

										k = k + 1;
									}
								}
							}
						}
						for (ForeignKey foreignKey : foreignKeys) {
							if (foreignKey.getChildAttribute()
									.equalsIgnoreCase(attribute.getName())) {
								ObjectDefinationData definationData = objectDefinationService
										.getObjectDefinationByName(
												foreignKey.getParentObject(),
												1, 1);
								attribute.setForeignKey(true);
								attribute.setReferenceObjectId(definationData
										.getObjectId());
								attribute.setReferenceName(definationData
										.getUserDefinedName());
								/*
								 * String referenceName = attribute.getName()
								 * .replaceAll("[^a-zA-Z 0-9_]+", "")
								 * .replaceAll("\\s+", "").replace("ID", "")
								 * .replace("Id", "").replace("id", "") +
								 * definationData.getUserDefinedName();
								 * referenceName = referenceName.substring(0, 1)
								 * .toLowerCase().trim() +
								 * referenceName.substring(1);
								 */
								attribute.setReferenceName(foreignKey
										.getReferenceName());
							}
						}
						attributeService.saveAttribute(attribute);

					}
					rs.close();
				}
			}

		} catch (PersistenceException | SQLException e) {
			result = false;
			logger.error("Error Collecting Attributes" + e.getMessage(), e);
			throw new DataAccessException("Error Collecting Attributes"
					+ e.getMessage(), e);
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				throw new DataAccessException("Error closing connection"
						+ e.getMessage(), e);
			}
		}
		return result;
	}

	@Override
	@Transactional
	public Boolean collectRelationship() throws DataAccessException {
		Boolean result = true;
		try {
			List<ObjectDefinationData> definationDatas = objectDefinationService
					.getAllObjectDefination(1, 1);
			for (ObjectDefinationData definationData : definationDatas) {
				List<ForeignKey> foreignKeys = collectForeignKey(
						definationData.getDataBaseName(),
						definationData.getSchemaName(),
						definationData.getName());
				for (ForeignKey foreignKey : foreignKeys) {

					RelationshipData relationshipData = new RelationshipData();
					ObjectDefinationData parent = objectDefinationService
							.getObjectDefinationByName(
									foreignKey.getParentObject(), 1, 1);
					ObjectDefinationData child = objectDefinationService
							.getObjectDefinationByName(
									foreignKey.getChildObject(), 1, 1);
					relationshipData.setParentObjectId(parent.getObjectId());
					relationshipData.setChildObjectId(child.getObjectId());
					relationshipData.setParentAttributeId(attributeService
							.getAttributeByNameAndObjectId(
									foreignKey.getParentAttribute(),
									parent.getObjectId(), 1, 1)
							.getAttributeId());
					relationshipData
							.setChildAttributeId(attributeService
									.getAttributeByNameAndObjectId(
											foreignKey.getChildAttribute(),
											child.getObjectId(), 1, 1)
									.getAttributeId());
					String relationName = foreignKey.getChildObject()
							.replaceAll("[^a-zA-Z 0-9_]+", "")
							.replaceAll("\\s+", "")
							+ foreignKey.getParentObject()
									.replaceAll("[^a-zA-Z 0-9_]+", "")
									.replaceAll("\\s+", "") + "s";
					relationshipData.setRelationName(relationName);
					relationName = relationName.substring(0, 1).toLowerCase()
							.trim()
							+ relationName.substring(1);
					relationshipData.setCamelcaseName(relationName);
					AttributeData attributeData = attributeService
							.getAttributeByNameAndObjectId(
									foreignKey.getChildAttribute(),
									child.getObjectId(), 1, 1);
					if (attributeData.getPrimaryKey())
						relationshipData.setCardinality("O");
					else
						relationshipData.setCardinality("M");
					relationshipService.saveRelationship(relationshipData);
				}
			}

		} catch (PersistenceException e) {
			result = false;
			logger.error("Error collecting Relationship" + e.getMessage(), e);
			throw new DataAccessException("Error collecting Relationship"
					+ e.getMessage(), e);
		}
		return result;
	}

	@Override
	@Transactional
	public Boolean collectMethods() throws DataAccessException {

		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> collectPrimaryKeys(String catalog, String schema,
			String table) throws DataAccessException {

		List<String> keys = new ArrayList<String>();
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
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
				throw new DataAccessException("Error closing connection"
						+ e.getMessage(), e);
			}
		}

		return keys;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ForeignKey> collectForeignKey(String catalog, String schema,
			String table) throws DataAccessException {

		List<ForeignKey> keys = new ArrayList<ForeignKey>();
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			DatabaseMetaData data = connection.getMetaData();
			ResultSet rs = data.getImportedKeys(catalog, schema, table);
			while (rs.next()) {
				ForeignKey key = new ForeignKey();
				key.setParentObject(rs.getString("PKTABLE_NAME"));
				key.setParentAttribute(rs.getString("PKCOLUMN_NAME"));
				key.setChildObject(rs.getString("FKTABLE_NAME"));
				key.setChildAttribute(rs.getString("FKCOLUMN_NAME"));
				String name = rs.getString("PKTABLE_NAME").trim();
				name = name.replaceAll("\\s", "");
				name = name.substring(0, 1).toLowerCase().trim()
						+ name.substring(1);
				key.setReferenceName(name);
				keys.add(key);
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(
					"Error collecting Foreign Key Columns" + e.getMessage(), e);
			throw new DataAccessException(
					"Error collecting Foreign Key Columns" + e.getMessage(), e);
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				throw new DataAccessException("Error closing connection"
						+ e.getMessage(), e);
			}
		}
		return keys;

	}
}
