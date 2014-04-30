package com.tmg.ebscore.dto.masterlist;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tmg.ebscore.dto.CoreCommonAttributes;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MasterListLimitValData", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "MasterListLimitValData", namespace = "http://www.tmg.com/coreservices/data")
public class MasterListLimitValData extends CoreCommonAttributes {

	@XmlElement(name = "MasterListLimitValId")
	private Integer masterListLimitValId;

	@XmlElement(name = "MasterList1Up")
	private Integer masterList1Up;

	@XmlElement(name = "LimitName")
	private String limitName;

	@XmlElement(name = "LimitDescription")
	private String limitDescription;

	@XmlElement(name = "LimitCategory")
	private String limitCategory;

	@XmlElement(name = "CoverageLevel")
	private Integer coverageLevel;

	@XmlElement(name = "TimePeriod")
	private Integer timePeriod;

	@XmlElement(name = "QuantityFrom")
	private Integer quantityFrom;

	@XmlElement(name = "QuantityTo")
	private Integer quantityTo;

	@XmlElement(name = "QuantityQualifier")
	private Integer quantityQualifier;

	@XmlElement(name = "BenefitAmtInDollarsFlag")
	private Boolean benefitAmtInDollarsFlag;

	@XmlElement(name = "BenefitAmtInPercentFlag")
	private Boolean benefitAmtInPercentFlag;

	@XmlElement(name = "BenefitAmount")
	private Double benefitAmount;

	@XmlElement(name = "ReinstatementAmount")
	private Integer reinstatementAmount;

	@XmlElement(name = "IsAmountsSetInBenefitFlag")
	private Boolean amountsSetInBenefitFlag;

	@XmlElement(name = "SequenceOrder")
	private Integer sequenceOrder;

	@XmlElement(name = "IsQuantitySetInBenefitFlag")
	private Boolean quantitySetInBenefitFlag;

	@XmlElement(name = "Accumulation1up")
	private Integer accumulation1up;

	@XmlElement(name = "InUse")
	private Boolean inUse;

	@XmlElement(name = "IsLocked")
	private Boolean isLocked;

	public Boolean getInUse() {
		return inUse;
	}

	public void setInUse(Boolean inUse) {
		this.inUse = inUse;
	}

	public Boolean getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}

	public Integer getMasterListLimitValId() {
		return masterListLimitValId;
	}

	public void setMasterListLimitValId(Integer masterListLimitValId) {
		this.masterListLimitValId = masterListLimitValId;
	}

	public Integer getMasterList1Up() {
		return masterList1Up;
	}

	public void setMasterList1Up(Integer masterList1Up) {
		this.masterList1Up = masterList1Up;
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

	public Integer getCoverageLevel() {
		return coverageLevel;
	}

	public void setCoverageLevel(Integer coverageLevel) {
		this.coverageLevel = coverageLevel;
	}

	public Integer getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(Integer timePeriod) {
		this.timePeriod = timePeriod;
	}

	public Integer getQuantityQualifier() {
		return quantityQualifier;
	}

	public void setQuantityQualifier(Integer quantityQualifier) {
		this.quantityQualifier = quantityQualifier;
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

	public Boolean getBenefitAmtInDollarsFlag() {
		return benefitAmtInDollarsFlag;
	}

	public void setBenefitAmtInDollarsFlag(Boolean benefitAmtInDollarsFlag) {
		this.benefitAmtInDollarsFlag = benefitAmtInDollarsFlag;
	}

	public Boolean getBenefitAmtInPercentFlag() {
		return benefitAmtInPercentFlag;
	}

	public void setBenefitAmtInPercentFlag(Boolean benefitAmtInPercentFlag) {
		this.benefitAmtInPercentFlag = benefitAmtInPercentFlag;
	}

	public Double getBenefitAmount() {
		return benefitAmount;
	}

	public void setBenefitAmount(Double benefitAmount) {
		this.benefitAmount = benefitAmount;
	}

	public Integer getReinstatementAmount() {
		return reinstatementAmount;
	}

	public void setReinstatementAmount(Integer reinstatementAmount) {
		this.reinstatementAmount = reinstatementAmount;
	}

	public Boolean getAmountsSetInBenefitFlag() {
		return amountsSetInBenefitFlag;
	}

	public void setAmountsSetInBenefitFlag(Boolean amountsSetInBenefitFlag) {
		this.amountsSetInBenefitFlag = amountsSetInBenefitFlag;
	}

	public Integer getSequenceOrder() {
		return sequenceOrder;
	}

	public void setSequenceOrder(Integer sequenceOrder) {
		this.sequenceOrder = sequenceOrder;
	}

	public Boolean getQuantitySetInBenefitFlag() {
		return quantitySetInBenefitFlag;
	}

	public void setQuantitySetInBenefitFlag(Boolean quantitySetInBenefitFlag) {
		this.quantitySetInBenefitFlag = quantitySetInBenefitFlag;
	}

	public Integer getAccumulation1up() {
		return accumulation1up;
	}

	public void setAccumulation1up(Integer accumulation1up) {
		this.accumulation1up = accumulation1up;
	}

}
