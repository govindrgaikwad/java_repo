package com.tmg.ebscore.orm.template;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tmg.ebscore.orm.masterlist.MasterListLimitVal;

@Embeddable
public class MatrixShapeLimitXrefId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TreeShape1up")
	private TreeShape treeShape;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MasterListLimitVal1up")
	private MasterListLimitVal masterListLimitVal;

	public TreeShape getTreeShape() {
		return treeShape;
	}

	public void setTreeShape(TreeShape treeShape) {
		this.treeShape = treeShape;
	}

	public MasterListLimitVal getMasterListLimitVal() {
		return masterListLimitVal;
	}

	public void setMasterListLimitVal(MasterListLimitVal masterListLimitVal) {
		this.masterListLimitVal = masterListLimitVal;
	}

	public int hashCode() {
		return (int) this.treeShape.hashCode()
				+ this.masterListLimitVal.hashCode();
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof TemplateAllowsLimitValId)) {
			return false;
		}
		MatrixShapeLimitXrefId Id = (MatrixShapeLimitXrefId) obj;
		return Id.masterListLimitVal.equals(this.masterListLimitVal)
				&& Id.treeShape.equals(this.treeShape);
	}

}
