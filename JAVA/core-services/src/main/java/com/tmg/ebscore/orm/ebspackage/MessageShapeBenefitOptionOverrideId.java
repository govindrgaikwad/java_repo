package com.tmg.ebscore.orm.ebspackage;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tmg.ebscore.orm.packageversion.PkgVerBenefitOption;

@Embeddable
public class MessageShapeBenefitOptionOverrideId implements Serializable {

	private static final long serialVersionUID = 1L;

	private MatrixShapeBig3COMessageXXrefId matrixShapeBig3COMessageXXrefId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PkgVerBenefitOption1up")
	private PkgVerBenefitOption pkgVerBenefitOption;

	public PkgVerBenefitOption getPkgVerBenefitOption() {
		return pkgVerBenefitOption;
	}

	public void setPkgVerBenefitOption(PkgVerBenefitOption pkgVerBenefitOption) {
		this.pkgVerBenefitOption = pkgVerBenefitOption;
	}

	public MatrixShapeBig3COMessageXXrefId getMatrixShapeBig3COMessageXXrefId() {
		return matrixShapeBig3COMessageXXrefId;
	}

	public void setMatrixShapeBig3COMessageXXrefId(
			MatrixShapeBig3COMessageXXrefId matrixShapeBig3COMessageXXrefId) {
		this.matrixShapeBig3COMessageXXrefId = matrixShapeBig3COMessageXXrefId;
	}

	public int hashCode() {
		return (int) this.matrixShapeBig3COMessageXXrefId.hashCode()
				+ this.pkgVerBenefitOption.hashCode();
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof MessageShapeBenefitOptionOverrideId)) {
			return false;
		}
		MessageShapeBenefitOptionOverrideId Id = (MessageShapeBenefitOptionOverrideId) obj;
		return Id.matrixShapeBig3COMessageXXrefId
				.equals(this.matrixShapeBig3COMessageXXrefId)
				&& Id.pkgVerBenefitOption.equals(this.pkgVerBenefitOption);
	}

}
