package com.wide.stringer.generator.dto;

public class AttributeData {

	private Integer attributeId;

	private Integer objectId;

	private Integer referenceObjectId;

	private String name;

	private String userDefinedName;

	private String camelCaseName;

	private String referenceObjectName;

	private String referenceName;

	private String datatype;

	private String javaDataType;

	private Boolean primaryKey;

	private Boolean foreignKey;

	private Boolean embaddable;

	public void setAttributeId(Integer attributeId) {
		this.attributeId = attributeId;
	}

	public Integer getAttributeId() {
		return this.attributeId;
	}

	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}

	public Integer getObjectId() {
		return this.objectId;
	}

	public void setReferenceObjectId(Integer referenceObjectId) {
		this.referenceObjectId = referenceObjectId;
	}

	public Integer getReferenceObjectId() {
		return this.referenceObjectId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
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

	public String getReferenceObjectName() {
		return referenceObjectName;
	}

	public void setReferenceObjectName(String referenceObjectName) {
		this.referenceObjectName = referenceObjectName;
	}

	public String getReferenceName() {
		return referenceName;
	}

	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	public String getDatatype() {
		return this.datatype;
	}

	public void setJavaDataType(String javaDataType) {
		this.javaDataType = javaDataType;
	}

	public String getJavaDataType() {
		return this.javaDataType;
	}

	public void setPrimaryKey(Boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Boolean getPrimaryKey() {
		return this.primaryKey;
	}

	public void setForeignKey(Boolean foreignKey) {
		this.foreignKey = foreignKey;
	}

	public Boolean getForeignKey() {
		return this.foreignKey;
	}

	public void setEmbaddable(Boolean embaddable) {
		this.embaddable = embaddable;
	}

	public Boolean getEmbaddable() {
		return this.embaddable;
	}

}