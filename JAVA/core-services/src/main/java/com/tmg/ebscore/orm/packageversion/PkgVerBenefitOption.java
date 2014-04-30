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
import com.tmg.ebscore.orm.ebspackage.MessageShapeBenefitOptionOverride;
import com.tmg.ebscore.orm.ebspackage.PkgConfigOptionBenefitOptionXref;
import com.tmg.ebscore.orm.ebspackage.ShapeValueBenefitOptionForBig3CO;

@Entity
@Table(schema = "Pkg", name = "PkgVerBenefitOption")
public class PkgVerBenefitOption extends CoreCommonAttributes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PkgVerBenefitOption1up")
	private Integer pkgVerBenefitOption1up;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PkgVersion1up")
	private PkgVersion pkgVersion;

	@Column(name = "Name")
	private String name;

	@Column(name = "Description")
	private String description;

	@OneToMany(mappedBy = "benefitOption", fetch = FetchType.LAZY)
	private Set<BenefitOptionTierdata> PkgVerBenefitOption = new HashSet<BenefitOptionTierdata>();

	@OneToMany(mappedBy = "pkgVerProductBenefitOptionXrefId.pkgVerBenefitOption", fetch = FetchType.LAZY)
	private Set<PkgVerProductBenefitOptionXref> pkgVerProductOprtion = new HashSet<PkgVerProductBenefitOptionXref>();

	@OneToMany(mappedBy = "pkgVerBenefitOption", fetch = FetchType.LAZY)
	private Set<BenefitOptionBig3CO> pkgVerBenOptionBig3CO = new HashSet<BenefitOptionBig3CO>();

	@OneToMany(mappedBy = "limitShapeBenefitOptionOverrideId.pkgVerBenefitOption", fetch = FetchType.LAZY)
	private Set<LimitShapeBenefitOptionOverride> pkgVerBenOptionOverride = new HashSet<LimitShapeBenefitOptionOverride>();

	@OneToMany(mappedBy = "messageShapeBenefitOptionOverrideId.pkgVerBenefitOption", fetch = FetchType.LAZY)
	private Set<MessageShapeBenefitOptionOverride> treeShapeMessageOption = new HashSet<MessageShapeBenefitOptionOverride>();

	@OneToMany(mappedBy = "pkgVerBenefitOption", fetch = FetchType.LAZY)
	private Set<PkgConfigOptionBenefitOptionXref> pkgConfigXref = new HashSet<PkgConfigOptionBenefitOptionXref>();

	@OneToMany(mappedBy = "shapeValueBenefitOptionForBig3COId.pkgVerBenefitOption", fetch = FetchType.LAZY)
	private Set<ShapeValueBenefitOptionForBig3CO> shapeValueIntBig3Co = new HashSet<ShapeValueBenefitOptionForBig3CO>();

	public Set<ShapeValueBenefitOptionForBig3CO> getShapeValueIntBig3Co() {
		return shapeValueIntBig3Co;
	}

	public Set<BenefitOptionTierdata> getPkgVerBenefitOption() {
		return PkgVerBenefitOption;
	}

	public void setPkgVerBenefitOption(
			Set<BenefitOptionTierdata> pkgVerBenefitOption) {
		PkgVerBenefitOption = pkgVerBenefitOption;
	}

	public void setShapeValueIntBig3Co(
			Set<ShapeValueBenefitOptionForBig3CO> shapeValueIntBig3Co) {
		this.shapeValueIntBig3Co = shapeValueIntBig3Co;
	}

	public Set<PkgConfigOptionBenefitOptionXref> getPkgConfigXref() {
		return pkgConfigXref;
	}

	public void setPkgConfigXref(
			Set<PkgConfigOptionBenefitOptionXref> pkgConfigXref) {
		this.pkgConfigXref = pkgConfigXref;
	}

	public Set<MessageShapeBenefitOptionOverride> getTreeShapeMessageOption() {
		return treeShapeMessageOption;
	}

	public void setTreeShapeMessageOption(
			Set<MessageShapeBenefitOptionOverride> treeShapeMessageOption) {
		this.treeShapeMessageOption = treeShapeMessageOption;
	}

	public Set<LimitShapeBenefitOptionOverride> getPkgVerBenOptionOverride() {
		return pkgVerBenOptionOverride;
	}

	public void setPkgVerBenOptionOverride(
			Set<LimitShapeBenefitOptionOverride> pkgVerBenOptionOverride) {
		this.pkgVerBenOptionOverride = pkgVerBenOptionOverride;
	}

	public Set<BenefitOptionBig3CO> getPkgVerBenOptionBig3CO() {
		return pkgVerBenOptionBig3CO;
	}

	public void setPkgVerBenOptionBig3CO(
			Set<BenefitOptionBig3CO> pkgVerBenOptionBig3CO) {
		this.pkgVerBenOptionBig3CO = pkgVerBenOptionBig3CO;
	}

	public Set<PkgVerProductBenefitOptionXref> getPkgVerProductOprtion() {
		return pkgVerProductOprtion;
	}

	public void setPkgVerProductOprtion(
			Set<PkgVerProductBenefitOptionXref> pkgVerProductOprtion) {
		this.pkgVerProductOprtion = pkgVerProductOprtion;
	}

	public Integer getPkgVerBenefitOption1up() {
		return pkgVerBenefitOption1up;
	}

	public void setPkgVerBenefitOption1up(Integer pkgVerBenefitOption1up) {
		this.pkgVerBenefitOption1up = pkgVerBenefitOption1up;
	}

	public PkgVersion getPkgVersion() {
		return pkgVersion;
	}

	public void setPkgVersion(PkgVersion pkgVersion) {
		this.pkgVersion = pkgVersion;
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

}
