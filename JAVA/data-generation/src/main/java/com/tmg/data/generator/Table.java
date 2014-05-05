package com.tmg.data.generator;

import java.util.List;

public class Table {

	private String tableName;

	private String tableSchema;

	private List<String> referenceTables;

	private List<Attribute> attributes;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableSchema() {
		return tableSchema;
	}

	public void setTableSchema(String tableSchema) {
		this.tableSchema = tableSchema;
	}

	public List<String> getReferenceTables() {
		return referenceTables;
	}

	public void setReferenceTables(List<String> referenceTables) {
		this.referenceTables = referenceTables;
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

}
