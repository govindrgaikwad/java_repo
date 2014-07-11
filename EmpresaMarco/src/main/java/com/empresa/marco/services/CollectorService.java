package com.empresa.marco.services;

import java.util.List;

import com.empresa.marco.data.ForeignKey;
import com.empresa.marco.exceptions.DataAccessException;

public interface CollectorService {

	public Boolean collectObjectDefination(String catalog,
			String schemaPattern, String tableNamePattern, String[] types)
			throws DataAccessException;

	public Boolean collectEmbaddable() throws DataAccessException;

	public Boolean collectAttributes() throws DataAccessException;

	public Boolean collectRelationship() throws DataAccessException;

	public Boolean collectMethods() throws DataAccessException;

	public List<String> collectPrimaryKeys(String catalog, String schema,
			String table) throws DataAccessException;

	public List<ForeignKey> collectForeignKey(String catalog, String schema,
			String table) throws DataAccessException;
}
