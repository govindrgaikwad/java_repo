package com.tmg.ebscore.dto.ebspackage;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tmg.ebscore.dto.CoreCommonAttributes;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PackageSummaryData", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "PackageSummaryData", namespace = "http://www.tmg.com/coreservices/data")
public class PackageSummaryData extends CoreCommonAttributes {

	@XmlElement(name = "PackageTemplate1up")
	private Integer packageTemplate1up;

	@XmlElement(name = "BenefitPackage1up")
	private Integer benefitPackage1up;

	@XmlElement(name = "TemplateID")
	private String templateID;

	@XmlElement(name = "TemplateName")
	private String templateName;

	@XmlElement(name = "PackageId")
	private String packageId;

	@XmlElement(name = "PackageName")
	private String packageName;

	@XmlElement(name = "BenefitPackageTypeAsString1up")
	private Integer benefitPackageTypeAsString1up;

	@XmlElement(name = "NoOfPackageVersions")
	private Integer noOfPackageVersions;

	@XmlElement(name = "NoOfReleasedPackageVersions")
	private Integer noOfReleasedPackageVersions;

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

	public String getTemplateID() {
		return templateID;
	}

	public void setTemplateID(String templateID) {
		this.templateID = templateID;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
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

	public Integer getBenefitPackageTypeAsString1up() {
		return benefitPackageTypeAsString1up;
	}

	public void setBenefitPackageTypeAsString1up(
			Integer benefitPackageTypeAsString1up) {
		this.benefitPackageTypeAsString1up = benefitPackageTypeAsString1up;
	}

	public Integer getNoOfPackageVersions() {
		return noOfPackageVersions;
	}

	public void setNoOfPackageVersions(Integer noOfPackageVersions) {
		this.noOfPackageVersions = noOfPackageVersions;
	}

	public Integer getNoOfReleasedPackageVersions() {
		return noOfReleasedPackageVersions;
	}

	public void setNoOfReleasedPackageVersions(
			Integer noOfReleasedPackageVersions) {
		this.noOfReleasedPackageVersions = noOfReleasedPackageVersions;
	}

}
