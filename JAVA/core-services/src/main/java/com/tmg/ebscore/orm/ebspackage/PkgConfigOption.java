package com.tmg.ebscore.orm.ebspackage;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

import com.tmg.ebscore.orm.CoreCommonAttributes;
import com.tmg.ebscore.orm.masterlist.MasterListStringVal;
import com.tmg.ebscore.orm.packageversion.PkgVerProductBenefitOptionXref;
import com.tmg.ebscore.orm.packageversion.PkgVersion;
import com.tmg.ebscore.orm.packageversion.PkgVersionProductXref;
import com.tmg.ebscore.orm.packageversion.PkgVersionWorkflowState;

@Entity
@Table(schema = "Pkg", name = "PkgConfigOption")
public class PkgConfigOption extends CoreCommonAttributes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PkgConfigOption1up")
	private Integer pkgConfigOption1up;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PkgConfigOptionFamily1up")
	private PkgConfigOptionFamily pkgConfigOptionFamily;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PkgVersion1up")
	private PkgVersion pkgVersion;

	@Column(name = "SalesEffectiveDate")
	private Date salesEffectiveDate;

	@Column(name = "SalesCancelDate")
	private Date salesCancelDate;

	@Column(name = "ClaimsEffectiveDate")
	private Date claimsEffectiveDate;

	@Column(name = "ClaimsCancelDate")
	private Date claimsCancelDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PackageVersionWorkflowState1up")
	private PkgVersionWorkflowState pkgVersionWorkflowState;

	@Column(name = "DoNotUse")
	private Boolean doNotUse;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MarketSegment")
	private MasterListStringVal masterListStringVal;

	@Column(name = "Description")
	private String description;

	@Column(name = "Name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy = "pkgVerProductBenefitOptionXrefId.pkgConfigOption", fetch = FetchType.LAZY)
	private Set<PkgVerProductBenefitOptionXref> pkgProductConfigOprtion = new HashSet<PkgVerProductBenefitOptionXref>();

	@OneToMany(mappedBy = "pkgVersionProductXrefId.pkgConfigOption", fetch = FetchType.LAZY)
	private Set<PkgVersionProductXref> pkgConfigProductXref = new HashSet<PkgVersionProductXref>();

	@OneToMany(mappedBy = "pkgConfigOption", fetch = FetchType.LAZY)
	private Set<PkgConfigOptionBenefitOptionXref> pkgConfigXref = new HashSet<PkgConfigOptionBenefitOptionXref>();

	public Set<PkgConfigOptionBenefitOptionXref> getPkgConfigXref() {
		return pkgConfigXref;
	}

	public void setPkgConfigXref(
			Set<PkgConfigOptionBenefitOptionXref> pkgConfigXref) {
		this.pkgConfigXref = pkgConfigXref;
	}

	public Set<PkgVersionProductXref> getPkgConfigProductXref() {
		return pkgConfigProductXref;
	}

	public void setPkgConfigProductXref(
			Set<PkgVersionProductXref> pkgConfigProductXref) {
		this.pkgConfigProductXref = pkgConfigProductXref;
	}

	public Set<PkgVerProductBenefitOptionXref> getPkgProductConfigOprtion() {
		return pkgProductConfigOprtion;
	}

	public void setPkgProductConfigOprtion(
			Set<PkgVerProductBenefitOptionXref> pkgProductConfigOprtion) {
		this.pkgProductConfigOprtion = pkgProductConfigOprtion;
	}

	public Integer getPkgConfigOption1up() {
		return pkgConfigOption1up;
	}

	public void setPkgConfigOption1up(Integer pkgConfigOption1up) {
		this.pkgConfigOption1up = pkgConfigOption1up;
	}

	public PkgConfigOptionFamily getPkgConfigOptionFamily() {
		return pkgConfigOptionFamily;
	}

	public void setPkgConfigOptionFamily(
			PkgConfigOptionFamily pkgConfigOptionFamily) {
		this.pkgConfigOptionFamily = pkgConfigOptionFamily;
	}

	public PkgVersion getPkgVersion() {
		return pkgVersion;
	}

	public void setPkgVersion(PkgVersion pkgVersion) {
		this.pkgVersion = pkgVersion;
	}

	public Date getSalesEffectiveDate() {
		return salesEffectiveDate;
	}

	public void setSalesEffectiveDate(Date salesEffectiveDate) {
		this.salesEffectiveDate = salesEffectiveDate;
	}

	public Date getSalesCancelDate() {
		return salesCancelDate;
	}

	public void setSalesCancelDate(Date salesCancelDate) {
		this.salesCancelDate = salesCancelDate;
	}

	public Date getClaimsEffectiveDate() {
		return claimsEffectiveDate;
	}

	public void setClaimsEffectiveDate(Date claimsEffectiveDate) {
		this.claimsEffectiveDate = claimsEffectiveDate;
	}

	public Date getClaimsCancelDate() {
		return claimsCancelDate;
	}

	public void setClaimsCancelDate(Date claimsCancelDate) {
		this.claimsCancelDate = claimsCancelDate;
	}

	public PkgVersionWorkflowState getPkgVersionWorkflowState() {
		return pkgVersionWorkflowState;
	}

	public void setPkgVersionWorkflowState(
			PkgVersionWorkflowState pkgVersionWorkflowState) {
		this.pkgVersionWorkflowState = pkgVersionWorkflowState;
	}

	public Boolean getDoNotUse() {
		return doNotUse;
	}

	public void setDoNotUse(Boolean doNotUse) {
		this.doNotUse = doNotUse;
	}

	public MasterListStringVal getMasterListStringVal() {
		return masterListStringVal;
	}

	public void setMasterListStringVal(MasterListStringVal masterListStringVal) {
		this.masterListStringVal = masterListStringVal;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
