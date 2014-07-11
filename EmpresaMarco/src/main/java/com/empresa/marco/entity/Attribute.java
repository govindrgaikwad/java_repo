package com.empresa.marco.entity;

import java.util.Date;
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
@Table(schema = "Data",name = "Attribute")
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
	private ObjectDefination objectDefination1;

	@Column(name = "Name")
	private String name;

	@Column(name = "UserDefinedName")
	private String userDefinedName;

	@Column(name = "CamelCaseName")
	private String camelCaseName;

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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ProjectId")
	private Project project;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ProjectVersionId")
	private ProjectVersion projectVersion;

	@Column(name = "CreatedBy")
	private String createdBy;

	@Column(name = "CreatedDate")
	private Date createdDate;

	@Column(name = "UpdatedBy")
	private String updatedBy;

	@Column(name = "UpdatedDate")
	private Date updatedDate;

	@OneToMany(mappedBy = "attribute", fetch = FetchType.LAZY)
	private Set<MethodParameter> methodParameters = new HashSet<MethodParameter>();

	@OneToMany(mappedBy = "attribute1", fetch = FetchType.LAZY)
	private Set<Relationship> relationships = new HashSet<Relationship>();

	@OneToMany(mappedBy = "attribute", fetch = FetchType.LAZY)
	private Set<Relationship> relationships1 = new HashSet<Relationship>();

	public void setAttributeId(Integer attributeId) {
		this.attributeId = attributeId;
	}

	public Integer getAttributeId() {
		return this.attributeId;
	}

	public void setObjectDefination(ObjectDefination objectDefination) {
		this.objectDefination = objectDefination;
	}

	public ObjectDefination getObjectDefination() {
		return this.objectDefination;
	}

	public void setObjectDefination1(ObjectDefination objectDefination1) {
		this.objectDefination1 = objectDefination1;
	}

	public ObjectDefination getObjectDefination1() {
		return this.objectDefination1;
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

	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}

	public String getReferenceName() {
		return this.referenceName;
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

	public void setProject(Project project) {
		this.project = project;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProjectVersion(ProjectVersion projectVersion) {
		this.projectVersion = projectVersion;
	}

	public ProjectVersion getProjectVersion() {
		return this.projectVersion;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setMethodParameters(Set<MethodParameter> methodParameters) {
		this.methodParameters = methodParameters;
	}

	public Set<MethodParameter> getMethodParameters() {
		return methodParameters;
	}

	public void setRelationships(Set<Relationship> relationships) {
		this.relationships = relationships;
	}

	public Set<Relationship> getRelationships() {
		return relationships;
	}

	public void setRelationships1(Set<Relationship> relationships1) {
		this.relationships1 = relationships1;
	}

	public Set<Relationship> getRelationships1() {
		return relationships1;
	}

}