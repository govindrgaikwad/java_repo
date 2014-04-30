package com.tmg.uifwk.orm.ui;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * The persistent class for the ApplicationDataType database table.
 * 
 */
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Entity
@Table(schema = "UI", name = "ApplicationDataType")
public class ApplicationDataType implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ApplicationDataTypeID")
	private int applicationDataTypeID;

	@Column(name = "AddedBy")
	private String addedBy;

	@Column(name = "AddedDate")
	private Timestamp addedDate;

	@Column(name = "ApplicationDataTypeName")
	private String applicationDataTypeName;

	@Column(name = "DisplayText")
	private String displayText;

	// bi-directional many-to-one association to UIElement
	@OneToMany(mappedBy = "applicationDataType", fetch=FetchType.LAZY)
	private List<UIElement> uielements;

	public ApplicationDataType() {
	}

	public int getApplicationDataTypeID() {
		return this.applicationDataTypeID;
	}

	public void setApplicationDataTypeID(int applicationDataTypeID) {
		this.applicationDataTypeID = applicationDataTypeID;
	}

	public String getAddedBy() {
		return this.addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	public Timestamp getAddedDate() {
		return this.addedDate;
	}

	public void setAddedDate(Timestamp addedDate) {
		this.addedDate = addedDate;
	}

	public String getApplicationDataTypeName() {
		return this.applicationDataTypeName;
	}

	public void setApplicationDataTypeName(String applicationDataTypeName) {
		this.applicationDataTypeName = applicationDataTypeName;
	}

	public String getDisplayText() {
		return this.displayText;
	}

	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

	public List<UIElement> getUielements() {
		return this.uielements;
	}

	public void setUielements(List<UIElement> uielements) {
		this.uielements = uielements;
	}

	public UIElement addUielement(UIElement uielement) {
		getUielements().add(uielement);
		uielement.setApplicationDataType(this);

		return uielement;
	}

	public UIElement removeUielement(UIElement uielement) {
		getUielements().remove(uielement);
		uielement.setApplicationDataType(null);

		return uielement;
	}

}