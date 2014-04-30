package com.tmg.uifwk.orm.ui;

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

import com.tmg.uifwk.orm.BaseEntityAttributes;

/**
 * The persistent class for the FormDesign database table.
 * 
 */
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(schema = "UI", name = "FormDesign")
public class FormDesign extends BaseEntityAttributes {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FormID")
	private Integer formID;

	@Column(name = "FormName")
	private String formName;

	@Column(name = "DisplayText")
	private String displayText;

	@Column(name = "IsActive")
	private boolean active;

	@Column(name = "Abbreviation")
	private String abbreviation;

	@Column(name = "TenantID")
	private int tenantID;

	// bi-directional many-to-one association to FormDesignGroupMapping
	@OneToMany(mappedBy = "formDesign", fetch = FetchType.LAZY)
	private List<FormDesignGroupMapping> formDesignGroupMappings;

	// bi-directional many-to-one association to FormDesignVersion
	@OneToMany(mappedBy = "formDesign", fetch = FetchType.LAZY)
	private List<FormDesignVersion> formDesignVersions;

	// bi-directional many-to-one association to UIElement
	@OneToMany(mappedBy = "formDesign", fetch = FetchType.LAZY)
	private List<UIElement> uielements;

	public FormDesign() {
	}

	public Integer getFormID() {
		return this.formID;
	}

	public void setFormID(Integer formID) {
		this.formID = formID;
	}

	public String getAbbreviation() {
		return this.abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
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

	public String getDisplayText() {
		return this.displayText;
	}

	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

	public String getFormName() {
		return this.formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getTenantID() {
		return this.tenantID;
	}

	public void setTenantID(int tenantID) {
		this.tenantID = tenantID;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public List<FormDesignGroupMapping> getFormDesignGroupMappings() {
		return this.formDesignGroupMappings;
	}

	public void setFormDesignGroupMappings(
			List<FormDesignGroupMapping> formDesignGroupMappings) {
		this.formDesignGroupMappings = formDesignGroupMappings;
	}

	public FormDesignGroupMapping addFormDesignGroupMapping(
			FormDesignGroupMapping formDesignGroupMapping) {
		getFormDesignGroupMappings().add(formDesignGroupMapping);
		formDesignGroupMapping.setFormDesign(this);

		return formDesignGroupMapping;
	}

	public FormDesignGroupMapping removeFormDesignGroupMapping(
			FormDesignGroupMapping formDesignGroupMapping) {
		getFormDesignGroupMappings().remove(formDesignGroupMapping);
		formDesignGroupMapping.setFormDesign(null);

		return formDesignGroupMapping;
	}

	public List<FormDesignVersion> getFormDesignVersions() {
		return this.formDesignVersions;
	}

	public void setFormDesignVersions(List<FormDesignVersion> formDesignVersions) {
		this.formDesignVersions = formDesignVersions;
	}

	public FormDesignVersion addFormDesignVersion(
			FormDesignVersion formDesignVersion) {
		getFormDesignVersions().add(formDesignVersion);
		formDesignVersion.setFormDesign(this);

		return formDesignVersion;
	}

	public FormDesignVersion removeFormDesignVersion(
			FormDesignVersion formDesignVersion) {
		getFormDesignVersions().remove(formDesignVersion);
		formDesignVersion.setFormDesign(null);

		return formDesignVersion;
	}

	public List<UIElement> getUielements() {
		return this.uielements;
	}

	public void setUielements(List<UIElement> uielements) {
		this.uielements = uielements;
	}

	public UIElement addUielement(UIElement uielement) {
		getUielements().add(uielement);
		uielement.setFormDesign(this);

		return uielement;
	}

	public UIElement removeUielement(UIElement uielement) {
		getUielements().remove(uielement);
		uielement.setFormDesign(null);

		return uielement;
	}

}