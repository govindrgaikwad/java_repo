package com.tmg.uifwk.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "DM", name = "ObjectTree")
public class ObjectTree {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TREEID")
	private Long treeID;

	@ManyToOne
	@JoinColumn(name = "ParentOID")
	private ObjectDefinition parentObject;

	@ManyToOne
	@JoinColumn(name = "RootOID")
	private ObjectDefinition rootObject;

	@ManyToOne
	@JoinColumn(name = "VersionID")
	private ObjectVersion objectVersion;

	@ManyToOne
	@JoinColumn(name = "RelationID")
	private ObjectRelation relation;

	public Long getTreeID() {
		return treeID;
	}

	public void setTreeID(Long treeID) {
		this.treeID = treeID;
	}

	public ObjectDefinition getParentObject() {
		return parentObject;
	}

	public void setParentObject(ObjectDefinition parentObject) {
		this.parentObject = parentObject;
	}

	public ObjectDefinition getRootObject() {
		return rootObject;
	}

	public void setRootObject(ObjectDefinition rootObject) {
		this.rootObject = rootObject;
	}

	public ObjectVersion getObjectVersion() {
		return objectVersion;
	}

	public void setObjectVersion(ObjectVersion objectVersion) {
		this.objectVersion = objectVersion;
	}

	public ObjectRelation getRelation() {
		return relation;
	}

	public void setRelation(ObjectRelation relation) {
		this.relation = relation;
	}

}
