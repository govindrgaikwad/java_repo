package com.tmg.ebscore.dto.template;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tmg.ebscore.dto.CoreCommonAttributes;
import com.tmg.ebscore.dto.masterlist.MasterListIntValData;
import com.tmg.ebscore.dto.masterlist.MasterListStringValData;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ShapeValueDefaultData", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "ShapeValueDefaultData", namespace = "http://www.tmg.com/coreservices/data")
public class ShapeValueDefaultData extends CoreCommonAttributes {

	@XmlElement(name = "TreeShape1up")
	private Integer treeShape1up;

	@XmlElement(name = "TemplateTree1up")
	private Integer templateTree1up;

	@XmlElement(name = "ApplyYesFlag")
	private Boolean applyYesFlag;

	@XmlElement(name = "ApplyNoFlag")
	private Boolean applyNoFlag;

	@XmlElement(name = "IntVal1up")
	private Integer intVal1up;

	@XmlElement(name = "IntValData")
	private MasterListIntValData intValData;

	@XmlElement(name = "StringVal1up")
	private Integer stringVal1up;

	@XmlElement(name = "StringValData")
	private MasterListStringValData stringValData;

	public Integer getTreeShape1up() {
		return treeShape1up;
	}

	public void setTreeShape1up(Integer treeShape1up) {
		this.treeShape1up = treeShape1up;
	}

	public Integer getTemplateTree1up() {
		return templateTree1up;
	}

	public void setTemplateTree1up(Integer templateTree1up) {
		this.templateTree1up = templateTree1up;
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

	public Integer getIntVal1up() {
		return intVal1up;
	}

	public void setIntVal1up(Integer intVal1up) {
		this.intVal1up = intVal1up;
	}

	public Integer getStringVal1up() {
		return stringVal1up;
	}

	public void setStringVal1up(Integer stringVal1up) {
		this.stringVal1up = stringVal1up;
	}

	public MasterListIntValData getIntValData() {
		return intValData;
	}

	public void setIntValData(MasterListIntValData intValData) {
		this.intValData = intValData;
	}

	public MasterListStringValData getStringValData() {
		return stringValData;
	}

	public void setStringValData(MasterListStringValData stringValData) {
		this.stringValData = stringValData;
	}

}
