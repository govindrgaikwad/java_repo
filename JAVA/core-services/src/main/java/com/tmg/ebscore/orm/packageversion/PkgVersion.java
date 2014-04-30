package com.tmg.ebscore.orm.packageversion;

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
import com.tmg.ebscore.orm.ebspackage.BenefitPackage;
import com.tmg.ebscore.orm.ebspackage.PkgConfigOption;

@Entity
@Table(schema = "Pkg", name = "PkgVersion")
public class PkgVersion extends CoreCommonAttributes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PkgVersion1up")
	private Integer pkgVersion1up;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BenefitPackage1up")
	private BenefitPackage benefitPackage;

	@Column(name = "VersionNumber")
	private String versionNumber;

	@Column(name = "Name")
	private String name;

	@Column(name = "Description")
	private String description;

	@Column(name = "EffectiveDate")
	protected Date effectiveDate;

	@Column(name = "CancelDate")
	protected Date cancelDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PkgVersionWorkflowState1up")
	private PkgVersionWorkflowState pkgVersionWorkflowState;

	@OneToMany(mappedBy = "pkgVersion", fetch = FetchType.LAZY)
	private Set<PkgVerBenefitOption> pkgBenefitOption = new HashSet<PkgVerBenefitOption>();

	@OneToMany(mappedBy = "pkgVersion", fetch = FetchType.LAZY)
	private Set<PkgVerBig3CO> pkgBigCo = new HashSet<PkgVerBig3CO>();

	@OneToMany(mappedBy = "pkgVersion", fetch = FetchType.LAZY)
	private Set<PkgVerInstanceTree> pkgVerTree = new HashSet<PkgVerInstanceTree>();

	@OneToMany(mappedBy = "pkgVerProductBenefitOptionXrefId.pkgVersion", fetch = FetchType.LAZY)
	private Set<PkgVerProductBenefitOptionXref> pkgProductOprtion = new HashSet<PkgVerProductBenefitOptionXref>();

	@OneToMany(mappedBy = "pkgVersionProductXrefId.pkgVersion", fetch = FetchType.LAZY)
	private Set<PkgVersionProductXref> pkgProductXref = new HashSet<PkgVersionProductXref>();

	@OneToMany(mappedBy = "pkgVersion", fetch = FetchType.LAZY)
	private Set<BenefitOptionBig3CO> pkgVerBenOption = new HashSet<BenefitOptionBig3CO>();

	@OneToMany(mappedBy = "pkgVersion", fetch = FetchType.LAZY)
	private Set<PkgConfigOption> configOptionVer = new HashSet<PkgConfigOption>();

	public Set<PkgConfigOption> getConfigOptionVer() {
		return configOptionVer;
	}

	public void setConfigOptionVer(Set<PkgConfigOption> configOptionVer) {
		this.configOptionVer = configOptionVer;
	}

	public Set<BenefitOptionBig3CO> getPkgVerBenOption() {
		return pkgVerBenOption;
	}

	public void setPkgVerBenOption(Set<BenefitOptionBig3CO> pkgVerBenOption) {
		this.pkgVerBenOption = pkgVerBenOption;
	}

	public Set<PkgVerBenefitOption> getPkgBenefitOption() {
		return pkgBenefitOption;
	}

	public void setPkgBenefitOption(Set<PkgVerBenefitOption> pkgBenefitOption) {
		this.pkgBenefitOption = pkgBenefitOption;
	}

	public Set<PkgVerBig3CO> getPkgBigCo() {
		return pkgBigCo;
	}

	public void setPkgBigCo(Set<PkgVerBig3CO> pkgBigCo) {
		this.pkgBigCo = pkgBigCo;
	}

	public Set<PkgVerInstanceTree> getPkgVerTree() {
		return pkgVerTree;
	}

	public void setPkgVerTree(Set<PkgVerInstanceTree> pkgVerTree) {
		this.pkgVerTree = pkgVerTree;
	}

	public Set<PkgVerProductBenefitOptionXref> getPkgProductOprtion() {
		return pkgProductOprtion;
	}

	public void setPkgProductOprtion(
			Set<PkgVerProductBenefitOptionXref> pkgProductOprtion) {
		this.pkgProductOprtion = pkgProductOprtion;
	}

	public Set<PkgVersionProductXref> getPkgProductXref() {
		return pkgProductXref;
	}

	public void setPkgProductXref(Set<PkgVersionProductXref> pkgProductXref) {
		this.pkgProductXref = pkgProductXref;
	}

	public Integer getPkgVersion1up() {
		return pkgVersion1up;
	}

	public void setPkgVersion1up(Integer pkgVersion1up) {
		this.pkgVersion1up = pkgVersion1up;
	}

	public BenefitPackage getBenefitPackage() {
		return benefitPackage;
	}

	public void setBenefitPackage(BenefitPackage benefitPackage) {
		this.benefitPackage = benefitPackage;
	}

	public String getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}

	public PkgVersionWorkflowState getPkgVersionWorkflowState() {
		return pkgVersionWorkflowState;
	}

	public void setPkgVersionWorkflowState(
			PkgVersionWorkflowState pkgVersionWorkflowState) {
		this.pkgVersionWorkflowState = pkgVersionWorkflowState;
	}

}
