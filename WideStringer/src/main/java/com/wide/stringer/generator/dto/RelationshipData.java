package com.wide.stringer.generator.dto;

public class RelationshipData {

	private Integer relationshipId;

	private Integer parentObjectId;

	private Integer relatedObjectId;

	private Integer parentAttributeId;

	private Integer childAttributeId;

	private String relationName;

	private String camelcaseName;

	private String cardinality;

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

	public void setRelatedObjectId(Integer relatedObjectId) {
		this.relatedObjectId = relatedObjectId;
	}

	public Integer getRelatedObjectId() {
		return this.relatedObjectId;
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

}