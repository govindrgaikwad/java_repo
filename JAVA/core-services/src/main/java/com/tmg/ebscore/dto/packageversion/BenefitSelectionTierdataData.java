package com.tmg.ebscore.dto.packageversion;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tmg.ebscore.dto.CoreCommonAttributes;
import com.tmg.ebscore.dto.masterlist.MasterListIntValData;
import com.tmg.ebscore.dto.masterlist.MasterListServiceDefinitionData;
import com.tmg.ebscore.dto.masterlist.MasterListStringValData;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BenefitSelectionTierdataData", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "BenefitSelectionTierdataData", namespace = "http://www.tmg.com/coreservices/data")
public class BenefitSelectionTierdataData extends CoreCommonAttributes {

	@XmlElement(name = "Id")
	private Integer id;

	@XmlElement(name = "pkgVerBig3CO1up")
	private Integer pkgVerBig3CO1up;

	@XmlElement(name = "BenefitOption1up")
	private Integer benefitOption1up;

	@XmlElement(name = "CommonBenefitData")
	private PkgVerInstanceTreeData pkgVerInstanceTreeData;

	@XmlElement(name = "PkgVerInstanceTree1up")
	private Integer pkgVerInstanceTree1up;

	@XmlElement(name = "MasterListServiceDefinitionData")
	private MasterListServiceDefinitionData masterListServiceDefinitionData;

	@XmlElement(name = "BenefitRuleData")
	private MasterListStringValData masterListStringValData;

	@XmlElement(name = "NetworkStringVal1up")
	private Integer networkStringVal1up;

	@XmlElement(name = "IsCovered")
	private Boolean isCovered;

	@XmlElement(name = "TreatAs100")
	private Boolean treatAs100;

	@XmlElement(name = "applyCoinsurance")
	private Boolean applyCoinsurance;

	@XmlElement(name = "applyCoinsuranceShape")
	private Integer applyCoinsuranceShape;

	@XmlElement(name = "coinsuranceYesValShape")
	private Integer coinsuranceYesValShape;

	@XmlElement(name = "CoinsuranceYesValData")
	private MasterListIntValData coinsuranceYesValData;

	@XmlElement(name = "CoinsuranceYesVal1up")
	private Integer coinsuranceYesVal1up;

	@XmlElement(name = "coinsuranceNoValShape")
	private Integer coinsuranceNoValShape;

	@XmlElement(name = "CoinsuranceNoValData")
	private MasterListStringValData coinsuranceNoValData;

	@XmlElement(name = "CoinsuranceNoVal1up")
	private Integer coinsuranceNoVal1up;

	@XmlElement(name = "CopayShape")
	private Integer copayShape;

	@XmlElement(name = "CopayValData")
	private MasterListIntValData copayValData;

	@XmlElement(name = "CopayVal1up")
	private Integer copayVal1up;

	@XmlElement(name = "AllowedAmtShape")
	private Integer allowedAmtShape;

	@XmlElement(name = "AllowedAmountData")
	private MasterListIntValData allowedAmountData;

	@XmlElement(name = "AllowedAmount1up")
	private Integer allowedAmount1up;

	@XmlElement(name = "AllowedCtrShape")
	private Integer allowedCtrShape;

	@XmlElement(name = "AllowedCtrData")
	private MasterListIntValData allowedCtrData;

	@XmlElement(name = "AllowedCtr1up")
	private Integer allowedCtr1up;

	@XmlElement(name = "TierNo")
	private Integer tierNo;

	@XmlElement(name = "applyDeductible")
	private Boolean applyDeductible;

	@XmlElement(name = "ApplyDeductibleShape")
	private Integer applyDeductibleShape;

	// @XmlElement(name = "IndividualDeductible")
	// private MasterListIntValData applyIndDeductible;

	@XmlElement(name = "ApplyIndDeductibleShape")
	private Integer applyIndDeductibleShape;

	@XmlElement(name = "ApplyIndDeductible1up")
	private Integer applyIndDeductible1up;

