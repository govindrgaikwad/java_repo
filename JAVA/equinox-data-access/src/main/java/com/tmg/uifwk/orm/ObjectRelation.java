package com.tmg.uifwk.orm;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "DM", name = "ObjectRelation")
public class ObjectRelation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RelationID")
	private Long relationID;

	@Column(name = "RelationName")
	private String relationName;

	@Column(name = "RelationNameCamelCase")
	private String relationNameCamelCase;

	@Column(name = "Cardinality")
	private String cardinality;

	@OneToOne
	@JoinColumn(name = "RelationID", insertable = false, updatable = false)
	private ObjectTree objectTree;

	@ManyToOne
	@JoinColumn(name = "RelatedObjectID")
	private ObjectDefinition relatedObject;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "RelationID")
	private Set<RelationKey> relationKeys;

	public Long getRelationID() {
		return relationID;
	}

	public void setRelationID(Long relationID) {
		this.relationID = relationID;
	}

	public ObjectTree getObjectTree() {
		return objectTree;
	}

	public void setObjectTree(ObjectTree objectTree) {
		this.objectTree = objectTree;
	}

	public String getRelationName() {
		return relationName;
	}

	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}

	public String getRelationNameCamelCase() {
		return relationNameCamelCase;
	}

	public void setRelationNameCamelCase(String relationNameCamelCase) {
		this.relationNameCamelCase = relationNameCamelCase;
	}

	public String getCardinality() {
		return cardinality;
	}

	public void setCardinality(String cardinality) {
		this.cardinality = cardinality;
	}

	public Set<RelationKey> getRelationKeys() {
		return relationKeys;
	}

	public void setRelationKeys(Set<RelationKey> relationKeys) {
		this.relationKeys = relationKeys;
	}

	public ObjectDefinition getRelatedObject() {
		return relatedObject;
	}

	public void setRelatedObject(ObjectDefinition relatedObject) {
		this.relatedObject = relatedObject;
	}

}
