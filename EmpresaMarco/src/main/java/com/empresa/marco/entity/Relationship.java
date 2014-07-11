package com.empresa.marco.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "Data",name = "Relationship")
public class Relationship {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RelationshipId")
	private Integer relationshipId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ParentObjectId")
	private ObjectDefination objectDefination;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ChildObjectId")
	private ObjectDefination objectDefination1;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ParentAttributeId")
	private Attribute attribute;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ChildAttributeId")
	private Attribute attribute1;

	@Column(name = "RelationName")
	private String relationName;

	@Column(name = "CamelcaseName")
	private String camelcaseName;

	@Column(name = "Cardinality")
	private String cardinality;

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

	public void setRelationshipId(Integer relationshipId) {
		this.relationshipId = relationshipId;
	}

	public Integer getRelationshipId() {
		return this.relationshipId;
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

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public Attribute getAttribute() {
		return this.attribute;
	}

	public void setAttribute1(Attribute attribute1) {
		this.attribute1 = attribute1;
	}

	public Attribute getAttribute1() {
		return this.attribute1;
	}

	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}

	public String getRelationName() {
		return this.relationName;
	}

	public void setCamelcaseName(String camelcaseName) {
		this.camelcaseName = camelcaseName;
	}

	public String getCamelcaseName() {
		return this.camelcaseName;
	}

	public void setCardinality(String cardinality) {
		this.cardinality = cardinality;
	}

	public String getCardinality() {
		return this.cardinality;
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

}