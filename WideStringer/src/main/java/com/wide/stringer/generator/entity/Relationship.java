package com.wide.stringer.generator.entity;

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
@Table(schema="Data",name = "Relationship")
public class Relationship {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RelationshipId")
	private Integer relationshipId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ParentObjectId")
	private ObjectDefination parentObjectDefination;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RelatedObjectId")
	private ObjectDefination relatedObjectDefination;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ParentAttributeId")
	private Attribute parentAttribute;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ChildAttributeId")
	private Attribute childAttribute;

	@Column(name = "RelationName")
	private String relationName;

	@Column(name = "CamelcaseName")
	private String camelcaseName;

	@Column(name = "Cardinality")
	private String cardinality;

	public Integer getRelationshipId() {
		return relationshipId;
	}

	public void setRelationshipId(Integer relationshipId) {
		this.relationshipId = relationshipId;
	}

	public ObjectDefination getParentObjectDefination() {
		return parentObjectDefination;
	}

	public void setParentObjectDefination(
			ObjectDefination parentObjectDefination) {
		this.parentObjectDefination = parentObjectDefination;
	}

	public ObjectDefination getRelatedObjectDefination() {
		return relatedObjectDefination;
	}

	public void setRelatedObjectDefination(
			ObjectDefination relatedObjectDefination) {
		this.relatedObjectDefination = relatedObjectDefination;
	}

	public Attribute getParentAttribute() {
		return parentAttribute;
	}

	public void setParentAttribute(Attribute parentAttribute) {
		this.parentAttribute = parentAttribute;
	}

	public Attribute getChildAttribute() {
		return childAttribute;
	}

	public void setChildAttribute(Attribute childAttribute) {
		this.childAttribute = childAttribute;
	}

	public String getRelationName() {
		return relationName;
	}

	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}

	public String getCamelcaseName() {
		return camelcaseName;
	}

	public void setCamelcaseName(String camelcaseName) {
		this.camelcaseName = camelcaseName;
	}

	public String getCardinality() {
		return cardinality;
	}

	public void setCardinality(String cardinality) {
		this.cardinality = cardinality;
	}

}