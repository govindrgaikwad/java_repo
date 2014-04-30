package com.tmg.ebscore.dto.ebspackage;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tmg.ebscore.dto.CoreCommonAttributes;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PkgConfigOptionFamilyData", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "PkgConfigOptionFamilyData", namespace = "http://www.tmg.com/coreservices/data")
public class PkgConfigOptionFamilyData extends CoreCommonAttributes {

	@XmlElement(name = "ConfigOptionFamily1up")
	private Integer configOptionFamily1up;

	@XmlElement(name = "PkgConfigOption1up")
	private Integer pkgConfigOption1up;

	@XmlElement(name = "BenefitPackage1up")
	private Integer benefitPackage1up;

	@XmlElement(name = "FamilyName")
	private String familyName;

	public Integer getConfigOptionFamily1up() {
		return configOptionFamily1up;
	}

	public void setConfigOptionFamily1up(Integer configOptionFamily1up) {
		this.configOptionFamily1up = configOptionFamily1up;
	}

	public Integer getPkgConfigOption1up() {
		return pkgConfigOption1up;
	}

	public void setPkgConfigOption1up(Integer pkgConfigOption1up) {
		this.pkgConfigOption1up = pkgConfigOption1up;
	}

	public Integer getBenefitPackage1up() {
		return benefitPackage1up;
	}

	public void setBenefitPackage1up(Integer benefitPackage1up) {
		this.benefitPackage1up = benefitPackage1up;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

}
