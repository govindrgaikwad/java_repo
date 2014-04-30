package com.tmg.ebscore.orm.ebspackage;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tmg.ebscore.orm.CoreCommonAttributes;

@Entity
@Table(schema = "Pkg", name = "MatrixShapeBig3COLimitXXref")
public class MatrixShapeBig3COLimitXXref extends CoreCommonAttributes {

	@EmbeddedId
	private MatrixShapeBig3COLimitXXrefId matrixShapeBig3COLimitXXrefId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "TreeShape1up", insertable = false, updatable = false),
			@JoinColumn(name = "PkgVerBig3CO1up", insertable = false, updatable = false) })
	private MatrixShapeBig3COXref matrixShapeBig3COXref;

	@OneToMany(mappedBy = "matrixShapeBig3COLimitXXref", fetch = FetchType.LAZY)
	private Set<LimitShapeBenefitOptionOverride> big3coLimitOptionOverride = new HashSet<LimitShapeBenefitOptionOverride>();

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

	public Set<LimitShapeBenefitOptionOverride> getBig3coLimitOptionOverride() {
		return big3coLimitOptionOverride;
	}

	public void setBig3coLimitOptionOverride(
			Set<LimitShapeBenefitOptionOverride> big3coLimitOptionOverride) {
		this.big3coLimitOptionOverride = big3coLimitOptionOverride;
	}

	public MatrixShapeBig3COXref getMatrixShapeBig3COXref() {
		return matrixShapeBig3COXref;
	}

	public void setMatrixShapeBig3COXref(
			MatrixShapeBig3COXref matrixShapeBig3COXref) {
		this.matrixShapeBig3COXref = matrixShapeBig3COXref;
	}

	public MatrixShapeBig3COLimitXXrefId getMatrixShapeBig3COLimitXXrefId() {
		return matrixShapeBig3COLimitXXrefId;
	}

	public void setMatrixShapeBig3COLimitXXrefId(
			MatrixShapeBig3COLimitXXrefId matrixShapeBig3COLimitXXrefId) {
		this.matrixShapeBig3COLimitXXrefId = matrixShapeBig3COLimitXXrefId;
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
