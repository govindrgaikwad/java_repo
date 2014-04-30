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
@Table(schema = "Data", name = "StringDataValue")
public class StringDataValue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ValueID")
	private Long valueID;

	@ManyToOne
	@JoinColumn(name = "ObjVerID")
	private ObjectVersionAttributeMap objectVersionAttributeMap;

	@Column(name = "ObjInstanceID")
	private Long objInstanceID;

	@Column(name = "ParentObjInstanceID")
	private Long parentObjInstanceID;

	@Column(name = "RowIDInfo")
	private Long rowIDInfo;

	public Long getValueID() {
		return valueID;
	}

	public void setValueID(Long valueID) {
		this.valueID = valueID;
	}

	public ObjectVersionAttributeMap getObjectVersionAttributeMap() {
		return objectVersionAttributeMap;
	}

	public void setObjectVersionAttributeMap(
			ObjectVersionAttributeMap objectVersionAttributeMap) {
		this.objectVersionAttributeMap = objectVersionAttributeMap;
	}

	public Long getObjInstanceID() {
		return objInstanceID;
	}

	public void setObjInstanceID(Long objInstanceID) {
		this.objInstanceID = objInstanceID;
	}

	public Long getParentObjInstanceID() {
		return parentObjInstanceID;
	}

	public void setParentObjInstanceID(Long parentObjInstanceID) {
		this.parentObjInstanceID = parentObjInstanceID;
	}

	public Long getRowIDInfo() {
		return rowIDInfo;
	}

	public void setRowIDInfo(Long rowIDInfo) {
		this.rowIDInfo = rowIDInfo;
	}

}
