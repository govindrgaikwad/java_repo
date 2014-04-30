package com.tmg.uifwk.orm;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(schema = "DM", name = "Attribute")
public class Attribute {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AttrID")
	private Long attributeID;

	@OneToMany()
	@JoinColumn(name = "AttrID")
	private List<ObjectVersionAttributeMap> attributeMap;

	@Column(name = "Name")
	private String name;

	@Column(name = "AttrType")
	private String type;

	@Column(name = "NameCamelCase")
	private String camelCaseName;

	@Column(name = "Cardinality")
	private String cardinality;

	@Column(name = "Length")
	private String length;

	@Column(name = "Precision")
	private String precision;

	@Column(name = "EditRegex")
	private String editRegex;

	@Column(name = "Formatter")
	private String fomatter;

	@Column(name = "Synthetic")
	private Boolean synthetic;

	@Column(name = "DefaultValue")
	private String defaultValue;

	@Transient
	private String dataType;

	public Long getAttributeID() {
		return attributeID;
	}

	public void setAttributeID(Long attributeID) {
		this.attributeID = attributeID;
	}

	public List<ObjectVersionAttributeMap> getAttributeMap() {
		return attributeMap;
	}

	public void setAttributeMap(List<ObjectVersionAttributeMap> attributeMap) {
		this.attributeMap = attributeMap;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCamelCaseName() {
		return camelCaseName;
	}

	public void setCamelCaseName(String camelCaseName) {
		this.camelCaseName = camelCaseName;
	}

	public String getCardinality() {
		return cardinality;
	}

	public void setCardinality(String cardinality) {
		this.cardinality = cardinality;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getPrecision() {
		return precision;
	}

	public void setPrecision(String precision) {
		this.precision = precision;
	}

	public String getEditRegex() {
		return editRegex;
	}

	public void setEditRegex(String editRegex) {
		this.editRegex = editRegex;
	}

	public String getFomatter() {
		return fomatter;
	}

	public void setFomatter(String fomatter) {
		this.fomatter = fomatter;
	}

	public boolean isSynthetic() {
		return synthetic;
	}

	public void setSynthetic(boolean synthetic) {
		this.synthetic = synthetic;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getSynthetic() {
		return synthetic;
	}

	public void setSynthetic(Boolean synthetic) {
		this.synthetic = synthetic;
	}

	public String getDataType() {

		if (this.type == null) {
			dataType = "Object";
		} else if (this.type.equalsIgnoreCase("String")) {
			dataType = "String";
		} else if (this.type.equalsIgnoreCase("Double")) {
			dataType = "Double";
		} else if (this.type.equalsIgnoreCase("Boolean")) {
			dataType = "Boolean";
		} else if (this.type.equalsIgnoreCase("Long") || this.type.equalsIgnoreCase("Integer")) {
			dataType = "Long";
		} else if (this.type.equalsIgnoreCase("Date")) {
			dataType = "java.util.Date";
		} else {
			dataType = "Object";
		}
		return dataType;
	}

}
