package com.tmg.ebscore.orm.template;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tmg.ebscore.orm.masterlist.MasterListMessageVal;

@Embeddable
public class MatrixShapeMessageXrefId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TreeShape1up")
	private TreeShape treeShape;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MasterListMessageVal1up")
	private MasterListMessageVal masterListMessageVal;

	public TreeShape getTreeShape() {
		return treeShape;
	}

	public void setTreeShape(TreeShape treeShape) {
		this.treeShape = treeShape;
	}

	public MasterListMessageVal getMasterListMessageVal() {
		return masterListMessageVal;
	}

	public void setMasterListMessageVal(
			MasterListMessageVal masterListMessageVal) {
		this.masterListMessageVal = masterListMessageVal;
	}

	public int hashCode() {
		return (int) this.treeShape.hashCode()
				+ this.masterListMessageVal.hashCode();
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof MatrixShapeMessageXrefId)) {
			return false;
		}
		MatrixShapeMessageXrefId Id = (MatrixShapeMessageXrefId) obj;
		return Id.treeShape.equals(this.treeShape)
				&& Id.masterListMessageVal.equals(this.masterListMessageVal);
	}

}
