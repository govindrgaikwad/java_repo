package com.tmg.ebscore.orm.ebspackage;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tmg.ebscore.orm.packageversion.PkgVerInstanceTree;
import com.tmg.ebscore.orm.template.TreeShape;

@Embeddable
public class ShapeValueTreeInstanceId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TreeShape1up")
	private TreeShape treeShape;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PkgVerInstanceTree1up")
	private PkgVerInstanceTree pkgVerInstanceTree;

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
	
	public int hashCode() {
		return (int) this.treeShape.hashCode() + this.pkgVerInstanceTree.hashCode();
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof ShapeValueTreeInstanceId)) {
			return false;
		}
		ShapeValueTreeInstanceId Id = (ShapeValueTreeInstanceId) obj;
		return Id.treeShape.equals(this.treeShape)
				&& Id.pkgVerInstanceTree.equals(this.pkgVerInstanceTree);
	}

}
