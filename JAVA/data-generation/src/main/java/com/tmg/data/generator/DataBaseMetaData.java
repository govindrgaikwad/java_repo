package com.tmg.data.generator;

import java.sql.Connection;

import java.util.List;

public interface DataBaseMetaData {

	public Connection getConnection() throws DataAccessException;

	public List<String> getCatalogs() throws DataAccessException;

	public List<String> getSchemas(String catalog, String schemaPattern)
			throws DataAccessException;;

	public List<Table> getTables(String catalog, String schemaPattern,
			String tableNamePattern, String[] types) throws DataAccessException;

	public List<Attribute> getColumns(String catalog, String schemaPattern,
			String tableNamePattern, String columnNamePattern)
			throws DataAccessException;

	public List<String> getExportedKeys(String catalog, String schema,
			String table) throws DataAccessException;

	public List<ForeignKey> getImportedKeys(String catalog, String schema,
			String table) throws DataAccessException;

	public List<String> getPrimaryKeys(String catalog, String schema,
			String table) throws DataAccessException;
	
	public String convertSQLTypesToJava(String dataType);
}
