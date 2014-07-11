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
import javax.persistence.OneToOne;
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

	@OneToMany(mappedBy = "objectDefination", fetch = FetchType.LAZY)
	private Set<Attribute> attributes = new HashSet<Attribute>();

	@OneToMany(mappedBy = "objectDefination1", fetch = FetchType.LAZY)
	private Set<Attribute> attributes1 = new HashSet<Attribute>();

	@OneToMany(mappedBy = "objectDefination", fetch = FetchType.LAZY)
	private Set<Method> methods = new HashSet<Method>();

	@OneToMany(mappedBy = "objectDefination1", fetch = FetchType.LAZY)
	private Set<Relationship> relationships = new HashSet<Relationship>();

	@OneToMany(mappedBy = "objectDefination", fetch = FetchType.LAZY)
	private Set<Relationship> relationships1 = new HashSet<Relationship>();

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

	public void setAttributes(Set<Attribute> attributes) {
		this.attributes = attributes;
	}

	public Set<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes1(Set<Attribute> attributes1) {
		this.attributes1 = attributes1;
	}

	public Set<Attribute> getAttributes1() {
		return attributes1;
	}

	public void setMethods(Set<Method> methods) {
		this.methods = methods;
	}

	public Set<Method> getMethods() {
		return methods;
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