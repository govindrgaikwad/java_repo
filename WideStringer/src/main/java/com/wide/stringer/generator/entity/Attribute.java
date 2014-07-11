package com.wide.stringer.generator.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema = "Data", name = "Attribute")
public class Attribute {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AttributeId")
	private Integer attributeId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ObjectId")
	private ObjectDefination objectDefination;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ReferenceObjectId")
	private ObjectDefination referenceObjectDefination;

	@Column(name = "Name")
	private String name;

	@Column(name = "UserDefinedName")
	private String userDefinedName;

	@Column(name = "CamelCaseName")
	private String camelCaseName;

	@Column(name = "ReferenceObjectName")
	private String referenceObjectName;

	@Column(name = "ReferenceName")
	private String referenceName;

	@Column(name = "Datatype")
	private String datatype;

	@Column(name = "JavaDataType")
	private String javaDataType;

	@Column(name = "PrimaryKey")
	private Boolean primaryKey;

	@Column(name = "ForeignKey")
	private Boolean foreignKey;

	@Column(name = "Embaddable")
	private Boolean embaddable;

	@OneToMany(mappedBy = "attribute", fetch = FetchType.LAZY)
	private Set<MethodParameter> methodParameters = new HashSet<MethodParameter>();

	@OneToMany(mappedBy = "parentAttribute", fetch = FetchType.LAZY)
	private Set<Relationship> parentRelationships = new HashSet<Relationship>();

	@OneToMany(mappedBy = "childAttribute", fetch = FetchType.LAZY)
	private Set<Relationship> childRelationships = new HashSet<Relationship>();

	public Integer getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(Integer attributeId) {
		this.attributeId = attributeId;
	}

	public ObjectDefination getObjectDefination() {
		return objectDefination;
	}

	public void setObjectDefination(ObjectDefination objectDefination) {
		this.objectDefination = objectDefination;
	}

	public ObjectDefination getReferenceObjectDefination() {
		return referenceObjectDefination;
	}

	public void setReferenceObjectDefination(
			ObjectDefination referenceObjectDefination) {
		this.referenceObjectDefination = referenceObjectDefination;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	public String getJavaDataType() {
		return javaDataType;
	}

	public void setJavaDataType(String javaDataType) {
		this.javaDataType = javaDataType;
	}

	public Boolean getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(Boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Boolean getForeignKey() {
		return foreignKey;
	}

	public void setForeignKey(Boolean foreignKey) {
		this.foreignKey = foreignKey;
	}

	public Boolean getEmbaddable() {
		return embaddable;
	}

	public void setEmbaddable(Boolean embaddable) {
		this.embaddable = embaddable;
	}

	public Set<MethodParameter> getMethodParameters() {
		return methodParameters;
	}

	public void setMethodParameters(Set<MethodParameter> methodParameters) {
		this.methodParameters = methodParameters;
	}

	public Set<Relationship> getParentRelationships() {
		return parentRelationships;
	}

	public void setParentRelationships(Set<Relationship> parentRelationships) {
		this.parentRelationships = parentRelationships;
	}

	public Set<Relationship> getChildRelationships() {
		return childRelationships;
	}

	public void setChildRelationships(Set<Relationship> childRelationships) {
		this.childRelationships = childRelationships;
	}

}