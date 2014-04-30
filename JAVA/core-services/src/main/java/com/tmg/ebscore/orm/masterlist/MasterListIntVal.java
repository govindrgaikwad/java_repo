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
import com.tmg.ebscore.orm.ebspackage.ShapeValueBenefitOptionForBig3CO;
import com.tmg.ebscore.orm.ebspackage.ShapeValueBig3CO;
import com.tmg.ebscore.orm.ebspackage.ShapeValueTreeInstance;
import com.tmg.ebscore.orm.packageversion.BenefitOptionTierdata;
import com.tmg.ebscore.orm.packageversion.BenefitSelectionTierdata;
import com.tmg.ebscore.orm.template.ShapeValueDefault;
import com.tmg.ebscore.orm.template.TemplateAllowsIntVal;

@Entity
@Table(schema = "Mast", name = "MasterListIntVal")
public class MasterListIntVal extends CoreCommonAttributes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MasterListIntVal1up")
	private Integer masterListIntVal1up;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MasterList1Up")
	private MasterList masterListInt;

	@Column(name = "IntAsDecimalValue")
	private Double intAsDecimalValue;

	@Column(name = "Abbrev")
	private String abbrev;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Accumulation1up")
	private MasterListAccumulationVal accumulation;

	@OneToMany(mappedBy = "allowedAmt", fetch = FetchType.LAZY)
	private Set<BenefitOptionTierdata> allowedBenAmt1 = new HashSet<BenefitOptionTierdata>();

	@OneToMany(mappedBy = "allowedCtr", fetch = FetchType.LAZY)
	private Set<BenefitOptionTierdata> allowedBenCtr = new HashSet<BenefitOptionTierdata>();

	@OneToMany(mappedBy = "coinsuranceYesVal", fetch = FetchType.LAZY)
	private Set<BenefitOptionTierdata> coinsuranceBenYesVal = new HashSet<BenefitOptionTierdata>();

	@OneToMany(mappedBy = "copayVal", fetch = FetchType.LAZY)
	private Set<BenefitOptionTierdata> copayBenVal = new HashSet<BenefitOptionTierdata>();

	@OneToMany(mappedBy = "allowedCtr", fetch = FetchType.LAZY)
	private Set<BenefitSelectionTierdata> allowedCtr = new HashSet<BenefitSelectionTierdata>();

	@OneToMany(mappedBy = "allowedAmt", fetch = FetchType.LAZY)
	private Set<BenefitSelectionTierdata> allowedAmt = new HashSet<BenefitSelectionTierdata>();

	@OneToMany(mappedBy = "copayVal", fetch = FetchType.LAZY)
	private Set<BenefitSelectionTierdata> copayVal = new HashSet<BenefitSelectionTierdata>();

	@OneToMany(mappedBy = "coinsuranceYesVal", fetch = FetchType.LAZY)
	private Set<BenefitSelectionTierdata> coinsuranceYesVal = new HashSet<BenefitSelectionTierdata>();

	@OneToMany(mappedBy = "setValueValueAsInt", fetch = FetchType.LAZY)
	private Set<ShapeValueDefault> shapeInt = new HashSet<ShapeValueDefault>();

	@OneToMany(mappedBy = "templateAllowsIntValId.masterListIntVal", fetch = FetchType.LAZY)
	private Set<TemplateAllowsIntVal> templateAllowInt = new HashSet<TemplateAllowsIntVal>();

	@OneToMany(mappedBy = "masterListIntVal", fetch = FetchType.LAZY)
	private Set<ShapeValueBenefitOptionForBig3CO> shapeValueIntBig3Co = new HashSet<ShapeValueBenefitOptionForBig3CO>();

	@OneToMany(mappedBy = "masterListIntVal", fetch = FetchType.LAZY)
	private Set<ShapeValueBig3CO> shapeIntBig3Co = new HashSet<ShapeValueBig3CO>();

	@OneToMany(mappedBy = "masterListIntVal", fetch = FetchType.LAZY)
	private Set<ShapeValueTreeInstance> shapeIntstanceTree = new HashSet<ShapeValueTreeInstance>();

	public Set<BenefitOptionTierdata> getCoinsuranceBenYesVal() {
		return coinsuranceBenYesVal;
	}

	public Set<BenefitOptionTierdata> getAllowedBenAmt1() {
		return allowedBenAmt1;
	}

	public void setAllowedBenAmt1(Set<BenefitOptionTierdata> allowedBenAmt1) {
		this.allowedBenAmt1 = allowedBenAmt1;
	}

	public Set<BenefitOptionTierdata> getAllowedBenCtr() {
		return allowedBenCtr;
	}

	public void setAllowedBenCtr(Set<BenefitOptionTierdata> allowedBenCtr) {
		this.allowedBenCtr = allowedBenCtr;
	}

	public void setCoinsuranceBenYesVal(
			Set<BenefitOptionTierdata> coinsuranceBenYesVal) {
		this.coinsuranceBenYesVal = coinsuranceBenYesVal;
	}

	public Set<BenefitOptionTierdata> getCopayBenVal() {
		return copayBenVal;
	}

	public void setCopayBenVal(Set<BenefitOptionTierdata> copayBenVal) {
		this.copayBenVal = copayBenVal;
	}

	public Integer getMasterListIntVal1up() {
		return masterListIntVal1up;
	}

	public void setMasterListIntVal1up(Integer masterListIntVal1up) {
		this.masterListIntVal1up = masterListIntVal1up;
	}

	public MasterList getMasterListInt() {
		return masterListInt;
	}

	public void setMasterListInt(MasterList masterListInt) {
		this.masterListInt = masterListInt;
	}

	public Double getIntAsDecimalValue() {
		return intAsDecimalValue;
	}

	public void setIntAsDecimalValue(Double intAsDecimalValue) {
		this.intAsDecimalValue = intAsDecimalValue;
	}

	public String getAbbrev() {
		return abbrev;
	}

	public void setAbbrev(String abbrev) {
		this.abbrev = abbrev;
	}

	public MasterListAccumulationVal getAccumulation() {
		return accumulation;
	}

	public void setAccumulation(MasterListAccumulationVal accumulation) {
		this.accumulation = accumulation;
	}

	public Set<BenefitSelectionTierdata> getAllowedCtr() {
		return allowedCtr;
	}

	public void setAllowedCtr(Set<BenefitSelectionTierdata> allowedCtr) {
		this.allowedCtr = allowedCtr;
	}

	public Set<BenefitSelectionTierdata> getAllowedAmt() {
		return allowedAmt;
	}

	public void setAllowedAmt(Set<BenefitSelectionTierdata> allowedAmt) {
		this.allowedAmt = allowedAmt;
	}

	public Set<BenefitSelectionTierdata> getCopayVal() {
		return copayVal;
	}

	public void setCopayVal(Set<BenefitSelectionTierdata> copayVal) {
		this.copayVal = copayVal;
	}

	public Set<BenefitSelectionTierdata> getCoinsuranceYesVal() {
		return coinsuranceYesVal;
	}

	public void setCoinsuranceYesVal(
			Set<BenefitSelectionTierdata> coinsuranceYesVal) {
		this.coinsuranceYesVal = coinsuranceYesVal;
	}

	public Set<ShapeValueDefault> getShapeInt() {
		return shapeInt;
	}

	public void setShapeInt(Set<ShapeValueDefault> shapeInt) {
		this.shapeInt = shapeInt;
	}

	public Set<TemplateAllowsIntVal> getTemplateAllowInt() {
		return templateAllowInt;
	}

	public void setTemplateAllowInt(Set<TemplateAllowsIntVal> templateAllowInt) {
		this.templateAllowInt = templateAllowInt;
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
