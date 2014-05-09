package com.tmg.generator.service;

import java.util.List;

import com.tmg.gererator.domain.Attribute;
import com.tmg.gererator.domain.ForeignKey;
import com.tmg.gererator.domain.Relationship;
import com.tmg.gererator.domain.Table;

public interface DataBaseMetaData {

	public List<String> getCatalogs() throws DataAccessException;

	public List<String> getSchemas(String catalog, String schemaPattern)
			throws DataAccessException;;

	public List<Table> getTables(String catalog, String schemaPattern,
			String tableNamePattern, String[] types) throws DataAccessException;

	public List<Attribute> getColumns(String catalog, String schemaPattern,
			String tableNamePattern, String columnNamePattern)
			throws DataAccessException;

	public List<Relationship> getExportedKeys(String catalog, String schema,
			String table) throws DataAccessException;

	public List<ForeignKey> getImportedKeys(String catalog, String schema,
			String table) throws DataAccessException;

	public List<String> getPrimaryKeys(String catalog, String schema,
			String table) throws DataAccessException;

	public String convertSQLTypesToJava(String dataType);
}
