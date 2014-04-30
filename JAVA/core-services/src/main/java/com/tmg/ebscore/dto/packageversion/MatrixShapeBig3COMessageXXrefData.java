package com.tmg.ebscore.dto.packageversion;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tmg.ebscore.dto.CoreCommonAttributes;
import com.tmg.ebscore.dto.masterlist.MasterListMessageValData;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MatrixShapeBig3COMessageXXrefData", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "MatrixShapeBig3COMessageXXrefData", namespace = "http://www.tmg.com/coreservices/data")
public class MatrixShapeBig3COMessageXXrefData extends CoreCommonAttributes {
	
	@XmlElement(name = "MasterListMessageVal1up")
	private Integer masterListMessageVal1up;

	@XmlElement(name = "MasterListMessageVal")
	private MasterListMessageValData masterListMessageVal;

	@XmlElement(name = "TreeShape1up")
	private Integer treeShape1up;

	@XmlElement(name = "PkgVerBig3CO1up")
	private Integer pkgVerBig3CO1up;

	@XmlElement(name = "BenefitOption1up")
	private Integer benefitOption1up;

	@Column(name = "IsEnableFlag")
	private Boolean enableFlag;

	@Column(name = "IsMappedFlag")
	private Boolean mappedFlag;

	public Integer getBenefitOption1up() {
		return benefitOption1up;
	}

	public void setBenefitOption1up(Integer benefitOption1up) {
		this.benefitOption1up = benefitOption1up;
	}

	public Integer getMasterListMessageVal1up() {
		return masterListMessageVal1up;
	}

	public void setMasterListMessageVal1up(Integer masterListMessageVal1up) {
		this.masterListMessageVal1up = masterListMessageVal1up;
	}

	public Integer getTreeShape1up() {
		return treeShape1up;
	}

	public void setTreeShape1up(Integer treeShape1up) {
		this.treeShape1up = treeShape1up;
	}

	public Integer getPkgVerBig3CO1up() {
		return pkgVerBig3CO1up;
	}

	public void setPkgVerBig3CO1up(Integer pkgVerBig3CO1up) {
		this.pkgVerBig3CO1up = pkgVerBig3CO1up;
	}

	public Boolean getEnableFlag() {
		return enableFlag;
	}

	public void setEnableFlag(Boolean enableFlag) {
		this.enableFlag = enableFlag;
	}

	public Boolean getMappedFlag() {
		return mappedFlag;
	}

	public void setMappedFlag(Boolean mappedFlag) {
		this.mappedFlag = mappedFlag;
	}

	public MasterListMessageValData getMasterListMessageVal() {
		return masterListMessageVal;
	}

	public void setMasterListMessageVal(
			MasterListMessageValData masterListMessageVal) {
		this.masterListMessageVal = masterListMessageVal;
	}

}
