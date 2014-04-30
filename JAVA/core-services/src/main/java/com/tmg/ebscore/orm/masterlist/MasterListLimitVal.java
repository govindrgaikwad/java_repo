package com.tmg.ebscore.orm.masterlist;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tmg.ebscore.orm.CoreCommonAttributes;
import com.tmg.ebscore.orm.ebspackage.LimitShapeBenefitOptionOverride;
import com.tmg.ebscore.orm.ebspackage.MatrixShapeBig3COLimitXXref;
import com.tmg.ebscore.orm.template.MatrixShapeLimitXref;
import com.tmg.ebscore.orm.template.TemplateAllowsLimitVal;

@Entity
@Table(schema = "Mast", name = "MasterListLimitVal")
public class MasterListLimitVal extends CoreCommonAttributes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MasterListLimitVal1up")
	private Integer masterListLimitVal1up;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MasterList1up")
	private MasterList masterListLimit;

	@Column(name = "LimitCategory")
	private String limitCategory;

	@Column(name = "LimitName")
	private String limitName;

	@Column(name = "LimitDescription")
	private String limitDescription;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LimitCoverageLevelVal1up")
	private MasterListStringVal masterListStringValCoverge;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LimitTimePeriodVal1up")
	private MasterListStringVal masterListStringValTimePeriod;

	@Column(name = "IsBenefitAmtInDollarsFlag")
	private Boolean benefitAmtInDollarsFlag;

	@Column(name = "IsBenefitAmtInPercentFlag")
	private Boolean benefitAmtInPercentFlag;

	@Column(name = "BenefitAmount")
	private Double benefitAmount;

	@Column(name = "ReinstatementAmount")
	private Integer reinstatementAmount;

	@Column(name = "IsAmountsSetInBenefitFlag")
	private Boolean amountsSetInBenefitFlag;

	@Column(name = "QuantityFrom")
	private Integer quantityFrom;

	@Column(name = "QuantityTo")
	private Integer quantityTo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QuantityQualifierVal1up")
	private MasterListStringVal masterListStringValQuanQualifier;

	@Column(name = "SequenceOrder")
	private Integer sequenceOrder;

	@Column(name = "IsQuantitySetInBenefitFlag")
	private Boolean quantitySetInBenefitFlag;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Accumulation1up")
	private MasterListAccumulationVal masterListAcumlationVal;

	@OneToMany(mappedBy = "matrixShapeLimitXrefId.masterListLimitVal", fetch = FetchType.LAZY)
	private Set<MatrixShapeLimitXref> masterShapeLimit = new HashSet<MatrixShapeLimitXref>();

	@OneToMany(mappedBy = "templateAllowsLimitValId.masterListLimitVal", fetch = FetchType.LAZY)
	private Set<TemplateAllowsLimitVal> templateAllowLimit = new HashSet<TemplateAllowsLimitVal>();

	@OneToMany(mappedBy = "limitShapeBenefitOptionOverrideId.matrixShapeBig3COLimitXXrefId.masterListLimitVal", fetch = FetchType.LAZY)
	private Set<LimitShapeBenefitOptionOverride> limitValOptionOverride = new HashSet<LimitShapeBenefitOptionOverride>();

	@OneToMany(mappedBy = "matrixShapeBig3COLimitXXrefId.masterListLimitVal", fetch = FetchType.LAZY)
	private Set<MatrixShapeBig3COLimitXXref> treeShapeLimitValXref = new HashSet<MatrixShapeBig3COLimitXXref>();

	public Set<MatrixShapeBig3COLimitXXref> getTreeShapeLimitValXref() {
		return treeShapeLimitValXref;
	}

	public void setTreeShapeLimitValXref(
			Set<MatrixShapeBig3COLimitXXref> treeShapeLimitValXref) {
		this.treeShapeLimitValXref = treeShapeLimitValXref;
	}

	public Set<LimitShapeBenefitOptionOverride> getLimitValOptionOverride() {
		return limitValOptionOverride;
	}

	public void setLimitValOptionOverride(
			Set<LimitShapeBenefitOptionOverride> limitValOptionOverride) {
		this.limitValOptionOverride = limitValOptionOverride;
	}

	public Set<TemplateAllowsLimitVal> getTemplateAllowLimit() {
		return templateAllowLimit;
	}

	public void setTemplateAllowLimit(
			Set<TemplateAllowsLimitVal> templateAllowLimit) {
		this.templateAllowLimit = templateAllowLimit;
	}

	public Set<MatrixShapeLimitXref> getMasterShapeLimit() {
		return masterShapeLimit;
	}

	public void setMasterShapeLimit(Set<MatrixShapeLimitXref> masterShapeLimit) {
		this.masterShapeLimit = masterShapeLimit;
	}

	public Integer getMasterListLimitVal1up() {
		return masterListLimitVal1up;
	}

	public void setMasterListLimitVal1up(Integer masterListLimitVal1up) {
		this.masterListLimitVal1up = masterListLimitVal1up;
	}

	public MasterList getMasterListLimit() {
		return masterListLimit;
	}

	public void setMasterListLimit(MasterList masterListLimit) {
		this.masterListLimit = masterListLimit;
	}

	public String getLimitCategory() {
		return limitCategory;
	}

	public void setLimitCategory(String limitCategory) {
		this.limitCategory = limitCategory;
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

	public MasterListStringVal getMasterListStringValCoverge() {
		return masterListStringValCoverge;
	}

	public void setMasterListStringValCoverge(
			MasterListStringVal masterListStringValCoverge) {
		this.masterListStringValCoverge = masterListStringValCoverge;
	}

	public MasterListStringVal getMasterListStringValTimePeriod() {
		return masterListStringValTimePeriod;
	}

	public void setMasterListStringValTimePeriod(
			MasterListStringVal masterListStringValTimePeriod) {
		this.masterListStringValTimePeriod = masterListStringValTimePeriod;
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

	public MasterListStringVal getMasterListStringValQuanQualifier() {
		return masterListStringValQuanQualifier;
	}

	public void setMasterListStringValQuanQualifier(
			MasterListStringVal masterListStringValQuanQualifier) {
		this.masterListStringValQuanQualifier = masterListStringValQuanQualifier;
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

	public MasterListAccumulationVal getMasterListAcumlationVal() {
		return masterListAcumlationVal;
	}

	public void setMasterListAcumlationVal(
			MasterListAccumulationVal masterListAcumlationVal) {
		this.masterListAcumlationVal = masterListAcumlationVal;
	}

}
