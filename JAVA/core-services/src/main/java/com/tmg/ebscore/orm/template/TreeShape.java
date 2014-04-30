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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.tmg.ebscore.orm.CoreCommonAttributes;
import com.tmg.ebscore.orm.ebspackage.ExcelViewInfo;
import com.tmg.ebscore.orm.ebspackage.LimitShapeBenefitOptionOverride;
import com.tmg.ebscore.orm.ebspackage.MatrixShapeBig3COLimitXXref;
import com.tmg.ebscore.orm.ebspackage.MatrixShapeBig3COMessageXXref;
import com.tmg.ebscore.orm.ebspackage.MatrixShapeBig3COXref;
import com.tmg.ebscore.orm.ebspackage.MessageShapeBenefitOptionOverride;
import com.tmg.ebscore.orm.ebspackage.ShapeValueBenefitOptionForBig3CO;
import com.tmg.ebscore.orm.ebspackage.ShapeValueBig3CO;
import com.tmg.ebscore.orm.ebspackage.ShapeValueTreeInstance;
import com.tmg.ebscore.orm.packageversion.BenefitOptionTierdata;
import com.tmg.ebscore.orm.packageversion.BenefitSelectionTierdata;

@Entity
@Table(schema = "Tmplt", name = "TreeShape")
public class TreeShape extends CoreCommonAttributes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TreeShape1up")
	private Integer treeShape1up;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TemplateTree1up")
	private TemplateTree templateTree;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TreeShapeType1up")
	private TreeShapeType treeShapeType;

	@Column(name = "Note")
	private String note;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IfYNTestAttribute1up")
	private IfYNTestAttribute ifYNTestAttribute;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SetValueParam1up")
	private SetValueParam setValueParam;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SwitchCriteria1up")
	private SwitchCriteria switchCriteria;

	@Column(name = "CollectionLabel")
	private String collectionLabel;

	@Column(name = "CollectionDescription")
	private String collectionDescription;

	@Column(name = "GoXamXMLBlob")
	private String goXamXMLBlob;

	@OneToMany(mappedBy = "allowedCtrShape", fetch = FetchType.LAZY)
	private Set<BenefitOptionTierdata> allowedBenCtrShape = new HashSet<BenefitOptionTierdata>();

	@OneToMany(mappedBy = "copayShape", fetch = FetchType.LAZY)
	private Set<BenefitOptionTierdata> copayBenShape = new HashSet<BenefitOptionTierdata>();

	@OneToMany(mappedBy = "allowedAmtShape", fetch = FetchType.LAZY)
	private Set<BenefitOptionTierdata> allowedBenAmtShape = new HashSet<BenefitOptionTierdata>();

	@OneToMany(mappedBy = "coinsuranceNoValShape", fetch = FetchType.LAZY)
	private Set<BenefitOptionTierdata> coinsuranceBenNoValShape = new HashSet<BenefitOptionTierdata>();

	@OneToMany(mappedBy = "coinsuranceYesValShape", fetch = FetchType.LAZY)
	private Set<BenefitOptionTierdata> coinsuranceBenYesValShape = new HashSet<BenefitOptionTierdata>();

	@OneToMany(mappedBy = "applyCoinsuranceShape", fetch = FetchType.LAZY)
	private Set<BenefitOptionTierdata> applyBenCoinsuranceShape = new HashSet<BenefitOptionTierdata>();

	@OneToMany(mappedBy = "allowedCtrShape", fetch = FetchType.LAZY)
	private Set<BenefitSelectionTierdata> allowedCtrShape = new HashSet<BenefitSelectionTierdata>();

	@OneToMany(mappedBy = "copayShape", fetch = FetchType.LAZY)
	private Set<BenefitSelectionTierdata> copayShape = new HashSet<BenefitSelectionTierdata>();

	@OneToMany(mappedBy = "allowedAmtShape" , fetch = FetchType.LAZY)
	private Set<BenefitSelectionTierdata> allowedAmtShape = new HashSet<BenefitSelectionTierdata>();

	@OneToMany(mappedBy = "coinsuranceNoValShape", fetch = FetchType.LAZY)
	private Set<BenefitSelectionTierdata> coinsuranceNoValShape = new HashSet<BenefitSelectionTierdata>();

	@OneToMany(mappedBy = "coinsuranceYesValShape", fetch = FetchType.LAZY)
	private Set<BenefitSelectionTierdata> coinsuranceYesValShape = new HashSet<BenefitSelectionTierdata>();

	@OneToMany(mappedBy = "applyCoinsuranceShape", fetch = FetchType.LAZY)
	private Set<BenefitSelectionTierdata> applyCoinsuranceShape = new HashSet<BenefitSelectionTierdata>();

	@OneToMany(mappedBy = "matrixShapeLimitXrefId.treeShape", fetch = FetchType.LAZY)
	private Set<MatrixShapeLimitXref> masterShapeTree = new HashSet<MatrixShapeLimitXref>();

	@OneToMany(mappedBy = "matrixShapeMessageXrefId.treeShape", fetch = FetchType.LAZY)
	private Set<MatrixShapeMessageXref> masterMessageXrefTree = new HashSet<MatrixShapeMessageXref>();

	@OneToMany(mappedBy = "fromShape", fetch = FetchType.LAZY)
	private Set<TreeConnector> treeFromShape = new HashSet<TreeConnector>();

	@OneToMany(mappedBy = "treeShape", fetch = FetchType.LAZY)
	private Set<ExcelViewInfo> treeShapeExcel = new HashSet<ExcelViewInfo>();

	@OneToMany(mappedBy = "toShape", fetch = FetchType.LAZY)
	private Set<TreeConnector> treeToShape = new HashSet<TreeConnector>();

	@OneToMany(mappedBy = "limitShapeBenefitOptionOverrideId.matrixShapeBig3COLimitXXrefId.matrixShapeBig3COXrefId.treeShape", fetch = FetchType.LAZY)
	private Set<LimitShapeBenefitOptionOverride> treeShapeOptionOverride = new HashSet<LimitShapeBenefitOptionOverride>();

	@OneToMany(mappedBy = "matrixShapeBig3COLimitXXrefId.matrixShapeBig3COXrefId.treeShape", fetch = FetchType.LAZY)
	private Set<MatrixShapeBig3COLimitXXref> treeShapeLimitXref = new HashSet<MatrixShapeBig3COLimitXXref>();

	@OneToMany(mappedBy = "matrixShapeBig3COMessageXXrefId.matrixShapeBig3COXrefId.treeShape", fetch = FetchType.LAZY)
	private Set<MatrixShapeBig3COMessageXXref> treeShapeMessageXref = new HashSet<MatrixShapeBig3COMessageXXref>();

	@OneToMany(mappedBy = "matrixShapeBig3COXrefId.treeShape", fetch = FetchType.LAZY)
	private Set<MatrixShapeBig3COXref> treeShapeBigXref = new HashSet<MatrixShapeBig3COXref>();

	@OneToMany(mappedBy = "messageShapeBenefitOptionOverrideId.matrixShapeBig3COMessageXXrefId.matrixShapeBig3COXrefId.treeShape", fetch = FetchType.LAZY)
	private Set<MessageShapeBenefitOptionOverride> treeShapeMessageOption = new HashSet<MessageShapeBenefitOptionOverride>();

	@OneToMany(mappedBy = "shapeValueBenefitOptionForBig3COId.treeShape", fetch = FetchType.LAZY)
	private Set<ShapeValueBenefitOptionForBig3CO> shapeValueIntBig3Co = new HashSet<ShapeValueBenefitOptionForBig3CO>();

	@OneToMany(mappedBy = "shapeValueBig3COId.treeShape", fetch = FetchType.LAZY)
	private Set<ShapeValueBig3CO> shapeIntBig3Co = new HashSet<ShapeValueBig3CO>();

	@OneToMany(mappedBy = "shapeValueTreeInstanceId.treeShape", fetch = FetchType.LAZY)
	private Set<ShapeValueTreeInstance> shapeIntstanceTree = new HashSet<ShapeValueTreeInstance>();

	@OneToOne(mappedBy = "treeShape", fetch = FetchType.LAZY)
	private ShapeValueDefault shapedefault;

	public Set<BenefitOptionTierdata> getAllowedBenCtrShape() {
		return allowedBenCtrShape;
	}

	public void setAllowedBenCtrShape(
			Set<BenefitOptionTierdata> allowedBenCtrShape) {
		this.allowedBenCtrShape = allowedBenCtrShape;
	}

	public Set<BenefitOptionTierdata> getCopayBenShape() {
		return copayBenShape;
	}

	public void setCopayBenShape(Set<BenefitOptionTierdata> copayBenShape) {
		this.copayBenShape = copayBenShape;
	}

	public Set<BenefitOptionTierdata> getAllowedBenAmtShape() {
		return allowedBenAmtShape;
	}

	public void setAllowedBenAmtShape(
			Set<BenefitOptionTierdata> allowedBenAmtShape) {
		this.allowedBenAmtShape = allowedBenAmtShape;
	}

	public Set<BenefitOptionTierdata> getCoinsuranceBenNoValShape() {
		return coinsuranceBenNoValShape;
	}

	public void setCoinsuranceBenNoValShape(
			Set<BenefitOptionTierdata> coinsuranceBenNoValShape) {
		this.coinsuranceBenNoValShape = coinsuranceBenNoValShape;
	}

	public Set<BenefitOptionTierdata> getCoinsuranceBenYesValShape() {
		return coinsuranceBenYesValShape;
	}

	public void setCoinsuranceBenYesValShape(
			Set<BenefitOptionTierdata> coinsuranceBenYesValShape) {
		this.coinsuranceBenYesValShape = coinsuranceBenYesValShape;
	}

	public Set<BenefitOptionTierdata> getApplyBenCoinsuranceShape() {
		return applyBenCoinsuranceShape;
	}

	public void setApplyBenCoinsuranceShape(
			Set<BenefitOptionTierdata> applyBenCoinsuranceShape) {
		this.applyBenCoinsuranceShape = applyBenCoinsuranceShape;
	}

	public Integer getTreeShape1up() {
		return treeShape1up;
	}

	public ShapeValueDefault getShapedefault() {
		return shapedefault;
	}

	public void setShapedefault(ShapeValueDefault shapedefault) {
		this.shapedefault = shapedefault;
	}

	public void setTreeShape1up(Integer treeShape1up) {
		this.treeShape1up = treeShape1up;
	}

	public TemplateTree getTemplateTree() {
		return templateTree;
	}

	public void setTemplateTree(TemplateTree templateTree) {
		this.templateTree = templateTree;
	}

	public TreeShapeType getTreeShapeType() {
		return treeShapeType;
	}

	public void setTreeShapeType(TreeShapeType treeShapeType) {
		this.treeShapeType = treeShapeType;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public IfYNTestAttribute getIfYNTestAttribute() {
		return ifYNTestAttribute;
	}

	public void setIfYNTestAttribute(IfYNTestAttribute ifYNTestAttribute) {
		this.ifYNTestAttribute = ifYNTestAttribute;
	}

	public SetValueParam getSetValueParam() {
		return setValueParam;
	}

	public void setSetValueParam(SetValueParam setValueParam) {
		this.setValueParam = setValueParam;
	}

	public SwitchCriteria getSwitchCriteria() {
		return switchCriteria;
	}

	public void setSwitchCriteria(SwitchCriteria switchCriteria) {
		this.switchCriteria = switchCriteria;
	}

	public String getCollectionLabel() {
		return collectionLabel;
	}

	public void setCollectionLabel(String collectionLabel) {
		this.collectionLabel = collectionLabel;
	}

	public String getCollectionDescription() {
		return collectionDescription;
	}

	public void setCollectionDescription(String collectionDescription) {
		this.collectionDescription = collectionDescription;
	}

	public String getGoXamXMLBlob() {
		return goXamXMLBlob;
	}

	public void setGoXamXMLBlob(String goXamXMLBlob) {
		this.goXamXMLBlob = goXamXMLBlob;
	}

	public Set<BenefitSelectionTierdata> getAllowedCtrShape() {
		return allowedCtrShape;
	}

	public void setAllowedCtrShape(Set<BenefitSelectionTierdata> allowedCtrShape) {
		this.allowedCtrShape = allowedCtrShape;
	}

	public Set<BenefitSelectionTierdata> getCopayShape() {
		return copayShape;
	}

	public void setCopayShape(Set<BenefitSelectionTierdata> copayShape) {
		this.copayShape = copayShape;
	}

	public Set<BenefitSelectionTierdata> getAllowedAmtShape() {
		return allowedAmtShape;
	}

	public void setAllowedAmtShape(Set<BenefitSelectionTierdata> allowedAmtShape) {
		this.allowedAmtShape = allowedAmtShape;
	}

	public Set<BenefitSelectionTierdata> getCoinsuranceNoValShape() {
		return coinsuranceNoValShape;
	}

	public void setCoinsuranceNoValShape(
			Set<BenefitSelectionTierdata> coinsuranceNoValShape) {
		this.coinsuranceNoValShape = coinsuranceNoValShape;
	}

	public Set<BenefitSelectionTierdata> getCoinsuranceYesValShape() {
		return coinsuranceYesValShape;
	}

	public void setCoinsuranceYesValShape(
			Set<BenefitSelectionTierdata> coinsuranceYesValShape) {
		this.coinsuranceYesValShape = coinsuranceYesValShape;
	}

	public Set<BenefitSelectionTierdata> getApplyCoinsuranceShape() {
		return applyCoinsuranceShape;
	}

	public void setApplyCoinsuranceShape(
			Set<BenefitSelectionTierdata> applyCoinsuranceShape) {
		this.applyCoinsuranceShape = applyCoinsuranceShape;
	}

	public Set<MatrixShapeLimitXref> getMasterShapeTree() {
		return masterShapeTree;
	}

	public void setMasterShapeTree(Set<MatrixShapeLimitXref> masterShapeTree) {
		this.masterShapeTree = masterShapeTree;
	}

	public Set<MatrixShapeMessageXref> getMasterMessageXrefTree() {
		return masterMessageXrefTree;
	}

	public void setMasterMessageXrefTree(
			Set<MatrixShapeMessageXref> masterMessageXrefTree) {
		this.masterMessageXrefTree = masterMessageXrefTree;
	}

	public Set<TreeConnector> getTreeFromShape() {
		return treeFromShape;
	}

	public void setTreeFromShape(Set<TreeConnector> treeFromShape) {
		this.treeFromShape = treeFromShape;
	}

	public Set<ExcelViewInfo> getTreeShapeExcel() {
		return treeShapeExcel;
	}

	public void setTreeShapeExcel(Set<ExcelViewInfo> treeShapeExcel) {
		this.treeShapeExcel = treeShapeExcel;
	}

	public Set<TreeConnector> getTreeToShape() {
		return treeToShape;
	}

	public void setTreeToShape(Set<TreeConnector> treeToShape) {
		this.treeToShape = treeToShape;
	}

	public Set<LimitShapeBenefitOptionOverride> getTreeShapeOptionOverride() {
		return treeShapeOptionOverride;
	}

	public void setTreeShapeOptionOverride(
			Set<LimitShapeBenefitOptionOverride> treeShapeOptionOverride) {
		this.treeShapeOptionOverride = treeShapeOptionOverride;
	}

	public Set<MatrixShapeBig3COLimitXXref> getTreeShapeLimitXref() {
		return treeShapeLimitXref;
	}

	public void setTreeShapeLimitXref(
			Set<MatrixShapeBig3COLimitXXref> treeShapeLimitXref) {
		this.treeShapeLimitXref = treeShapeLimitXref;
	}

	public Set<MatrixShapeBig3COMessageXXref> getTreeShapeMessageXref() {
		return treeShapeMessageXref;
	}

	public void setTreeShapeMessageXref(
			Set<MatrixShapeBig3COMessageXXref> treeShapeMessageXref) {
		this.treeShapeMessageXref = treeShapeMessageXref;
	}

	public Set<MatrixShapeBig3COXref> getTreeShapeBigXref() {
		return treeShapeBigXref;
	}

	public void setTreeShapeBigXref(Set<MatrixShapeBig3COXref> treeShapeBigXref) {
		this.treeShapeBigXref = treeShapeBigXref;
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

	public Set<ShapeValueTreeInstance> getShapeIntstanceTree() {
		return shapeIntstanceTree;
	}

	public void setShapeIntstanceTree(
			Set<ShapeValueTreeInstance> shapeIntstanceTree) {
		this.shapeIntstanceTree = shapeIntstanceTree;
	}

}
