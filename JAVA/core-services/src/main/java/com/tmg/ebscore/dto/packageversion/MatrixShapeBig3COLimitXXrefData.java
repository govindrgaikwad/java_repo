package com.tmg.ebscore.dto.packageversion;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tmg.ebscore.dto.masterlist.MasterListLimitValData;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MatrixShapeBig3COLimitXXrefData", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "MatrixShapeBig3COLimitXXrefData", namespace = "http://www.tmg.com/coreservices/data")
public class MatrixShapeBig3COLimitXXrefData {

	@XmlElement(name = "Enable")
	private Boolean enable;

	@XmlElement(name = "MasterListLimitVal1up")
	private Integer masterListLimitVal1up;

	@XmlElement(name = "MasterListLimitVal")
	private MasterListLimitValData masterListLimitVal;

	@XmlElement(name = "TreeShape1up")
	private Integer treeShape1up;

	@XmlElement(name = "BenefitOption1up")
	private Integer benefitOption1up;

	@XmlElement(name = "PkgVerBig3CO1up")
	private Integer pkgVerBig3CO1up;

	@Column(name = "IsMappedFlag")
	private Boolean mappedFlag;

	public Integer getBenefitOption1up() {
		return benefitOption1up;
	}

	public void setBenefitOption1up(Integer benefitOption1up) {
		this.benefitOption1up = benefitOption1up;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Integer getMasterListLimitVal1up() {
		return masterListLimitVal1up;
	}

	public void setMasterListLimitVal1up(Integer masterListLimitVal1up) {
		this.masterListLimitVal1up = masterListLimitVal1up;
	}

	public MasterListLimitValData getMasterListLimitVal() {
		return masterListLimitVal;
	}

	public void setMasterListLimitVal(MasterListLimitValData masterListLimitVal) {
		this.masterListLimitVal = masterListLimitVal;
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

	public Boolean getMappedFlag() {
		return mappedFlag;
	}

	public void setMappedFlag(Boolean mappedFlag) {
		this.mappedFlag = mappedFlag;
	}

}
