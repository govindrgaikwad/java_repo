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
@Table(schema = "Pkg", name = "MatrixShapeBig3COMessageXXref")
public class MatrixShapeBig3COMessageXXref extends CoreCommonAttributes {
	@EmbeddedId
	private MatrixShapeBig3COMessageXXrefId matrixShapeBig3COMessageXXrefId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "TreeShape1up", insertable = false, updatable = false),
			@JoinColumn(name = "PkgVerBig3CO1up", insertable = false, updatable = false) })
	private MatrixShapeBig3COXref matrixShapeBig3COXref;

	@OneToMany(mappedBy = "matrixShapeBig3COMessageXXref", fetch = FetchType.LAZY)
	private Set<MessageShapeBenefitOptionOverride> big3coMessageOptionOverride = new HashSet<MessageShapeBenefitOptionOverride>();

	@Column(name = "IsMappedFlag")
	private Boolean mappedFlag;

	public MatrixShapeBig3COXref getMatrixShapeBig3COXref() {
		return matrixShapeBig3COXref;
	}

	public void setMatrixShapeBig3COXref(
			MatrixShapeBig3COXref matrixShapeBig3COXref) {
		this.matrixShapeBig3COXref = matrixShapeBig3COXref;
	}

	public Set<MessageShapeBenefitOptionOverride> getBig3coMessageOptionOverride() {
		return big3coMessageOptionOverride;
	}

	public void setBig3coMessageOptionOverride(
			Set<MessageShapeBenefitOptionOverride> big3coMessageOptionOverride) {
		this.big3coMessageOptionOverride = big3coMessageOptionOverride;
	}

	public MatrixShapeBig3COMessageXXrefId getMatrixShapeBig3COMessageXXrefId() {
		return matrixShapeBig3COMessageXXrefId;
	}

	public void setMatrixShapeBig3COMessageXXrefId(
			MatrixShapeBig3COMessageXXrefId matrixShapeBig3COMessageXXrefId) {
		this.matrixShapeBig3COMessageXXrefId = matrixShapeBig3COMessageXXrefId;
	}

	public Boolean getMappedFlag() {
		return mappedFlag;
	}

	public void setMappedFlag(Boolean mappedFlag) {
		this.mappedFlag = mappedFlag;
	}

}
