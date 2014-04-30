package com.tmg.ebscore.dto.ebspackage;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tmg.ebscore.dto.CoreCommonAttributes;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PkgConfigOptionData", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "PkgConfigOptionData", namespace = "http://www.tmg.com/coreservices/data")
public class PkgConfigOptionData extends CoreCommonAttributes {

	@XmlElement(name = "PkgConfigOption1up")
	private Integer pkgConfigOption1up;

	@XmlElement(name = "ConfigOptionFamily1up")
	private Integer configOptionFamily1up;

	@XmlElement(name = "EffectiveDate")
	private Date effectiveDate;

	@XmlElement(name = "SalesCancelDate1")
	private Date salesCancelDate1;

	@XmlElement(name = "ClaimsEffectiveDate")
	private Date claimsEffectiveDate;

	@XmlElement(name = "ClaimsCancelDate")
	private Date claimsCancelDate;

	@XmlElement(name = "AllowUse")
	private Boolean allowUse;

	@XmlElement(name = "PackageVersion")
	private Integer packageVersion;

	@XmlElement(name = "BenefitOption")
	private List<Integer> benefitOption;

	@XmlElement(name = "WorkflowState")
	private Integer workflowState;

	@XmlElement(name = "Description")
	private String description;

	@XmlElement(name = "MarketSegment")
	private Integer marketSegment;

	@XmlElement(name = "BenefitOfferingName")
	private String benefitOfferingName;

	@XmlElement(name = "FamilyName")
	private String familyName;

	public String getBenefitOfferingName() {
		return benefitOfferingName;
	}

	public void setBenefitOfferingName(String benefitOfferingName) {
		this.benefitOfferingName = benefitOfferingName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public Integer getMarketSegment() {
		return marketSegment;
	}

	public void setMarketSegment(Integer marketSegment) {
		this.marketSegment = marketSegment;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPackageVersion() {
		return packageVersion;
	}

	public void setPackageVersion(Integer packageVersion) {
		this.packageVersion = packageVersion;
	}

	public List<Integer> getBenefitOption() {
		return benefitOption;
	}

	public void setBenefitOption(List<Integer> benefitOption) {
		this.benefitOption = benefitOption;
	}

	public Integer getWorkflowState() {
		return workflowState;
	}

	public void setWorkflowState(Integer workflowState) {
		this.workflowState = workflowState;
	}

	public Integer getPkgConfigOption1up() {
		return pkgConfigOption1up;
	}

	public void setPkgConfigOption1up(Integer pkgConfigOption1up) {
		this.pkgConfigOption1up = pkgConfigOption1up;
	}

	public Integer getConfigOptionFamily1up() {
		return configOptionFamily1up;
	}

	public void setConfigOptionFamily1up(Integer configOptionFamily1up) {
		this.configOptionFamily1up = configOptionFamily1up;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getSalesCancelDate1() {
		return salesCancelDate1;
	}

	public void setSalesCancelDate1(Date salesCancelDate1) {
		this.salesCancelDate1 = salesCancelDate1;
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

	public Boolean getAllowUse() {
		return allowUse;
	}

	public void setAllowUse(Boolean allowUse) {
		this.allowUse = allowUse;
	}

}
