package com.tmg.uifwk.orm;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema = "DM", name = "ObjectDefinition")
public class ObjectDefinition {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OID")
	private Long objectID;

	@Column(name = "ObjectName")
	private String name;

	@Column(name = "locked")
	private boolean locked;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "OID")
	private Set<ObjectVersionAttributeMap> attributeMap;

	public Long getObjectId() {
		return objectID;
	}

	public void setObjectId(Long objectId) {
		this.objectID = objectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public Set<ObjectVersionAttributeMap> getAttributeMap() {
		return attributeMap;
	}

	public void setAttributeMap(Set<ObjectVersionAttributeMap> attributeMap) {
		this.attributeMap = attributeMap;
	}

}
