package com.tmg.ebscore.orm.packageversion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tmg.ebscore.orm.CoreCommonAttributes;

@Entity
@Table(schema = "Pkg", name = "BenefitOptionBig3CO")
public class BenefitOptionBig3CO extends CoreCommonAttributes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BenefitOptionBig3CO1up")
	private Integer benefitOptionBig3CO1up;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PkgVersion1up")
	private PkgVersion pkgVersion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PkgVerBig3CO1up")
	private PkgVerBig3CO pkgVerBig3CO;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PkgVerBenefitOption1up")
	private PkgVerBenefitOption pkgVerBenefitOption;

	@Column(name = "IsCoveredFlag")
	private Boolean coveredFlag;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PkgVerInstanceTree1up")
	private PkgVerInstanceTree pkgVerInstanceTree;

	@Column(name = "TreatAs100PctFlag")
	private Boolean treatAs100PctFlag;

	@Column(name = "IsSETRMessageSelected")
	private Boolean sETRMessageSelected;

	public Integer getBenefitOptionBig3CO1up() {
		return benefitOptionBig3CO1up;
	}

	public void setBenefitOptionBig3CO1up(Integer benefitOptionBig3CO1up) {
		this.benefitOptionBig3CO1up = benefitOptionBig3CO1up;
	}

	public PkgVersion getPkgVersion() {
		return pkgVersion;
	}

	public void setPkgVersion(PkgVersion pkgVersion) {
		this.pkgVersion = pkgVersion;
	}

	public PkgVerBig3CO getPkgVerBig3CO() {
		return pkgVerBig3CO;
	}

	public void setPkgVerBig3CO(PkgVerBig3CO pkgVerBig3CO) {
		this.pkgVerBig3CO = pkgVerBig3CO;
	}

	public PkgVerBenefitOption getPkgVerBenefitOption() {
		return pkgVerBenefitOption;
	}

	public void setPkgVerBenefitOption(PkgVerBenefitOption pkgVerBenefitOption) {
		this.pkgVerBenefitOption = pkgVerBenefitOption;
	}

	public Boolean getCoveredFlag() {
		return coveredFlag;
	}

	public void setCoveredFlag(Boolean coveredFlag) {
		this.coveredFlag = coveredFlag;
	}

	public PkgVerInstanceTree getPkgVerInstanceTree() {
		return pkgVerInstanceTree;
	}

	public void setPkgVerInstanceTree(PkgVerInstanceTree pkgVerInstanceTree) {
		this.pkgVerInstanceTree = pkgVerInstanceTree;
	}

	public Boolean getTreatAs100PctFlag() {
		return treatAs100PctFlag;
	}

	public void setTreatAs100PctFlag(Boolean treatAs100PctFlag) {
		this.treatAs100PctFlag = treatAs100PctFlag;
	}

	public Boolean getsETRMessageSelected() {
		return sETRMessageSelected;
	}

	public void setsETRMessageSelected(Boolean sETRMessageSelected) {
		this.sETRMessageSelected = sETRMessageSelected;
	}

}
