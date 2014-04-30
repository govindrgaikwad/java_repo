package com.tmg.ebscore.orm.ebspackage;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tmg.ebscore.orm.packageversion.PkgVerBenefitOption;

@Embeddable
public class LimitShapeBenefitOptionOverrideId implements Serializable {

	private static final long serialVersionUID = 1L;

	private MatrixShapeBig3COLimitXXrefId matrixShapeBig3COLimitXXrefId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PkgVerBenefitOption1up")
	private PkgVerBenefitOption pkgVerBenefitOption;

	public MatrixShapeBig3COLimitXXrefId getMatrixShapeBig3COLimitXXrefId() {
		return matrixShapeBig3COLimitXXrefId;
	}

	public void setMatrixShapeBig3COLimitXXrefId(
			MatrixShapeBig3COLimitXXrefId matrixShapeBig3COLimitXXrefId) {
		this.matrixShapeBig3COLimitXXrefId = matrixShapeBig3COLimitXXrefId;
	}

	public PkgVerBenefitOption getPkgVerBenefitOption() {
		return pkgVerBenefitOption;
	}

	public void setPkgVerBenefitOption(PkgVerBenefitOption pkgVerBenefitOption) {
		this.pkgVerBenefitOption = pkgVerBenefitOption;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof LimitShapeBenefitOptionOverrideId)) {
			return false;
		}
		LimitShapeBenefitOptionOverrideId Id = (LimitShapeBenefitOptionOverrideId) obj;
		return Id.matrixShapeBig3COLimitXXrefId
				.equals(this.matrixShapeBig3COLimitXXrefId)
				&& Id.pkgVerBenefitOption.equals(this.pkgVerBenefitOption);
	}

}
