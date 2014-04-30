package com.tmg.ebscore.orm.ebspackage;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tmg.ebscore.orm.packageversion.PkgVerBig3CO;
import com.tmg.ebscore.orm.template.TreeShape;

@Embeddable
public class MatrixShapeBig3COXrefId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TreeShape1up")
	private TreeShape treeShape;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PkgVerBig3CO1up")
	private PkgVerBig3CO pkgVerBig3CO;

	public TreeShape getTreeShape() {
		return treeShape;
	}

	public void setTreeShape(TreeShape treeShape) {
		this.treeShape = treeShape;
	}

	public PkgVerBig3CO getPkgVerBig3CO() {
		return pkgVerBig3CO;
	}

	public void setPkgVerBig3CO(PkgVerBig3CO pkgVerBig3CO) {
		this.pkgVerBig3CO = pkgVerBig3CO;
	}
	
	public int hashCode() {
		return (int) this.treeShape.hashCode() + this.pkgVerBig3CO.hashCode();
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof MatrixShapeBig3COXrefId)) {
			return false;
		}
		MatrixShapeBig3COXrefId Id = (MatrixShapeBig3COXrefId) obj;
		return Id.treeShape.equals(this.treeShape)
				&& Id.pkgVerBig3CO.equals(this.pkgVerBig3CO);
	}

}
