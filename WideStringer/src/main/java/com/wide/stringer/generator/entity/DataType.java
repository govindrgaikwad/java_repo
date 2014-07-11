package com.wide.stringer.generator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="Data",name = "DataType")
public class DataType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DataTypeId")
	private Integer dataTypeId;

	@Column(name = "ConstantValue")
	private Integer constantValue;

	@Column(name = "SQLType")
	private String sQLType;

	@Column(name = "JavaType")
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