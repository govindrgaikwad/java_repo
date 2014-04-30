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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.tmg.uifwk.orm.BaseEntityAttributes;

/**
 * The persistent class for the FormDesignVersion database table.
 * 
 */
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(schema = "UI", name = "FormDesignVersion")
public class FormDesignVersion extends BaseEntityAttributes {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FormDesignVersionID")
	private Integer formDesignVersionID;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FormDesignID")
	private FormDesign formDesign;

	@Column(name = "TenantID")
	private Integer tenantID;

	@Column(name = "EffectiveDate")
	private Timestamp effectiveDate;

	@Column(name = "FormDesignVersionData")
	private String formDesignVersionData;

	@Column(name = "VersionNumber")
	private String versionNumber;

	// bi-directional many-to-one association to Status
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "StatusID")
	private Status status;

	@OneToMany(mappedBy = "formDesignVersion", fetch = FetchType.LAZY)
	private List<FormDesignVersionUIElementMap> formDesignVersionUielementMaps;

	public FormDesignVersion() {
	}

	public Integer getFormDesignVersionID() {
		return this.formDesignVersionID;
	}

	public void setFormDesignVersionID(Integer formDesignVersionID) {
		this.formDesignVersionID = formDesignVersionID;
	}

	public Timestamp getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Timestamp effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Integer getTenantID() {
		return this.tenantID;
	}

	public void setTenantID(Integer tenantID) {
		this.tenantID = tenantID;
	}

	public FormDesign getFormDesign() {
		return this.formDesign;
	}

	public String getFormDesignVersionData() {
		return formDesignVersionData;
	}

	public void setFormDesignVersionData(String formDesignVersionData) {
		this.formDesignVersionData = formDesignVersionData;
	}

	public String getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}

	public void setFormDesign(FormDesign formDesign) {
		this.formDesign = formDesign;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<FormDesignVersionUIElementMap> getFormDesignVersionUielementMaps() {
		return formDesignVersionUielementMaps;
	}

	public void setFormDesignVersionUielementMaps(
			List<FormDesignVersionUIElementMap> formDesignVersionUielementMaps) {
		this.formDesignVersionUielementMaps = formDesignVersionUielementMaps;
	}

}