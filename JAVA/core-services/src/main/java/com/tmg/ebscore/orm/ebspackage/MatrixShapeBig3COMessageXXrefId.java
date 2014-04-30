package com.tmg.ebscore.orm.ebspackage;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tmg.ebscore.orm.masterlist.MasterListMessageVal;

@Embeddable
public class MatrixShapeBig3COMessageXXrefId implements Serializable {

	private static final long serialVersionUID = 1L;

	private MatrixShapeBig3COXrefId matrixShapeBig3COXrefId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MasterListMessageVal1up")
	private MasterListMessageVal masterListMessageVal;

	public MatrixShapeBig3COXrefId getMatrixShapeBig3COXrefId() {
		return matrixShapeBig3COXrefId;
	}

	public void setMatrixShapeBig3COXrefId(
			MatrixShapeBig3COXrefId matrixShapeBig3COXrefId) {
		this.matrixShapeBig3COXrefId = matrixShapeBig3COXrefId;
	}

	public MasterListMessageVal getMasterListMessageVal() {
		return masterListMessageVal;
	}

	public void setMasterListMessageVal(
			MasterListMessageVal masterListMessageVal) {
		this.masterListMessageVal = masterListMessageVal;
	}

	public int hashCode() {
		return (int) this.matrixShapeBig3COXrefId.hashCode()
				+ this.masterListMessageVal.hashCode();
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof MatrixShapeBig3COMessageXXrefId)) {
			return false;
		}
		MatrixShapeBig3COMessageXXrefId Id = (MatrixShapeBig3COMessageXXrefId) obj;
		return Id.matrixShapeBig3COXrefId.equals(this.matrixShapeBig3COXrefId)
				&& Id.masterListMessageVal.equals(this.masterListMessageVal);
	}

}
