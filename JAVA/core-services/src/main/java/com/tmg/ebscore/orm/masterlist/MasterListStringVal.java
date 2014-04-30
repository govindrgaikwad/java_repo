package com.tmg.ebscore.orm.masterlist;

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
import com.tmg.ebscore.orm.ebspackage.PkgConfigOption;
import com.tmg.ebscore.orm.ebspackage.PkgMasterListForBig3CO;
import com.tmg.ebscore.orm.ebspackage.ShapeValueBenefitOptionForBig3CO;
import com.tmg.ebscore.orm.ebspackage.ShapeValueBig3CO;
import com.tmg.ebscore.orm.ebspackage.ShapeValueTreeInstance;
import com.tmg.ebscore.orm.packageversion.BenefitOptionTierdata;
import com.tmg.ebscore.orm.packageversion.BenefitSelectionTierdata;
import com.tmg.ebscore.orm.template.PackageTemplate;
import com.tmg.ebscore.orm.template.ShapeValueDefault;
import com.tmg.ebscore.orm.template.TemplateAllowsStringVal;
import com.tmg.ebscore.orm.template.TreeConnector;

@Entity
@Table(schema = "Mast", name = "MasterListStringVal")
public class MasterListStringVal extends CoreCommonAttributes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MasterListStringVal1up")
	private Integer masterListStringVal1up;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MasterList1Up")
	private MasterList masterListString;

	@Column(name = "StringValue")
	private String stringValue;

	@Column(name = "Abbrev")
	private String abbrev;

	@OneToMany(mappedBy = "coinsuranceNoVal", fetch = FetchType.LAZY)
	private Set<BenefitOptionTierdata> coinsuranceBenNoVal = new HashSet<BenefitOptionTierdata>();

	public Set<BenefitOptionTierdata> getCoinsuranceBenNoVal() {
		return coinsuranceBenNoVal;
	}

	public void setCoinsuranceBenNoVal(
			Set<BenefitOptionTierdata> coinsuranceBenNoVal) {
		this.coinsuranceBenNoVal = coinsuranceBenNoVal;
	}

	@OneToMany(mappedBy = "coinsuranceNoVal", fetch = FetchType.LAZY)
	private Set<BenefitSelectionTierdata> coinsuranceNoVal = new HashSet<BenefitSelectionTierdata>();

	@OneToMany(mappedBy = "masterListStringValCoverge", fetch = FetchType.LAZY)
	private Set<MasterListLimitVal> masterLimitCoverage = new HashSet<MasterListLimitVal>();

	@OneToMany(mappedBy = "masterListStringValTimePeriod", fetch = FetchType.LAZY)
	private Set<MasterListLimitVal> masterLimitPeriod = new HashSet<MasterListLimitVal>();

	@OneToMany(mappedBy = "masterListStringValQuanQualifier", fetch = FetchType.LAZY)
	private Set<MasterListLimitVal> masterLimitQuantifier = new HashSet<MasterListLimitVal>();

	@OneToMany(mappedBy = "masterListStringValCat1", fetch = FetchType.LAZY)
	private Set<MasterListServiceDefinition> masterCat1 = new HashSet<MasterListServiceDefinition>();

	@OneToMany(mappedBy = "masterListStringValCat2", fetch = FetchType.LAZY)
	private Set<MasterListServiceDefinition> masterCat2 = new HashSet<MasterListServiceDefinition>();

	@OneToMany(mappedBy = "masterListStringValCat3", fetch = FetchType.LAZY)
	private Set<MasterListServiceDefinition> masterCat3 = new HashSet<MasterListServiceDefinition>();

	@OneToMany(mappedBy = "masterListStringValPostString", fetch = FetchType.LAZY)
	private Set<MasterListServiceDefinition> masterPost = new HashSet<MasterListServiceDefinition>();

	@OneToMany(mappedBy = "benefitPackageTypeAsString", fetch = FetchType.LAZY)
	private Set<PackageTemplate> templateString = new HashSet<PackageTemplate>();

	@OneToMany(mappedBy = "setValueValueAsString", fetch = FetchType.LAZY)
	private Set<ShapeValueDefault> shapeString = new HashSet<ShapeValueDefault>();

	@OneToMany(mappedBy = "templateAllowsStringValId.masterListStringVal", fetch = FetchType.LAZY)
	private Set<TemplateAllowsStringVal> templateAllowString = new HashSet<TemplateAllowsStringVal>();

	@OneToMany(mappedBy = "switchOptionStringVal", fetch = FetchType.LAZY)
	private Set<TreeConnector> treeSwitchOption = new HashSet<TreeConnector>();

	@OneToMany(mappedBy = "masterListStringVal", fetch = FetchType.LAZY)
	private Set<PkgConfigOption> configOptionVer = new HashSet<PkgConfigOption>();

	@OneToMany(mappedBy = "masterListStringVal", fetch = FetchType.LAZY)
	private Set<PkgMasterListForBig3CO> benPkgMasterList = new HashSet<PkgMasterListForBig3CO>();

	@OneToMany(mappedBy = "masterListStringVal", fetch = FetchType.LAZY)
	private Set<ShapeValueBenefitOptionForBig3CO> shapeValueStringBig3Co = new HashSet<ShapeValueBenefitOptionForBig3CO>();

	@OneToMany(mappedBy = "masterListStringVal", fetch = FetchType.LAZY)
	private Set<ShapeValueBig3CO> shapeStringBig3Co = new HashSet<ShapeValueBig3CO>();

	@OneToMany(mappedBy = "masterListStringVal", fetch = FetchType.LAZY)
	private Set<ShapeValueTreeInstance> shapeIntstanceTree = new HashSet<ShapeValueTreeInstance>();

	public Integer getMasterListStringVal1up() {
		return masterListStringVal1up;
	}

	public void setMasterListStringVal1up(Integer masterListStringVal1up) {
		this.masterListStringVal1up = masterListStringVal1up;
	}

	public MasterList getMasterListString() {
		return masterListString;
	}

	public void setMasterListString(MasterList masterListString) {
		this.masterListString = masterListString;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public String getAbbrev() {
		return abbrev;
	}

	public void setAbbrev(String abbrev) {
		this.abbrev = abbrev;
	}

	public Set<BenefitSelectionTierdata> getCoinsuranceNoVal() {
		return coinsuranceNoVal;
	}

	public void setCoinsuranceNoVal(
			Set<BenefitSelectionTierdata> coinsuranceNoVal) {
		this.coinsuranceNoVal = coinsuranceNoVal;
	}

	public Set<MasterListLimitVal> getMasterLimitCoverage() {
		return masterLimitCoverage;
	}

	public void setMasterLimitCoverage(
			Set<MasterListLimitVal> masterLimitCoverage) {
		this.masterLimitCoverage = masterLimitCoverage;
	}

	public Set<MasterListLimitVal> getMasterLimitPeriod() {
		return masterLimitPeriod;
	}

	public void setMasterLimitPeriod(Set<MasterListLimitVal> masterLimitPeriod) {
		this.masterLimitPeriod = masterLimitPeriod;
	}

	public Set<MasterListLimitVal> getMasterLimitQuantifier() {
		return masterLimitQuantifier;
	}

	public void setMasterLimitQuantifier(
			Set<MasterListLimitVal> masterLimitQuantifier) {
		this.masterLimitQuantifier = masterLimitQuantifier;
	}

	public Set<MasterListServiceDefinition> getMasterCat1() {
		return masterCat1;
	}

	public void setMasterCat1(Set<MasterListServiceDefinition> masterCat1) {
		this.masterCat1 = masterCat1;
	}

	public Set<MasterListServiceDefinition> getMasterCat2() {
		return masterCat2;
	}

	public void setMasterCat2(Set<MasterListServiceDefinition> masterCat2) {
		this.masterCat2 = masterCat2;
	}

	public Set<MasterListServiceDefinition> getMasterCat3() {
		return masterCat3;
	}

	public void setMasterCat3(Set<MasterListServiceDefinition> masterCat3) {
		this.masterCat3 = masterCat3;
	}

	public Set<MasterListServiceDefinition> getMasterPost() {
		return masterPost;
	}

	public void setMasterPost(Set<MasterListServiceDefinition> masterPost) {
		this.masterPost = masterPost;
	}

	public Set<PackageTemplate> getTemplateString() {
		return templateString;
	}

	public void setTemplateString(Set<PackageTemplate> templateString) {
		this.templateString = templateString;
	}

	public Set<ShapeValueDefault> getShapeString() {
		return shapeString;
	}

	public void setShapeString(Set<ShapeValueDefault> shapeString) {
		this.shapeString = shapeString;
	}

	public Set<TemplateAllowsStringVal> getTemplateAllowString() {
		return templateAllowString;
	}

	public void setTemplateAllowString(
			Set<TemplateAllowsStringVal> templateAllowString) {
		this.templateAllowString = templateAllowString;
	}

	public Set<TreeConnector> getTreeSwitchOption() {
		return treeSwitchOption;
	}

	public void setTreeSwitchOption(Set<TreeConnector> treeSwitchOption) {
		this.treeSwitchOption = treeSwitchOption;
	}

	public Set<PkgConfigOption> getConfigOptionVer() {
		return configOptionVer;
	}

	public void setConfigOptionVer(Set<PkgConfigOption> configOptionVer) {
		this.configOptionVer = configOptionVer;
	}

	public Set<PkgMasterListForBig3CO> getBenPkgMasterList() {
		return benPkgMasterList;
	}

	public void setBenPkgMasterList(Set<PkgMasterListForBig3CO> benPkgMasterList) {
		this.benPkgMasterList = benPkgMasterList;
	}

	public Set<ShapeValueBenefitOptionForBig3CO> getShapeValueStringBig3Co() {
		return shapeValueStringBig3Co;
	}

	public void setShapeValueStringBig3Co(
			Set<ShapeValueBenefitOptionForBig3CO> shapeValueStringBig3Co) {
		this.shapeValueStringBig3Co = shapeValueStringBig3Co;
	}

	public Set<ShapeValueBig3CO> getShapeStringBig3Co() {
		return shapeStringBig3Co;
	}

	public void setShapeStringBig3Co(Set<ShapeValueBig3CO> shapeStringBig3Co) {
		this.shapeStringBig3Co = shapeStringBig3Co;
	}

	public Set<ShapeValueTreeInstance> getShapeIntstanceTree() {
		return shapeIntstanceTree;
	}

	public void setShapeIntstanceTree(
			Set<ShapeValueTreeInstance> shapeIntstanceTree) {
		this.shapeIntstanceTree = shapeIntstanceTree;
	}

}
