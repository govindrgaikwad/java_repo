package com.tmg.ebscore.dto.template;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tmg.ebscore.dto.CoreCommonAttributes;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PkgVersionWorkflowStateData", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "PkgVersionWorkflowStateData", namespace = "http://www.tmg.com/coreservices/data")
public class PkgVersionWorkflowStateData extends CoreCommonAttributes {

	@XmlElement(name = "PackageTemplate1Up")
	private Integer packageTemplate1Up;

	@XmlElement(name = "PkgVersionWorkflowState1up")
	private Integer pkgVersionWorkflowState1up;

	@XmlElement(name = "Name")
	private String name;

	@XmlElement(name = "Description")
	private String description;

	@XmlElement(name = "IsReleasedFlag")
	private Boolean releasedFlag;

	@XmlElement(name = "IsDefaultValFlag")
	private Boolean defaultValFlag;

	@XmlElement(name = "DisplayOrder")
	private Integer displayOrder;

	@XmlElement(name = "NoOfPackages")
	private Integer noOfPackages;

	public Integer getPackageTemplate1Up() {
		return packageTemplate1Up;
	}

	public void setPackageTemplate1Up(Integer packageTemplate1Up) {
		this.packageTemplate1Up = packageTemplate1Up;
	}

	public Integer getPkgVersionWorkflowState1up() {
		return pkgVersionWorkflowState1up;
	}

	public void setPkgVersionWorkflowState1up(Integer pkgVersionWorkflowState1up) {
		this.pkgVersionWorkflowState1up = pkgVersionWorkflowState1up;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getReleasedFlag() {
		return releasedFlag;
	}

	public void setReleasedFlag(Boolean releasedFlag) {
		this.releasedFlag = releasedFlag;
	}

	public Boolean getDefaultValFlag() {
		return defaultValFlag;
	}

	public void setDefaultValFlag(Boolean defaultValFlag) {
		this.defaultValFlag = defaultValFlag;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public Integer getNoOfPackages() {
		return noOfPackages;
	}

	public void setNoOfPackages(Integer noOfPackages) {
		this.noOfPackages = noOfPackages;
	}

}
