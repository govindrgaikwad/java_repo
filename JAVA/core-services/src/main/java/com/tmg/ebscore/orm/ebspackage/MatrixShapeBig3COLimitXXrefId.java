package com.tmg.ebscore.orm.ebspackage;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tmg.ebscore.orm.masterlist.MasterListLimitVal;

@Embeddable
public class MatrixShapeBig3COLimitXXrefId implements Serializable {

	private static final long serialVersionUID = 1L;

	private MatrixShapeBig3COXrefId matrixShapeBig3COXrefId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MasterListLimitVal1up")
	private MasterListLimitVal masterListLimitVal;

	public MasterListLimitVal getMasterListLimitVal() {
		return masterListLimitVal;
	}

	public void setMasterListLimitVal(MasterListLimitVal masterListLimitVal) {
		this.masterListLimitVal = masterListLimitVal;
	}

	public MatrixShapeBig3COXrefId getMatrixShapeBig3COXrefId() {
		return matrixShapeBig3COXrefId;
	}

	public void setMatrixShapeBig3COXrefId(
			MatrixShapeBig3COXrefId matrixShapeBig3COXrefId) {
		this.matrixShapeBig3COXrefId = matrixShapeBig3COXrefId;
	}

	public int hashCode() {
		return (int) this.matrixShapeBig3COXrefId.hashCode()
				+ this.masterListLimitVal.hashCode();
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof MatrixShapeBig3COLimitXXrefId)) {
			return false;
		}
		MatrixShapeBig3COLimitXXrefId Id = (MatrixShapeBig3COLimitXXrefId) obj;
		return Id.matrixShapeBig3COXrefId.equals(this.matrixShapeBig3COXrefId)

		&& Id.masterListLimitVal.equals(this.masterListLimitVal);
	}

}
