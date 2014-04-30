package com.tmg.ebscore.dto.packageversion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tmg.ebscore.dto.CoreCommonAttributes;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BenefitOptionBig3COData", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "BenefitOptionBig3COData", namespace = "http://www.tmg.com/coreservices/data")
public class BenefitOptionBig3COData extends CoreCommonAttributes {

	@XmlElement(name = "BenefitOptionBig3CO1up")
	private Integer benefitOptionBig3CO1up;

	@XmlElement(name = "PkgVersion1up")
	private Integer pkgVersion1up;

	@XmlElement(name = "PkgVerBig3CO1up")
	private Integer pkgVerBig3CO1up;

	@XmlElement(name = "PkgMasterBig3CO1up")
	private Integer pkgMasterBig3CO1up;

	@XmlElement(name = "MasterServiceDefn1up")
	private Integer masterServiceDefn1up;

	@XmlElement(name = "MasterStringVal1up")
	private Integer masterStringVal1up;

	@XmlElement(name = "PkgVerBenefitOption1up")
	private Integer pkgVerBenefitOption1up;

	@XmlElement(name = "PkgVerInstanceTree1up")
	private Integer pkgVerInstanceTree1up;

	@XmlElement(name = "Enable")
	private Boolean enable;

	@XmlElement(name = "PkgVersionCoveredFlag")
	private Boolean pkgVersionCoveredFlag;

	@XmlElement(name = "PkgVersionTreatAs100PctFlag")
	private Boolean pkgVersionTreaAs100PctFlag;

	@XmlElement(name = "BenefitOptionCoveredFlag")
	private Boolean benefitOptionCoveredFlag;

	@XmlElement(name = "BenefitOptionTreatAs100PctFlag")
	private Boolean benefitOptionTreatAs100PctFlag;

	@XmlElement(name = "BenefitOptionInstanceTree1up")
	private Integer benefitOptionInstanceTree1up;

	@XmlElement(name = "IsSETRMessageSelected")
	private Boolean sETRMessageSelected;

	public Integer getMasterStringVal1up() {
		return masterStringVal1up;
	}

	public void setMasterStringVal1up(Integer masterStringVal1up) {
		this.masterStringVal1up = masterStringVal1up;
	}

	public Integer getPkgMasterBig3CO1up() {
		return pkgMasterBig3CO1up;
	}

	public void setPkgMasterBig3CO1up(Integer pkgMasterBig3CO1up) {
		this.pkgMasterBig3CO1up = pkgMasterBig3CO1up;
	}

	public Integer getMasterServiceDefn1up() {
		return masterServiceDefn1up;
	}

	public void setMasterServiceDefn1up(Integer masterServiceDefn1up) {
		this.masterServiceDefn1up = masterServiceDefn1up;
	}

	public Integer getBenefitOptionBig3CO1up() {
		return benefitOptionBig3CO1up;
	}

	public void setBenefitOptionBig3CO1up(Integer benefitOptionBig3CO1up) {
		this.benefitOptionBig3CO1up = benefitOptionBig3CO1up;
	}

	public Integer getPkgVersion1up() {
		return pkgVersion1up;
	}

	public void setPkgVersion1up(Integer pkgVersion1up) {
		this.pkgVersion1up = pkgVersion1up;
	}

	public Integer getPkgVerBig3CO1up() {
		return pkgVerBig3CO1up;
	}

	public void setPkgVerBig3CO1up(Integer pkgVerBig3CO1up) {
		this.pkgVerBig3CO1up = pkgVerBig3CO1up;
	}

	public Integer getPkgVerBenefitOption1up() {
		return pkgVerBenefitOption1up;
	}

	public void setPkgVerBenefitOption1up(Integer pkgVerBenefitOption1up) {
		this.pkgVerBenefitOption1up = pkgVerBenefitOption1up;
	}

	public Integer getPkgVerInstanceTree1up() {
		return pkgVerInstanceTree1up;
	}

	public void setPkgVerInstanceTree1up(Integer pkgVerInstanceTree1up) {
		this.pkgVerInstanceTree1up = pkgVerInstanceTree1up;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Boolean getPkgVersionCoveredFlag() {
		return pkgVersionCoveredFlag;
	}

	public void setPkgVersionCoveredFlag(Boolean pkgVersionCoveredFlag) {
		this.pkgVersionCoveredFlag = pkgVersionCoveredFlag;
	}

	public Boolean getPkgVersionTreaAs100PctFlag() {
		return pkgVersionTreaAs100PctFlag;
	}

	public void setPkgVersionTreaAs100PctFlag(Boolean pkgVersionTreaAs100PctFlag) {
		this.pkgVersionTreaAs100PctFlag = pkgVersionTreaAs100PctFlag;
	}

	public Boolean getBenefitOptionCoveredFlag() {
		return benefitOptionCoveredFlag;
	}

	public void setBenefitOptionCoveredFlag(Boolean benefitOptionCoveredFlag) {
		this.benefitOptionCoveredFlag = benefitOptionCoveredFlag;
	}

	public Boolean getBenefitOptionTreatAs100PctFlag() {
		return benefitOptionTreatAs100PctFlag;
	}

	public void setBenefitOptionTreatAs100PctFlag(
			Boolean benefitOptionTreatAs100PctFlag) {
		this.benefitOptionTreatAs100PctFlag = benefitOptionTreatAs100PctFlag;
	}

	public Integer getBenefitOptionInstanceTree1up() {
		return benefitOptionInstanceTree1up;
	}

	public void setBenefitOptionInstanceTree1up(
			Integer benefitOptionInstanceTree1up) {
		this.benefitOptionInstanceTree1up = benefitOptionInstanceTree1up;
	}

	public Boolean getsETRMessageSelected() {
		return sETRMessageSelected;
	}

	public void setsETRMessageSelected(Boolean sETRMessageSelected) {
		this.sETRMessageSelected = sETRMessageSelected;
	}

}
