package com.tmg.ebscore.orm.ebspackage;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tmg.ebscore.orm.packageversion.PkgVerBig3CO;
import com.tmg.ebscore.orm.packageversion.PkgVerInstanceTree;
import com.tmg.ebscore.orm.template.TreeShape;

@Embeddable
public class ShapeValueBig3COId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TreeShape1up")
	private TreeShape treeShape;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PkgVerInstanceTree1up")
	private PkgVerInstanceTree pkgVerInstanceTree;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PkgVerBig3CO1up")
	private PkgVerBig3CO pkgVerBig3CO;

	public TreeShape getTreeShape() {
		return treeShape;
	}

	public void setTreeShape(TreeShape treeShape) {
		this.treeShape = treeShape;
	}

	public PkgVerInstanceTree getPkgVerInstanceTree() {
		return pkgVerInstanceTree;
	}

	public void setPkgVerInstanceTree(PkgVerInstanceTree pkgVerInstanceTree) {
		this.pkgVerInstanceTree = pkgVerInstanceTree;
	}

	public PkgVerBig3CO getPkgVerBig3CO() {
		return pkgVerBig3CO;
	}

	public void setPkgVerBig3CO(PkgVerBig3CO pkgVerBig3CO) {
		this.pkgVerBig3CO = pkgVerBig3CO;
	}
	
	public int hashCode() {
		return (int) this.treeShape.hashCode() + this.pkgVerBig3CO.hashCode()
				+ this.pkgVerInstanceTree.hashCode();
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof ShapeValueBig3COId)) {
			return false;
		}
		ShapeValueBig3COId Id = (ShapeValueBig3COId) obj;
		return Id.treeShape.equals(this.treeShape)
				&& Id.pkgVerBig3CO.equals(this.pkgVerBig3CO)
				&& Id.pkgVerInstanceTree.equals(this.pkgVerInstanceTree);
	}

}
