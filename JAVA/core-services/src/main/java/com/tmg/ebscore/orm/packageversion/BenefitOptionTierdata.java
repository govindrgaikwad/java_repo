package com.tmg.ebscore.orm.packageversion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tmg.ebscore.orm.CoreCommonAttributes;
import com.tmg.ebscore.orm.masterlist.MasterListIntVal;
import com.tmg.ebscore.orm.masterlist.MasterListStringVal;
import com.tmg.ebscore.orm.template.TreeShape;

@Entity
@Table(schema = "Pkg", name = "BenefitOptionTierdata")
public class BenefitOptionTierdata extends CoreCommonAttributes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BenefitOptionTierdata1up")
	private Integer benefitOptionTierdata1up;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BenefitOption1up")
	private PkgVerBenefitOption benefitOption;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PkgVerInstanceTree1up")
	private PkgVerInstanceTree pkgVerInstanceTree;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PkgVerBig3CO1up")
	private PkgVerBig3CO pkgVerBig3CO;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ApplyCoinsuranceShape1up")
	private TreeShape applyCoinsuranceShape;

	@Column(name = "ApplyCoinsurance")
	private Boolean applyCoinsurance;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CoinsuranceYesValShape1up")
	private TreeShape coinsuranceYesValShape;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CoinsuranceYesVal1up")
	private MasterListIntVal coinsuranceYesVal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CoinsuranceNoValShape1up")
	private TreeShape coinsuranceNoValShape;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CoinsuranceNoVal1up")
	private MasterListStringVal coinsuranceNoVal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CopayShape1up")
	private TreeShape copayShape;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CopayVal1up")
	private MasterListIntVal copayVal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AllowedAmtShape1up")
	private TreeShape allowedAmtShape;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AllowedAmt1up")
	private MasterListIntVal allowedAmt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AllowedCtrShape1up")
	private TreeShape allowedCtrShape;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AllowedCtr1up")
	private MasterListIntVal allowedCtr;

	@Column(name = "TierNo")
	private Integer tierNo;

	public Integer getBenefitOptionTierdata1up() {
		return benefitOptionTierdata1up;
	}

	public void setBenefitOptionTierdata1up(Integer benefitOptionTierdata1up) {
		this.benefitOptionTierdata1up = benefitOptionTierdata1up;
	}

	public PkgVerBenefitOption getBenefitOption() {
		return benefitOption;
	}

	public void setBenefitOption(PkgVerBenefitOption benefitOption) {
		this.benefitOption = benefitOption;
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

	public TreeShape getApplyCoinsuranceShape() {
		return applyCoinsuranceShape;
	}

	public void setApplyCoinsuranceShape(TreeShape applyCoinsuranceShape) {
		this.applyCoinsuranceShape = applyCoinsuranceShape;
	}

	public Boolean getApplyCoinsurance() {
		return applyCoinsurance;
	}

	public void setApplyCoinsurance(Boolean applyCoinsurance) {
		this.applyCoinsurance = applyCoinsurance;
	}

	public TreeShape getCoinsuranceYesValShape() {
		return coinsuranceYesValShape;
	}

	public void setCoinsuranceYesValShape(TreeShape coinsuranceYesValShape) {
		this.coinsuranceYesValShape = coinsuranceYesValShape;
	}

	public MasterListIntVal getCoinsuranceYesVal() {
		return coinsuranceYesVal;
	}

	public void setCoinsuranceYesVal(MasterListIntVal coinsuranceYesVal) {
		this.coinsuranceYesVal = coinsuranceYesVal;
	}

	public TreeShape getCoinsuranceNoValShape() {
		return coinsuranceNoValShape;
	}

	public void setCoinsuranceNoValShape(TreeShape coinsuranceNoValShape) {
		this.coinsuranceNoValShape = coinsuranceNoValShape;
	}

	public MasterListStringVal getCoinsuranceNoVal() {
		return coinsuranceNoVal;
	}

	public void setCoinsuranceNoVal(MasterListStringVal coinsuranceNoVal) {
		this.coinsuranceNoVal = coinsuranceNoVal;
	}

	public TreeShape getCopayShape() {
		return copayShape;
	}

	public void setCopayShape(TreeShape copayShape) {
		this.copayShape = copayShape;
	}

	public MasterListIntVal getCopayVal() {
		return copayVal;
	}

	public void setCopayVal(MasterListIntVal copayVal) {
		this.copayVal = copayVal;
	}

	public TreeShape getAllowedAmtShape() {
		return allowedAmtShape;
	}

	public void setAllowedAmtShape(TreeShape allowedAmtShape) {
		this.allowedAmtShape = allowedAmtShape;
	}

	public MasterListIntVal getAllowedAmt() {
		return allowedAmt;
	}

	public void setAllowedAmt(MasterListIntVal allowedAmt) {
		this.allowedAmt = allowedAmt;
	}

	public TreeShape getAllowedCtrShape() {
		return allowedCtrShape;
	}

	public void setAllowedCtrShape(TreeShape allowedCtrShape) {
		this.allowedCtrShape = allowedCtrShape;
	}

	public MasterListIntVal getAllowedCtr() {
		return allowedCtr;
	}

	public void setAllowedCtr(MasterListIntVal allowedCtr) {
		this.allowedCtr = allowedCtr;
	}

	public Integer getTierNo() {
		return tierNo;
	}

	public void setTierNo(Integer tierNo) {
		this.tierNo = tierNo;
	}

}
