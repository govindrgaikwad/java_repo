package com.tmg.uifwk.orm;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema = "DM", name = "ObjectVersionAttribXRef")
public class ObjectVersionAttributeMap {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ObjVerID")
	private Long objectVersionId;

	@ManyToOne
	@JoinColumn(name = "VersionID")
	private ObjectVersion objectVersion;

	@ManyToOne
	@JoinColumn(name = "OID")
	private ObjectDefinition object;

	@ManyToOne
	@JoinColumn(name = "AttrID")
	private Attribute attribute;

	@OneToMany()
	@JoinColumn(name = "ObjVerID")
	private List<StringDataValue> stringValues;

	public ObjectVersion getObjectVersion() {
		return objectVersion;
	}

	public void setObjectVersion(ObjectVersion objectVersion) {
		this.objectVersion = objectVersion;
	}

	public ObjectDefinition getObject() {
		return object;
	}

	public void setObject(ObjectDefinition object) {
		this.object = object;
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public Long getObjectVersionId() {
		return objectVersionId;
	}

	public void setObjectVersionId(Long objectVersionId) {
		this.objectVersionId = objectVersionId;
	}
}
