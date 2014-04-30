package com.tmg.ebscore.dto.packageversion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tmg.ebscore.dto.CoreCommonAttributes;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PackageVersionBenefitOptionData", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "PackageVersionBenefitOptionData", namespace = "http://www.tmg.com/coreservices/data")
public class PackageVersionBenefitOptionData extends CoreCommonAttributes {

	@XmlElement(name = "PkgVerBenefitOption1up")
	private Integer pkgVerBenefitOption1up;

	@XmlElement(name = "PkgVersion1up")
	private Integer pkgVersion1up;

	@XmlElement(name = "InUse")
	private Boolean inUse;

	@XmlElement(name = "BenefitOptionName")
	private String benefitOptionName;

	@XmlElement(name = "Description")
	private String description;

	public Boolean getInUse() {
		return inUse;
	}

	public void setInUse(Boolean inUse) {
		this.inUse = inUse;
	}

	public Integer getPkgVerBenefitOption1up() {
		return pkgVerBenefitOption1up;
	}

	public void setPkgVerBenefitOption1up(Integer pkgVerBenefitOption1up) {
		this.pkgVerBenefitOption1up = pkgVerBenefitOption1up;
	}

	public Integer getPkgVersion1up() {
		return pkgVersion1up;
	}

	public void setPkgVersion1up(Integer pkgVersion1up) {
		this.pkgVersion1up = pkgVersion1up;
	}

	public String getBenefitOptionName() {
		return benefitOptionName;
	}

	public void setBenefitOptionName(String benefitOptionName) {
		this.benefitOptionName = benefitOptionName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