	// @XmlElement(name = "FamilyDeductible")
	// private MasterListIntValData applyFamDeductible;

	@XmlElement(name = "Deductible")
	private List<DeductibleData> deductibleData;

	@XmlElement(name = "ApplyFamDeductibleShape")
	private Integer applyFamDeductibleShape;

	@XmlElement(name = "ApplyFamDeductible1up")
	private Integer applyFamDeductible1up;

	@XmlElement(name = "noDeductible")
	private MasterListStringValData applyDeductibleNo;

	@XmlElement(name = "ApplyDeductibleNoShape")
	private Integer applyDeductibleNoShape;

	@XmlElement(name = "ApplyDeductibleNo1up")
	private Integer applyDeductibleNo1up;

	@XmlElement(name = "MessageCount")
	private Integer messageCount;

	@XmlElement(name = "MessageShape")
	private Integer messageShape;

	@XmlElement(name = "LimitCount")
	private Integer limitCount;

	@XmlElement(name = "LimitShape")
	private Integer limitShape;

	@XmlElement(name = "FacetShape")
	private Integer facetShape;

	@XmlElement(name = "ServiceRuleShape")
	private Integer serviceRuleShape;

	@XmlElement(name = "LimitData")
	private List<MatrixShapeBig3COLimitXXrefData> limitData;

	@XmlElement(name = "messageData")
	private List<MatrixShapeBig3COMessageXXrefData> messageData;

	public Integer getApplyCoinsuranceShape() {
		return applyCoinsuranceShape;
	}

	public void setApplyCoinsuranceShape(Integer applyCoinsuranceShape) {
		this.applyCoinsuranceShape = applyCoinsuranceShape;
	}

	public Boolean getApplyDeductible() {
		return applyDeductible;
	}

	public void setApplyDeductible(Boolean applyDeductible) {
		this.applyDeductible = applyDeductible;
	}

	// public MasterListIntValData getApplyIndDeductible() {
	// return applyIndDeductible;
	// }
	//
	// public void setApplyIndDeductible(MasterListIntValData
	// applyIndDeductible) {
	// this.applyIndDeductible = applyIndDeductible;
	// }
	//
	// public MasterListIntValData getApplyFamDeductible() {
	// return applyFamDeductible;
	// }
	//
	// public void setApplyFamDeductible(MasterListIntValData
	// applyFamDeductible) {
	// this.applyFamDeductible = applyFamDeductible;
	// }

	public MasterListStringValData getApplyDeductibleNo() {
		return applyDeductibleNo;
	}

