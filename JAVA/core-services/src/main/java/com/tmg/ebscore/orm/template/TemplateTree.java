package com.tmg.ebscore.orm.template;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tmg.ebscore.orm.CoreCommonAttributes;
import com.tmg.ebscore.orm.packageversion.PkgVerInstanceTree;

@Entity
@Table(schema = "Tmplt", name = "TemplateTree")
public class TemplateTree extends CoreCommonAttributes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TemplateTree1up")
	private Integer templateTree1up;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PackageTemplate1up")
	private PackageTemplate packageTemplate;

	@Column(name = "Name")
	private String name;

	@Column(name = "Description")
	private String description;

	@Column(name = "GoXamXMLBlob")
	private String goXamXMLBlob;

	@OneToMany(mappedBy = "templateTree", fetch = FetchType.LAZY)
	private Set<ShapeValueDefault> shapeValueTree = new HashSet<ShapeValueDefault>();

	@OneToMany(mappedBy = "templateTree", fetch = FetchType.LAZY)
	private Set<TreeConnector> tempTree = new HashSet<TreeConnector>();

	@OneToMany(mappedBy = "templateTree", fetch = FetchType.LAZY)
	private Set<TreeShape> shapeTree = new HashSet<TreeShape>();

	@OneToMany(mappedBy = "templateTree", fetch = FetchType.LAZY)
	private Set<PkgVerInstanceTree> pkgtempTree = new HashSet<PkgVerInstanceTree>();

	public Set<PkgVerInstanceTree> getPkgtempTree() {
		return pkgtempTree;
	}

	public void setPkgtempTree(Set<PkgVerInstanceTree> pkgtempTree) {
		this.pkgtempTree = pkgtempTree;
	}

	public Set<ShapeValueDefault> getShapeValueTree() {
		return shapeValueTree;
	}

	public void setShapeValueTree(Set<ShapeValueDefault> shapeValueTree) {
		this.shapeValueTree = shapeValueTree;
	}

	public Set<TreeShape> getShapeTree() {
		return shapeTree;
	}

	public void setShapeTree(Set<TreeShape> shapeTree) {
		this.shapeTree = shapeTree;
	}

	public Set<TreeConnector> getTempTree() {
		return tempTree;
	}

	public void setTempTree(Set<TreeConnector> tempTree) {
		this.tempTree = tempTree;
	}

	public Integer getTemplateTree1up() {
		return templateTree1up;
	}

	public void setTemplateTree1up(Integer templateTree1up) {
		this.templateTree1up = templateTree1up;
	}

	public PackageTemplate getPackageTemplate() {
		return packageTemplate;
	}

	public void setPackageTemplate(PackageTemplate packageTemplate) {
		this.packageTemplate = packageTemplate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGoXamXMLBlob() {
		return goXamXMLBlob;
	}

	public void setGoXamXMLBlob(String goXamXMLBlob) {
		this.goXamXMLBlob = goXamXMLBlob;
	}

}
