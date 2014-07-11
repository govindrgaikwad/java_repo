package com.wide.stringer.generator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "Data", name = "ObjectDefination")
public class ObjectDefination {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ObjectId")
	private Integer objectId;

	@Column(name = "Name")
	private String name;

	@Column(name = "SchemaName")
	private String schemaName;

	@Column(name = "DataBaseName")
	private String dataBaseName;

	@Column(name = "UserDefinedName")
	private String userDefinedName;

	@Column(name = "CamelCaseName")
	private String camelCaseName;

	@Column(name = "Embaddable")
	private Boolean embaddable;

	@Column(name = "Updated")
	private Boolean updated;

	@Column(name = "primaryKey")
	private Boolean primaryKey;

	public Integer getObjectId() {
		return objectId;
	}

	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	public String getDataBaseName() {
		return dataBaseName;
	}

	public void setDataBaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
	}

	public String getUserDefinedName() {
		return userDefinedName;
	}

	public void setUserDefinedName(String userDefinedName) {
		this.userDefinedName = userDefinedName;
	}

	public String getCamelCaseName() {
		return camelCaseName;
	}

	public void setCamelCaseName(String camelCaseName) {
		this.camelCaseName = camelCaseName;
	}

	public Boolean getEmbaddable() {
		return embaddable;
	}

	public void setEmbaddable(Boolean embaddable) {
		this.embaddable = embaddable;
	}

	public Boolean getUpdated() {
		return updated;
	}

	public void setUpdated(Boolean updated) {
		this.updated = updated;
	}

	public Boolean getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(Boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

}