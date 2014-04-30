package com.tmg.ebscore.dto.packageversion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tmg.ebscore.dto.CoreCommonAttributes;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PackageVersionData", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "PackageVersionData", namespace = "http://www.tmg.com/coreservices/data")
public class PackageVersionData extends CoreCommonAttributes {

	@XmlElement(name = "PkgVersion1up")
	private Integer pkgVersion1up;

	@XmlElement(name = "BenifitPackage1up")
	private Integer BenifitPackage1up;

	@XmlElement(name = "VersionID")
	private String versionID;

	@XmlElement(name = "VersionName")
	private String versionName;

	@XmlElement(name = "VersionDescription")
	private String versionDescription;

	@XmlElement(name = "Workflowstate1up")
	private Integer workflowstate1up;

	@XmlElement(name = "Released")
	private Boolean released;

	@XmlElement(name = "ServiceTypeList")
	private Boolean serviceTypeList;

	@XmlElement(name = "CommonBenefit")
	private Boolean commonBenefit;

	@XmlElement(name = "BenefitSelection")
	private Boolean benefitSelection;

	@XmlElement(name = "BenefitOption")
	private Boolean benefitOption;

	public Boolean getServiceTypeList() {
		return serviceTypeList;
	}

	public void setServiceTypeList(Boolean serviceTypeList) {
		this.serviceTypeList = serviceTypeList;
	}

	public Boolean getCommonBenefit() {
		return commonBenefit;
	}

	public void setCommonBenefit(Boolean commonBenefit) {
		this.commonBenefit = commonBenefit;
	}

	public Boolean getBenefitSelection() {
		return benefitSelection;
	}

	public void setBenefitSelection(Boolean benefitSelection) {
		this.benefitSelection = benefitSelection;
	}

	public Boolean getBenefitOption() {
		return benefitOption;
	}

	public void setBenefitOption(Boolean benefitOption) {
		this.benefitOption = benefitOption;
	}

	public Integer getPkgVersion1up() {
		return pkgVersion1up;
	}

	public void setPkgVersion1up(Integer pkgVersion1up) {
		this.pkgVersion1up = pkgVersion1up;
	}

	public Integer getBenifitPackage1up() {
		return BenifitPackage1up;
	}

	public void setBenifitPackage1up(Integer benifitPackage1up) {
		BenifitPackage1up = benifitPackage1up;
	}

	public String getVersionID() {
		return versionID;
	}

	public void setVersionID(String versionID) {
		this.versionID = versionID;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getVersionDescription() {
		return versionDescription;
	}

	public void setVersionDescription(String versionDescription) {
		this.versionDescription = versionDescription;
	}

	public Integer getWorkflowstate1up() {
		return workflowstate1up;
	}

	public void setWorkflowstate1up(Integer workflowstate1up) {
		this.workflowstate1up = workflowstate1up;
	}

	public Boolean getReleased() {
		return released;
	}

	public void setReleased(Boolean released) {
		this.released = released;
	}

}
