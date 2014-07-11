package com.empresa.marco.data;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class RelationshipData {

	private Integer relationshipId;

	private Integer parentObjectId;

	private Integer childObjectId;

	private Integer parentAttributeId;

	private Integer childAttributeId;

	private String relationName;

	private String camelcaseName;

	private String cardinality;

	private Integer projectId;

	private Integer projectVersionId;

	private String createdBy;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd,HH:00", timezone = "CET")
	private Date createdDate;

	private String updatedBy;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd,HH:00", timezone = "CET")
	private Date updatedDate;

	public void setRelationshipId(Integer relationshipId) {
		this.relationshipId = relationshipId;
	}

	public Integer getRelationshipId() {
		return this.relationshipId;
	}

	public void setParentObjectId(Integer parentObjectId) {
		this.parentObjectId = parentObjectId;
	}

	public Integer getParentObjectId() {
		return this.parentObjectId;
	}

	public void setChildObjectId(Integer childObjectId) {
		this.childObjectId = childObjectId;
	}

	public Integer getChildObjectId() {
		return this.childObjectId;
	}

	public void setParentAttributeId(Integer parentAttributeId) {
		this.parentAttributeId = parentAttributeId;
	}

	public Integer getParentAttributeId() {
		return this.parentAttributeId;
	}

	public void setChildAttributeId(Integer childAttributeId) {
		this.childAttributeId = childAttributeId;
	}

	public Integer getChildAttributeId() {
		return this.childAttributeId;
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

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getProjectId() {
		return this.projectId;
	}

	public void setProjectVersionId(Integer projectVersionId) {
		this.projectVersionId = projectVersionId;
	}

	public Integer getProjectVersionId() {
		return this.projectVersionId;
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