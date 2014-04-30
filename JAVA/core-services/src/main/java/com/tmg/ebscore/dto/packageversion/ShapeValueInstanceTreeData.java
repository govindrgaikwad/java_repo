package com.tmg.ebscore.dto.packageversion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tmg.ebscore.dto.CoreCommonAttributes;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ShapeValueInstanceTreeData", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "ShapeValueInstanceTreeData", namespace = "http://www.tmg.com/coreservices/data")
public class ShapeValueInstanceTreeData extends CoreCommonAttributes {

	@XmlElement(name = "TreeShape1up")
	private Integer treeShape1up;

	@XmlElement(name = "PkgVersionInstanceTree1up")
	private Integer pkgVersionInstanceTree1up;

	@XmlElement(name = "IsOverrideAllowedFlag")
	private Boolean isOverrideAllowedFlag;

	@XmlElement(name = "Note2")
	private String note2;

	@XmlElement(name = "IfYNTestAnswerYesFlag")
	private Boolean ifYNTestAnswerYesFlag;

	@XmlElement(name = "IfYNTestAnswerNoFlag")
	private Boolean ifYNTestAnswerNoFlag;

	@XmlElement(name = "setValueValueAsInt1up")
	private Integer setValueValueAsInt1up;

	@XmlElement(name = "SetValueValueAsString1up")
	private Integer setValueValueAsString1up;

	public Integer getTreeShape1up() {
		return treeShape1up;
	}

	public void setTreeShape1up(Integer treeShape1up) {
		this.treeShape1up = treeShape1up;
	}

	public Integer getPkgVersionInstanceTree1up() {
		return pkgVersionInstanceTree1up;
	}

	public void setPkgVersionInstanceTree1up(Integer pkgVersionInstanceTree1up) {
		this.pkgVersionInstanceTree1up = pkgVersionInstanceTree1up;
	}

	public Boolean getIsOverrideAllowedFlag() {
		return isOverrideAllowedFlag;
	}

	public void setIsOverrideAllowedFlag(Boolean isOverrideAllowedFlag) {
		this.isOverrideAllowedFlag = isOverrideAllowedFlag;
	}

	public String getNote2() {
		return note2;
	}

	public void setNote2(String note2) {
		this.note2 = note2;
	}

	public Boolean getIfYNTestAnswerYesFlag() {
		return ifYNTestAnswerYesFlag;
	}

	public void setIfYNTestAnswerYesFlag(Boolean ifYNTestAnswerYesFlag) {
		this.ifYNTestAnswerYesFlag = ifYNTestAnswerYesFlag;
	}

	public Boolean getIfYNTestAnswerNoFlag() {
		return ifYNTestAnswerNoFlag;
	}

	public void setIfYNTestAnswerNoFlag(Boolean ifYNTestAnswerNoFlag) {
		this.ifYNTestAnswerNoFlag = ifYNTestAnswerNoFlag;
	}

	public Integer getSetValueValueAsInt1up() {
		return setValueValueAsInt1up;
	}

	public void setSetValueValueAsInt1up(Integer setValueValueAsInt1up) {
		this.setValueValueAsInt1up = setValueValueAsInt1up;
	}

	public Integer getSetValueValueAsString1up() {
		return setValueValueAsString1up;
	}

	public void setSetValueValueAsString1up(Integer setValueValueAsString1up) {
		this.setValueValueAsString1up = setValueValueAsString1up;
	}

}
