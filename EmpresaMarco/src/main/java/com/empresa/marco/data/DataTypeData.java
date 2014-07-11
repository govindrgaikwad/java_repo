package com.empresa.marco.data;

public class DataTypeData {

	private Integer dataTypeId;

	private Integer constantValue;

	private String sQLType;

	private String javaType;

	public void setDataTypeId(Integer dataTypeId) {
		this.dataTypeId = dataTypeId;
	}

	public Integer getDataTypeId() {
		return this.dataTypeId;
	}

	public void setConstantValue(Integer constantValue) {
		this.constantValue = constantValue;
	}

	public Integer getConstantValue() {
		return this.constantValue;
	}

	public void setSQLType(String sQLType) {
		this.sQLType = sQLType;
	}

	public String getSQLType() {
		return this.sQLType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public String getJavaType() {
		return this.javaType;
	}

}