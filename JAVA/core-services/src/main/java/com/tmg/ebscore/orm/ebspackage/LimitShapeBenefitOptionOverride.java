package com.tmg.ebscore.orm.ebspackage;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tmg.ebscore.orm.CoreCommonAttributes;

@Entity
@Table(schema = "Pkg", name = "LimitShapeBenefitOptionOverride")
public class LimitShapeBenefitOptionOverride extends CoreCommonAttributes {

	@EmbeddedId
	private LimitShapeBenefitOptionOverrideId limitShapeBenefitOptionOverrideId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "TreeShape1up", insertable = false, updatable = false),
			@JoinColumn(name = "PkgVerBig3CO1up", insertable = false, updatable = false),
			@JoinColumn(name = "MasterListLimitVal1up", insertable = false, updatable = false) })
	private MatrixShapeBig3COLimitXXref matrixShapeBig3COLimitXXref;

	@Column(name = "IsMappedFlag")
	private Boolean mappedFlag;

	@Column(name = "BenefitAmount")
	private Integer benefitAmount;

	@Column(name = "QuantityFrom")
	private Integer quantityFrom;

	@Column(name = "QuantityTo")
	private Integer quantityTo;

	@Column(name = "ReinstatementAmount")
	private Integer reinstatementAmount;

	public MatrixShapeBig3COLimitXXref getMatrixShapeBig3COLimitXXref() {
		return matrixShapeBig3COLimitXXref;
	}

	public void setMatrixShapeBig3COLimitXXref(
			MatrixShapeBig3COLimitXXref matrixShapeBig3COLimitXXref) {
		this.matrixShapeBig3COLimitXXref = matrixShapeBig3COLimitXXref;
	}

	public LimitShapeBenefitOptionOverrideId getLimitShapeBenefitOptionOverrideId() {
		return limitShapeBenefitOptionOverrideId;
	}

	public void setLimitShapeBenefitOptionOverrideId(
			LimitShapeBenefitOptionOverrideId limitShapeBenefitOptionOverrideId) {
		this.limitShapeBenefitOptionOverrideId = limitShapeBenefitOptionOverrideId;
	}

	public Boolean getMappedFlag() {
		return mappedFlag;
	}

	public void setMappedFlag(Boolean mappedFlag) {
		this.mappedFlag = mappedFlag;
	}

	public Integer getBenefitAmount() {
		return benefitAmount;
	}

	public void setBenefitAmount(Integer benefitAmount) {
		this.benefitAmount = benefitAmount;
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

	public Integer getReinstatementAmount() {
		return reinstatementAmount;
	}

	public void setReinstatementAmount(Integer reinstatementAmount) {
		this.reinstatementAmount = reinstatementAmount;
	}

}
