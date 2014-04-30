package com.tmg.ebscore.orm.packageversion;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
import com.tmg.ebscore.orm.ebspackage.ExcelViewInfo;
import com.tmg.ebscore.orm.ebspackage.ShapeValueBenefitOptionForBig3CO;
import com.tmg.ebscore.orm.ebspackage.ShapeValueBig3CO;
import com.tmg.ebscore.orm.ebspackage.ShapeValueTreeInstance;
import com.tmg.ebscore.orm.template.TemplateTree;

@Entity
@Table(schema = "Pkg", name = "PkgVerInstanceTree")
public class PkgVerInstanceTree extends CoreCommonAttributes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PkgVerInstanceTree1up")
	private Integer pkgVerInstanceTree1up;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PkgVersion1up")
	private PkgVersion pkgVersion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TemplateTree1up")
	private TemplateTree templateTree;

	@Column(name = "Name")
	private String name;

	@Column(name = "Description")
	private String description;

	@OneToMany(mappedBy = "pkgVerInstanceTree", fetch = FetchType.LAZY)
	private Set<BenefitOptionTierdata> pkgVerInstanceTree = new HashSet<BenefitOptionTierdata>();

	@OneToMany(mappedBy = "pkgVerInstanceTree", fetch = FetchType.LAZY)
	private Set<BenefitSelectionTierdata> pkgVerBenInstanceTree = new HashSet<BenefitSelectionTierdata>();

	@OneToMany(mappedBy = "pkgVerInstanceTree", fetch = FetchType.LAZY)
	private Set<PkgVerBig3CO> pkgInstanceTree = new HashSet<PkgVerBig3CO>();

	@OneToMany(mappedBy = "pkgVerInstanceTree", fetch = FetchType.LAZY)
	private Set<BenefitOptionBig3CO> pkgVerBenOptionTree = new HashSet<BenefitOptionBig3CO>();

	@OneToMany(mappedBy = "pkgVerInstanceTree", fetch = FetchType.LAZY)
	private Set<ExcelViewInfo> instanceTreeExcel = new HashSet<ExcelViewInfo>();

	@OneToMany(mappedBy = "shapeValueBenefitOptionForBig3COId.pkgVerInstanceTree", fetch = FetchType.LAZY)
	private Set<ShapeValueBenefitOptionForBig3CO> shapeValueIntBig3Co = new HashSet<ShapeValueBenefitOptionForBig3CO>();

	@OneToMany(mappedBy = "shapeValueBig3COId.pkgVerInstanceTree", fetch = FetchType.LAZY)
	private Set<ShapeValueBig3CO> shapeIntBig3Co = new HashSet<ShapeValueBig3CO>();

	@OneToMany(mappedBy = "shapeValueTreeInstanceId.pkgVerInstanceTree", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ShapeValueTreeInstance> shapeIntstanceTree = new HashSet<ShapeValueTreeInstance>();

	public Integer getPkgVerInstanceTree1up() {
		return pkgVerInstanceTree1up;
	}

	public Set<BenefitOptionTierdata> getPkgVerInstanceTree() {
		return pkgVerInstanceTree;
	}

	public void setPkgVerInstanceTree(
			Set<BenefitOptionTierdata> pkgVerInstanceTree) {
		this.pkgVerInstanceTree = pkgVerInstanceTree;
	}

	public void setPkgVerInstanceTree1up(Integer pkgVerInstanceTree1up) {
		this.pkgVerInstanceTree1up = pkgVerInstanceTree1up;
	}

	public PkgVersion getPkgVersion() {
		return pkgVersion;
	}

	public void setPkgVersion(PkgVersion pkgVersion) {
		this.pkgVersion = pkgVersion;
	}

	public TemplateTree getTemplateTree() {
		return templateTree;
	}

	public void setTemplateTree(TemplateTree templateTree) {
		this.templateTree = templateTree;
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

	public Set<BenefitSelectionTierdata> getPkgVerBenInstanceTree() {
		return pkgVerBenInstanceTree;
	}

	public void setPkgVerBenInstanceTree(
			Set<BenefitSelectionTierdata> pkgVerBenInstanceTree) {
		this.pkgVerBenInstanceTree = pkgVerBenInstanceTree;
	}

	public Set<PkgVerBig3CO> getPkgInstanceTree() {
		return pkgInstanceTree;
	}

	public void setPkgInstanceTree(Set<PkgVerBig3CO> pkgInstanceTree) {
		this.pkgInstanceTree = pkgInstanceTree;
	}

	public Set<BenefitOptionBig3CO> getPkgVerBenOptionTree() {
		return pkgVerBenOptionTree;
	}

	public void setPkgVerBenOptionTree(
			Set<BenefitOptionBig3CO> pkgVerBenOptionTree) {
		this.pkgVerBenOptionTree = pkgVerBenOptionTree;
	}

	public Set<ExcelViewInfo> getInstanceTreeExcel() {
		return instanceTreeExcel;
	}

	public void setInstanceTreeExcel(Set<ExcelViewInfo> instanceTreeExcel) {
		this.instanceTreeExcel = instanceTreeExcel;
	}

	public Set<ShapeValueBenefitOptionForBig3CO> getShapeValueIntBig3Co() {
		return shapeValueIntBig3Co;
	}

	public void setShapeValueIntBig3Co(
			Set<ShapeValueBenefitOptionForBig3CO> shapeValueIntBig3Co) {
		this.shapeValueIntBig3Co = shapeValueIntBig3Co;
	}

	public Set<ShapeValueBig3CO> getShapeIntBig3Co() {
		return shapeIntBig3Co;
	}

	public void setShapeIntBig3Co(Set<ShapeValueBig3CO> shapeIntBig3Co) {
		this.shapeIntBig3Co = shapeIntBig3Co;
	}

	public Set<ShapeValueTreeInstance> getShapeIntstanceTree() {
		return shapeIntstanceTree;
	}

	public void setShapeIntstanceTree(
			Set<ShapeValueTreeInstance> shapeIntstanceTree) {
		this.shapeIntstanceTree = shapeIntstanceTree;
	}

}
