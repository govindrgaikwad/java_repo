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
@Table(schema = "Pkg", name = "MessageShapeBenefitOptionOverride")
public class MessageShapeBenefitOptionOverride extends CoreCommonAttributes {
	@EmbeddedId
	private MessageShapeBenefitOptionOverrideId messageShapeBenefitOptionOverrideId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "TreeShape1up", insertable = false, updatable = false),
			@JoinColumn(name = "PkgVerBig3CO1up", insertable = false, updatable = false),
			@JoinColumn(name = "MasterListMessageVal1up", insertable = false, updatable = false) })
	private MatrixShapeBig3COMessageXXref matrixShapeBig3COMessageXXref;

	@Column(name = "IsMappedFlag")
	private Boolean mappedFlag;

	public MatrixShapeBig3COMessageXXref getMatrixShapeBig3COMessageXXref() {
		return matrixShapeBig3COMessageXXref;
	}

	public void setMatrixShapeBig3COMessageXXref(
			MatrixShapeBig3COMessageXXref matrixShapeBig3COMessageXXref) {
		this.matrixShapeBig3COMessageXXref = matrixShapeBig3COMessageXXref;
	}

	public MessageShapeBenefitOptionOverrideId getMessageShapeBenefitOptionOverrideId() {
		return messageShapeBenefitOptionOverrideId;
	}

	public void setMessageShapeBenefitOptionOverrideId(
			MessageShapeBenefitOptionOverrideId messageShapeBenefitOptionOverrideId) {
		this.messageShapeBenefitOptionOverrideId = messageShapeBenefitOptionOverrideId;
	}

	public Boolean getMappedFlag() {
		return mappedFlag;
	}

	public void setMappedFlag(Boolean mappedFlag) {
		this.mappedFlag = mappedFlag;
	}

}
