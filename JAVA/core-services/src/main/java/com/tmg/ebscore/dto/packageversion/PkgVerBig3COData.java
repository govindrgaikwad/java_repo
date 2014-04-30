package com.tmg.ebscore.dto.packageversion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tmg.ebscore.dto.CoreCommonAttributes;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PkgVerBig3COData", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "PkgVerBig3COData", namespace = "http://www.tmg.com/coreservices/data")
public class PkgVerBig3COData extends CoreCommonAttributes {

	@XmlElement(name = "PkgVerBig3CO1up")
	private Integer pkgVerBig3CO1up;

	@XmlElement(name = "PkgMasterListForBig3CO1up")
	private Integer pkgMasterListForBig3COId;

	@XmlElement(name = "PkgVersion1up")
	private Integer pkgVersionId;

	@XmlElement(name = "InUse")
	private Boolean inUse;

	@XmlElement(name = "Enable")
	private Boolean enable;

	@XmlElement(name = "Category1")
	private String category1;

	@XmlElement(name = "Category2")
	private String category2;

	@XmlElement(name = "Category3")
	private String category3;

	@XmlElement(name = "BenefitRule1Up")
	private Integer benefitRule1Up;

	@XmlElement(name = "PlaceOfService")
	private String placeOfService;

	@XmlElement(name = "IsCovered")
	private Boolean isCovered;

	@XmlElement(name = "TreatAs100")
	private Boolean treatAs100;

	@XmlElement(name = "CommonBenefit1up")
	private Integer commonBenefit1Up;

	@XmlElement(name = "IsSETRMessageSelected")
	private Boolean sETRMessageSelected;

	public Integer getPkgVerBig3CO1up() {
		return pkgVerBig3CO1up;
	}

	public void setPkgVerBig3CO1up(Integer pkgVerBig3CO1up) {
		this.pkgVerBig3CO1up = pkgVerBig3CO1up;
	}

	public Boolean getsETRMessageSelected() {
		return sETRMessageSelected;
	}

	public void setsETRMessageSelected(Boolean sETRMessageSelected) {
		this.sETRMessageSelected = sETRMessageSelected;
	}

	public Boolean getInUse() {
		return inUse;
	}

	public void setInUse(Boolean inUse) {
		this.inUse = inUse;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public String getCategory1() {
		return category1;
	}

	public void setCategory1(String category1) {
		this.category1 = category1;
	}

	public String getCategory2() {
		return category2;
	}

	public void setCategory2(String category2) {
		this.category2 = category2;
	}

	public String getCategory3() {
		return category3;
	}

	public void setCategory3(String category3) {
		this.category3 = category3;
	}

	public Integer getBenefitRule1Up() {
		return benefitRule1Up;
	}

	public void setBenefitRule1Up(Integer benefitRule1Up) {
		this.benefitRule1Up = benefitRule1Up;
	}

	public String getPlaceOfService() {
		return placeOfService;
	}

	public void setPlaceOfService(String placeOfService) {
		this.placeOfService = placeOfService;
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

	public Integer getCommonBenefit1Up() {
		return commonBenefit1Up;
	}

	public void setCommonBenefit1Up(Integer commonBenefit1Up) {
		this.commonBenefit1Up = commonBenefit1Up;
	}

	public Integer getPkgMasterListForBig3COId() {
		return pkgMasterListForBig3COId;
	}

	public void setPkgMasterListForBig3COId(Integer pkgMasterListForBig3COId) {
		this.pkgMasterListForBig3COId = pkgMasterListForBig3COId;
	}

	public Integer getPkgVersionId() {
		return pkgVersionId;
	}

	public void setPkgVersionId(Integer pkgVersionId) {
		this.pkgVersionId = pkgVersionId;
	}

}
