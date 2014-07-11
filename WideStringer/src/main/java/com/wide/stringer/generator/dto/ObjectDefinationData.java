package com.wide.stringer.generator.dto;

public class ObjectDefinationData {

	private Integer objectId;

	private String name;

	private String schemaName;

	private String dataBaseName;

	private String userDefinedName;

	private String camelCaseName;

	private Boolean embaddable;

	private Boolean updated;

	private Boolean primaryKey;

	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}

	public Integer getObjectId() {
		return this.objectId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	public String getSchemaName() {
		return this.schemaName;
	}

	public void setDataBaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
	}

	public String getDataBaseName() {
		return this.dataBaseName;
	}

	public void setUserDefinedName(String userDefinedName) {
		this.userDefinedName = userDefinedName;
	}

	public String getUserDefinedName() {
		return this.userDefinedName;
	}

	public void setCamelCaseName(String camelCaseName) {
		this.camelCaseName = camelCaseName;
	}

	public String getCamelCaseName() {
		return this.camelCaseName;
	}

	public void setEmbaddable(Boolean embaddable) {
		this.embaddable = embaddable;
	}

	public Boolean getEmbaddable() {
		return this.embaddable;
	}

	public void setUpdated(Boolean updated) {
		this.updated = updated;
	}

	public Boolean getUpdated() {
		return this.updated;
	}

	public void setPrimaryKey(Boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Boolean getPrimaryKey() {
		return this.primaryKey;
	}

}