	public void setApplyDeductibleNo(MasterListStringValData applyDeductibleNo) {
		this.applyDeductibleNo = applyDeductibleNo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBenefitOption1up() {
		return benefitOption1up;
	}

	public void setBenefitOption1up(Integer benefitOption1up) {
		this.benefitOption1up = benefitOption1up;
	}

	public PkgVerInstanceTreeData getPkgVerInstanceTreeData() {
		return pkgVerInstanceTreeData;
	}

	public void setPkgVerInstanceTreeData(
			PkgVerInstanceTreeData pkgVerInstanceTreeData) {
		this.pkgVerInstanceTreeData = pkgVerInstanceTreeData;
	}

	public MasterListServiceDefinitionData getMasterListServiceDefinitionData() {
		return masterListServiceDefinitionData;
	}

	public void setMasterListServiceDefinitionData(
			MasterListServiceDefinitionData masterListServiceDefinitionData) {
		this.masterListServiceDefinitionData = masterListServiceDefinitionData;
	}

	public MasterListStringValData getMasterListStringValData() {
		return masterListStringValData;
	}

	public void setMasterListStringValData(
			MasterListStringValData masterListStringValData) {
		this.masterListStringValData = masterListStringValData;
	}

	public Boolean getIsCovered() {
		return isCovered;
	}

	public void setIsCovered(Boolean isCovered) {
		this.isCovered = isCovered;
	}

	public Boolean getTreatAs100() {
		return treatAs100;
	}

	public void setTreatAs100(Boolean treatAs100) {
		this.treatAs100 = treatAs100;
	}

	public Integer getPkgVerBig3CO1up() {
		return pkgVerBig3CO1up;
	}

	public void setPkgVerBig3CO1up(Integer pkgVerBig3CO1up) {
		this.pkgVerBig3CO1up = pkgVerBig3CO1up;
	}

	public Boolean getApplyCoinsurance() {
		return applyCoinsurance;
	}

	public void setApplyCoinsurance(Boolean applyCoinsurance) {
		this.applyCoinsurance = applyCoinsurance;
	}

	public Integer getCoinsuranceYesValShape() {
		return coinsuranceYesValShape;
	}

	public void setCoinsuranceYesValShape(Integer coinsuranceYesValShape) {
		this.coinsuranceYesValShape = coinsuranceYesValShape;
	}

	public MasterListIntValData getCoinsuranceYesValData() {
		return coinsuranceYesValData;
	}

	public void setCoinsuranceYesValData(
			MasterListIntValData coinsuranceYesValData) {
		this.coinsuranceYesValData = coinsuranceYesValData;
	}

	public Integer getCoinsuranceNoValShape() {
		return coinsuranceNoValShape;
	}

	public void setCoinsuranceNoValShape(Integer coinsuranceNoValShape) {
		this.coinsuranceNoValShape = coinsuranceNoValShape;
	}

	public MasterListStringValData getCoinsuranceNoValData() {
		return coinsuranceNoValData;
	}

	public void setCoinsuranceNoValData(
			MasterListStringValData coinsuranceNoValData) {
		this.coinsuranceNoValData = coinsuranceNoValData;
	}

	public Integer getCopayShape() {
		return copayShape;
	}

	public void setCopayShape(Integer copayShape) {
		this.copayShape = copayShape;
	}

	public MasterListIntValData getCopayValData() {
		return copayValData;
	}

	public void setCopayValData(MasterListIntValData copayValData) {
		this.copayValData = copayValData;
	}

	public Integer getAllowedAmtShape() {
		return allowedAmtShape;
	}

	public void setAllowedAmtShape(Integer allowedAmtShape) {
		this.allowedAmtShape = allowedAmtShape;
	}

	public MasterListIntValData getAllowedAmountData() {
		return allowedAmountData;
	}

	public void setAllowedAmountData(MasterListIntValData allowedAmountData) {
		this.allowedAmountData = allowedAmountData;
	}

	public Integer getAllowedCtrShape() {
		return allowedCtrShape;
	}

	public void setAllowedCtrShape(Integer allowedCtrShape) {
		this.allowedCtrShape = allowedCtrShape;
	}

	public MasterListIntValData getAllowedCtrData() {
		return allowedCtrData;
	}

	public void setAllowedCtrData(MasterListIntValData allowedCtrData) {
		this.allowedCtrData = allowedCtrData;
	}

	public Integer getTierNo() {
		return tierNo;
	}

	public void setTierNo(Integer tierNo) {
		this.tierNo = tierNo;
	}

	public Integer getMessageCount() {
		return messageCount;
	}

	public void setMessageCount(Integer messageCount) {
		this.messageCount = messageCount;
	}

	public Integer getLimitCount() {
		return limitCount;
	}

	public void setLimitCount(Integer limitCount) {
		this.limitCount = limitCount;
	}

	public Integer getPkgVerInstanceTree1up() {
		return pkgVerInstanceTree1up;
	}

	public void setPkgVerInstanceTree1up(Integer pkgVerInstanceTree1up) {
		this.pkgVerInstanceTree1up = pkgVerInstanceTree1up;
	}

	public Integer getNetworkStringVal1up() {
		return networkStringVal1up;
	}

	public void setNetworkStringVal1up(Integer networkStringVal1up) {
		this.networkStringVal1up = networkStringVal1up;
	}

	public Integer getCoinsuranceYesVal1up() {
		return coinsuranceYesVal1up;
	}

	public void setCoinsuranceYesVal1up(Integer coinsuranceYesVal1up) {
		this.coinsuranceYesVal1up = coinsuranceYesVal1up;
	}

	public Integer getCoinsuranceNoVal1up() {
		return coinsuranceNoVal1up;
	}

	public void setCoinsuranceNoVal1up(Integer coinsuranceNoVal1up) {
		this.coinsuranceNoVal1up = coinsuranceNoVal1up;
	}

	public Integer getCopayVal1up() {
		return copayVal1up;
	}

	public void setCopayVal1up(Integer copayVal1up) {
		this.copayVal1up = copayVal1up;
	}

	public Integer getAllowedAmount1up() {
		return allowedAmount1up;
	}

	public void setAllowedAmount1up(Integer allowedAmount1up) {
		this.allowedAmount1up = allowedAmount1up;
	}

	public Integer getAllowedCtr1up() {
		return allowedCtr1up;
	}

	public void setAllowedCtr1up(Integer allowedCtr1up) {
		this.allowedCtr1up = allowedCtr1up;
	}

	public Integer getApplyIndDeductible1up() {
		return applyIndDeductible1up;
	}

	public void setApplyIndDeductible1up(Integer applyIndDeductible1up) {
		this.applyIndDeductible1up = applyIndDeductible1up;
	}

	public Integer getApplyFamDeductible1up() {
		return applyFamDeductible1up;
	}

	public void setApplyFamDeductible1up(Integer applyFamDeductible1up) {
		this.applyFamDeductible1up = applyFamDeductible1up;
	}

	public Integer getApplyDeductibleNo1up() {
		return applyDeductibleNo1up;
	}

	public void setApplyDeductibleNo1up(Integer applyDeductibleNo1up) {
		this.applyDeductibleNo1up = applyDeductibleNo1up;
	}

	public Integer getApplyDeductibleShape() {
		return applyDeductibleShape;
	}

	public void setApplyDeductibleShape(Integer applyDeductibleShape) {
		this.applyDeductibleShape = applyDeductibleShape;
	}

	public Integer getApplyIndDeductibleShape() {
		return applyIndDeductibleShape;
	}

	public void setApplyIndDeductibleShape(Integer applyIndDeductibleShape) {
		this.applyIndDeductibleShape = applyIndDeductibleShape;
	}

	public Integer getApplyFamDeductibleShape() {
		return applyFamDeductibleShape;
	}

	public void setApplyFamDeductibleShape(Integer applyFamDeductibleShape) {
		this.applyFamDeductibleShape = applyFamDeductibleShape;
	}

	public Integer getApplyDeductibleNoShape() {
		return applyDeductibleNoShape;
	}

	public void setApplyDeductibleNoShape(Integer applyDeductibleNoShape) {
		this.applyDeductibleNoShape = applyDeductibleNoShape;
	}

	public Integer getMessageShape() {
		return messageShape;
	}

	public void setMessageShape(Integer messageShape) {
		this.messageShape = messageShape;
	}

	public Integer getLimitShape() {
		return limitShape;
	}

	public void setLimitShape(Integer limitShape) {
		this.limitShape = limitShape;
	}

	public Integer getServiceRuleShape() {
		return serviceRuleShape;
	}

	public void setServiceRuleShape(Integer serviceRuleShape) {
		this.serviceRuleShape = serviceRuleShape;
	}

	public Integer getFacetShape() {
		return facetShape;
	}

	public void setFacetShape(Integer facetShape) {
		this.facetShape = facetShape;
	}

	public List<MatrixShapeBig3COLimitXXrefData> getLimitData() {
		return limitData;
	}

	public void setLimitData(List<MatrixShapeBig3COLimitXXrefData> limitData) {
		this.limitData = limitData;
	}

	public List<MatrixShapeBig3COMessageXXrefData> getMessageData() {
		return messageData;
	}

	public void setMessageData(
			List<MatrixShapeBig3COMessageXXrefData> messageData) {
		this.messageData = messageData;
	}

	public List<DeductibleData> getDeductibleData() {
		return deductibleData;
	}

	public void setDeductibleData(List<DeductibleData> deductibleData) {
		this.deductibleData = deductibleData;
	}

}
