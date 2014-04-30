package com.tmg.ebscore.orm.packageversion;

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
import com.tmg.ebscore.orm.ebspackage.LimitShapeBenefitOptionOverride;
import com.tmg.ebscore.orm.ebspackage.MatrixShapeBig3COLimitXXref;
import com.tmg.ebscore.orm.ebspackage.MatrixShapeBig3COMessageXXref;
import com.tmg.ebscore.orm.ebspackage.MatrixShapeBig3COXref;
import com.tmg.ebscore.orm.ebspackage.MessageShapeBenefitOptionOverride;
import com.tmg.ebscore.orm.ebspackage.PkgMasterListForBig3CO;
import com.tmg.ebscore.orm.ebspackage.ShapeValueBenefitOptionForBig3CO;
import com.tmg.ebscore.orm.ebspackage.ShapeValueBig3CO;

@Entity
@Table(schema = "Pkg", name = "PkgVerBig3CO")
public class PkgVerBig3CO extends CoreCommonAttributes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PkgVerBig3CO1up")
	private Integer pkgVerBig3CO1up;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PkgVersion1up")
	private PkgVersion pkgVersion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PkgMasterListForBig3CO1up")
	private PkgMasterListForBig3CO pkgMasterListForBig3CO;

	@Column(name = "IsCoveredFlag")
	private Boolean coveredFlag;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PkgVerInstanceTree1up")
	private PkgVerInstanceTree pkgVerInstanceTree;

	@Column(name = "TreatAs100PctFlag")
	private Boolean treatAs100PctFlag;

	@Column(name = "IsSETRMessageSelected")
	private Boolean sETRMessageSelected;

	@OneToMany(mappedBy = "pkgVerBig3CO", fetch = FetchType.LAZY)
	private Set<BenefitOptionTierdata> pkgVerBig3CO = new HashSet<BenefitOptionTierdata>();

	@OneToMany(mappedBy = "pkgVerBig3CO", fetch = FetchType.LAZY)
	private Set<BenefitSelectionTierdata> pkgVerBenSel3Co = new HashSet<BenefitSelectionTierdata>();

	@OneToMany(mappedBy = "pkgVerBig3CO", fetch = FetchType.LAZY)
	private Set<BenefitOptionBig3CO> pkgVerBenOption3Co = new HashSet<BenefitOptionBig3CO>();

	@OneToMany(mappedBy = "limitShapeBenefitOptionOverrideId.matrixShapeBig3COLimitXXrefId.matrixShapeBig3COXrefId.pkgVerBig3CO", fetch = FetchType.LAZY)
	private Set<LimitShapeBenefitOptionOverride> pkgVerOptionOverride = new HashSet<LimitShapeBenefitOptionOverride>();

	@OneToMany(mappedBy = "matrixShapeBig3COLimitXXrefId.matrixShapeBig3COXrefId.pkgVerBig3CO", fetch = FetchType.LAZY)
	private Set<MatrixShapeBig3COLimitXXref> treeShapeBigLimitXref = new HashSet<MatrixShapeBig3COLimitXXref>();

	@OneToMany(mappedBy = "matrixShapeBig3COMessageXXrefId.matrixShapeBig3COXrefId.pkgVerBig3CO", fetch = FetchType.LAZY)
	private Set<MatrixShapeBig3COMessageXXref> treeShapeBigMessageXref = new HashSet<MatrixShapeBig3COMessageXXref>();

	@OneToMany(mappedBy = "matrixShapeBig3COXrefId.pkgVerBig3CO", fetch = FetchType.LAZY)
	private Set<MatrixShapeBig3COXref> treeShapeBig3CoXref = new HashSet<MatrixShapeBig3COXref>();

	@OneToMany(mappedBy = "messageShapeBenefitOptionOverrideId.matrixShapeBig3COMessageXXrefId.matrixShapeBig3COXrefId.pkgVerBig3CO", fetch = FetchType.LAZY)
	private Set<MessageShapeBenefitOptionOverride> treeShapeMessageOption = new HashSet<MessageShapeBenefitOptionOverride>();

	@OneToMany(mappedBy = "shapeValueBenefitOptionForBig3COId.pkgVerBig3CO", fetch = FetchType.LAZY)
	private Set<ShapeValueBenefitOptionForBig3CO> shapeValueIntBig3Co = new HashSet<ShapeValueBenefitOptionForBig3CO>();

	@OneToMany(mappedBy = "shapeValueBig3COId.pkgVerBig3CO", fetch = FetchType.LAZY)
	private Set<ShapeValueBig3CO> shapeIntBig3Co = new HashSet<ShapeValueBig3CO>();

	public Integer getPkgVerBig3CO1up() {
		return pkgVerBig3CO1up;
	}

	public Set<BenefitOptionTierdata> getPkgVerBig3CO() {
		return pkgVerBig3CO;
	}

	public void setPkgVerBig3CO(Set<BenefitOptionTierdata> pkgVerBig3CO) {
		this.pkgVerBig3CO = pkgVerBig3CO;
	}

	public void setPkgVerBig3CO1up(Integer pkgVerBig3CO1up) {
		this.pkgVerBig3CO1up = pkgVerBig3CO1up;
	}

	public PkgVersion getPkgVersion() {
		return pkgVersion;
	}

	public void setPkgVersion(PkgVersion pkgVersion) {
		this.pkgVersion = pkgVersion;
	}

	public PkgMasterListForBig3CO getPkgMasterListForBig3CO() {
		return pkgMasterListForBig3CO;
	}

	public void setPkgMasterListForBig3CO(
			PkgMasterListForBig3CO pkgMasterListForBig3CO) {
		this.pkgMasterListForBig3CO = pkgMasterListForBig3CO;
	}

	public Boolean getCoveredFlag() {
		return coveredFlag;
	}

	public void setCoveredFlag(Boolean coveredFlag) {
		this.coveredFlag = coveredFlag;
	}

	public PkgVerInstanceTree getPkgVerInstanceTree() {
		return pkgVerInstanceTree;
	}

	public void setPkgVerInstanceTree(PkgVerInstanceTree pkgVerInstanceTree) {
		this.pkgVerInstanceTree = pkgVerInstanceTree;
	}

	public Boolean getTreatAs100PctFlag() {
		return treatAs100PctFlag;
	}

	public void setTreatAs100PctFlag(Boolean treatAs100PctFlag) {
		this.treatAs100PctFlag = treatAs100PctFlag;
	}

	public Boolean getsETRMessageSelected() {
		return sETRMessageSelected;
	}

	public void setsETRMessageSelected(Boolean sETRMessageSelected) {
		this.sETRMessageSelected = sETRMessageSelected;
	}

	public Set<BenefitSelectionTierdata> getPkgVerBenSel3Co() {
		return pkgVerBenSel3Co;
	}

	public void setPkgVerBenSel3Co(Set<BenefitSelectionTierdata> pkgVerBenSel3Co) {
		this.pkgVerBenSel3Co = pkgVerBenSel3Co;
	}

	public Set<BenefitOptionBig3CO> getPkgVerBenOption3Co() {
		return pkgVerBenOption3Co;
	}

	public void setPkgVerBenOption3Co(
			Set<BenefitOptionBig3CO> pkgVerBenOption3Co) {
		this.pkgVerBenOption3Co = pkgVerBenOption3Co;
	}

	public Set<LimitShapeBenefitOptionOverride> getPkgVerOptionOverride() {
		return pkgVerOptionOverride;
	}

	public void setPkgVerOptionOverride(
			Set<LimitShapeBenefitOptionOverride> pkgVerOptionOverride) {
		this.pkgVerOptionOverride = pkgVerOptionOverride;
	}

	public Set<MatrixShapeBig3COLimitXXref> getTreeShapeBigLimitXref() {
		return treeShapeBigLimitXref;
	}

	public void setTreeShapeBigLimitXref(
			Set<MatrixShapeBig3COLimitXXref> treeShapeBigLimitXref) {
		this.treeShapeBigLimitXref = treeShapeBigLimitXref;
	}

	public Set<MatrixShapeBig3COMessageXXref> getTreeShapeBigMessageXref() {
		return treeShapeBigMessageXref;
	}

	public void setTreeShapeBigMessageXref(
			Set<MatrixShapeBig3COMessageXXref> treeShapeBigMessageXref) {
		this.treeShapeBigMessageXref = treeShapeBigMessageXref;
	}

	public Set<MatrixShapeBig3COXref> getTreeShapeBig3CoXref() {
		return treeShapeBig3CoXref;
	}

	public void setTreeShapeBig3CoXref(
			Set<MatrixShapeBig3COXref> treeShapeBig3CoXref) {
		this.treeShapeBig3CoXref = treeShapeBig3CoXref;
	}

	public Set<MessageShapeBenefitOptionOverride> getTreeShapeMessageOption() {
		return treeShapeMessageOption;
	}

	public void setTreeShapeMessageOption(
			Set<MessageShapeBenefitOptionOverride> treeShapeMessageOption) {
		this.treeShapeMessageOption = treeShapeMessageOption;
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

}
