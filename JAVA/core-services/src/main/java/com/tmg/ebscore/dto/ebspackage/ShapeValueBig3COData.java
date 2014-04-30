package com.tmg.ebscore.dto.ebspackage;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tmg.ebscore.dto.CoreCommonAttributes;
import com.tmg.ebscore.dto.masterlist.MasterListIntValData;
import com.tmg.ebscore.dto.masterlist.MasterListStringValData;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ShapeValueBig3COData", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "ShapeValueBig3COData", namespace = "http://www.tmg.com/coreservices/data")
public class ShapeValueBig3COData extends CoreCommonAttributes {

	@XmlElement(name = "PkgVerInstanceTree1up")
	private Integer instanceTreeId;

	@XmlElement(name = "TreeShape1up")
	private Integer treeShape1up;

	@XmlElement(name = "PkgVerBig3CO1up")
	private Integer pkgVerBig3CO1up;

	@XmlElement(name = "SetValueValueAsInt1up")
	private Integer setValueValueAsInt1up;

	@XmlElement(name = "SetValueValueAsString1up")
	private Integer setValueValueAsString1up;

	@XmlElement(name = "AltRule")
	private String altRule;

	@XmlElement(name = "ApplyYesFlag")
	private Boolean applyYesFlag;

	@XmlElement(name = "ApplyNoFlag")
	private Boolean applyNoFlag;

	@XmlElement(name = "MasterListStringValData")
	private MasterListStringValData masterListStringValData;

	@XmlElement(name = "MasterListIntValData")
	private MasterListIntValData masterListIntValData;

	public Integer getSetValueValueAsInt1up() {
		return setValueValueAsInt1up;
	}

	public void setSetValueValueAsInt1up(Integer setValueValueAsInt1up) {
		this.setValueValueAsInt1up = setValueValueAsInt1up;
	}

	public String getAltRule() {
		return altRule;
	}

	public void setAltRule(String altRule) {
		this.altRule = altRule;
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

	public Integer getInstanceTreeId() {
		return instanceTreeId;
	}

	public void setInstanceTreeId(Integer instanceTreeId) {
		this.instanceTreeId = instanceTreeId;
	}

	public Boolean getApplyYesFlag() {
		return applyYesFlag;
	}

	public void setApplyYesFlag(Boolean applyYesFlag) {
		this.applyYesFlag = applyYesFlag;
	}

	public Boolean getApplyNoFlag() {
		return applyNoFlag;
	}

	public void setApplyNoFlag(Boolean applyNoFlag) {
		this.applyNoFlag = applyNoFlag;
	}

	public MasterListStringValData getMasterListStringValData() {
		return masterListStringValData;
	}

	public void setMasterListStringValData(
			MasterListStringValData masterListStringValData) {
		this.masterListStringValData = masterListStringValData;
	}

	public MasterListIntValData getMasterListIntValData() {
		return masterListIntValData;
	}

	public void setMasterListIntValData(
			MasterListIntValData masterListIntValData) {
		this.masterListIntValData = masterListIntValData;
	}

}
