package com.tmg.ebscore.dto.template;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tmg.ebscore.dto.CoreCommonAttributes;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TemplateAllowsLimitValData", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "TemplateAllowsLimitValData", namespace = "http://www.tmg.com/coreservices/data")
public class TemplateAllowsLimitValData extends CoreCommonAttributes {

	@XmlElement(name = "InUse")
	private Boolean inUse;

	@XmlElement(name = "Locked")
	private Boolean locked;

	@XmlElement(name = "Allowed")
	private Boolean allowed;

	@XmlElement(name = "LimitName")
	private String limitName;

	@XmlElement(name = "LimitDescription")
	private String limitDescription;

	@XmlElement(name = "LimitCategory")
	private String limitCategory;

	@XmlElement(name = "CoverageLevel")
	private String coverageLevel;

	@XmlElement(name = "TimePeriod")
	private String timePeriod;

	@XmlElement(name = "QuantityFrom")
	private Integer quantityFrom;

	@XmlElement(name = "QuantityTo")
	private Integer quantityTo;

	@XmlElement(name = "QuantityQualifier")
	private String quantityQualifier;

	@XmlElement(name = "BenefitAmount")
	private Double benefitAmount;

	@XmlElement(name = "PackageTemplate1Up")
	private Integer packageTemplate1Up;

	@XmlElement(name = "MasterListLimitVal1up")
	private Integer masterListLimitVal1up;

	public Integer getPackageTemplate1Up() {
		return packageTemplate1Up;
	}

	public void setPackageTemplate1Up(Integer packageTemplate1Up) {
		this.packageTemplate1Up = packageTemplate1Up;
	}

	public Integer getMasterListLimitVal1up() {
		return masterListLimitVal1up;
	}

	public void setMasterListLimitVal1up(Integer masterListLimitVal1up) {
		this.masterListLimitVal1up = masterListLimitVal1up;
	}

	public Boolean getInUse() {
		return inUse;
	}

	public void setInUse(Boolean inUse) {
		this.inUse = inUse;
	}

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public Boolean getAllowed() {
		return allowed;
	}

	public void setAllowed(Boolean allowed) {
		this.allowed = allowed;
	}

	public String getLimitName() {
		return limitName;
	}

	public void setLimitName(String limitName) {
		this.limitName = limitName;
	}

	public String getLimitDescription() {
		return limitDescription;
	}

	public void setLimitDescription(String limitDescription) {
		this.limitDescription = limitDescription;
	}

	public String getLimitCategory() {
		return limitCategory;
	}

	public void setLimitCategory(String limitCategory) {
		this.limitCategory = limitCategory;
	}

	public Integer getQuantityFrom() {
		return quantityFrom;
	}

	public void setQuantityFrom(Integer quantityFrom) {
		this.quantityFrom = quantityFrom;
	}

	public Integer getQuantityTo() {
		return quantityTo;
	}

	public void setQuantityTo(Integer quantityTo) {
		this.quantityTo = quantityTo;
	}

	public String getCoverageLevel() {
		return coverageLevel;
	}

	public void setCoverageLevel(String coverageLevel) {
		this.coverageLevel = coverageLevel;
	}

	public String getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(String timePeriod) {
		this.timePeriod = timePeriod;
	}

	public String getQuantityQualifier() {
		return quantityQualifier;
	}

	public void setQuantityQualifier(String quantityQualifier) {
		this.quantityQualifier = quantityQualifier;
	}

	public Double getBenefitAmount() {
		return benefitAmount;
	}

	public void setBenefitAmount(Double benefitAmount) {
		this.benefitAmount = benefitAmount;
	}

}
