package com.tmg.ebscore.dto.ebspackage;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tmg.ebscore.dto.CoreCommonAttributes;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BenifitPackageData", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "BenifitPackageData", namespace = "http://www.tmg.com/coreservices/data")
public class BenifitPackageData extends CoreCommonAttributes {

	@XmlElement(name = "PackageTemplate1up")
	private Integer packageTemplate1up;

	@XmlElement(name = "BenefitPackage1up")
	private Integer benefitPackage1up;

	@XmlElement(name = "PackageId")
	private String packageId;

	@XmlElement(name = "PackageName")
	private String packageName;

	@XmlElement(name = "PackageDescription")
	private String packageDescription;

	@XmlElement(name = "NoOfVersions")
	private Integer noOfVersions;

	@XmlElement(name = "ServiceTypeListFlag")
	private Boolean serviceTypeListFlag;

	@XmlElement(name = "CommonBenefitsFlag")
	private Boolean commonBenefitsFlag;

	@XmlElement(name = "benefitOptionsFlag")
	private Boolean benefitOptionsFlag;

	public Integer getPackageTemplate1up() {
		return packageTemplate1up;
	}

	public void setPackageTemplate1up(Integer packageTemplate1up) {
		this.packageTemplate1up = packageTemplate1up;
	}

	public Integer getBenefitPackage1up() {
		return benefitPackage1up;
	}

	public void setBenefitPackage1up(Integer benefitPackage1up) {
		this.benefitPackage1up = benefitPackage1up;
	}

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getPackageDescription() {
		return packageDescription;
	}

	public void setPackageDescription(String packageDescription) {
		this.packageDescription = packageDescription;
	}

	public Integer getNoOfVersions() {
		return noOfVersions;
	}

	public void setNoOfVersions(Integer noOfVersions) {
		this.noOfVersions = noOfVersions;
	}

}
