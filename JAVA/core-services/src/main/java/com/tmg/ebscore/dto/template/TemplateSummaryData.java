package com.tmg.ebscore.dto.template;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tmg.ebscore.dto.CoreCommonAttributes;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TemplateSummaryData", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "TemplateSummaryData", namespace = "http://www.tmg.com/coreservices/data")
public class TemplateSummaryData extends CoreCommonAttributes {

	@XmlElement(name = "InUse")
	private Boolean inUse;

	@XmlElement(name = "IsLocked")
	private Boolean locked;

	@XmlElement(name = "PackageTemplate1Up")
	private Integer packageTemplate1Up;

	@XmlElement(name = "TemplateID")
	private String templateID;

	@XmlElement(name = "TemplateName")
	private String templateName;

	@XmlElement(name = "TemplateType")
	private String templateType;

	@XmlElement(name = "WorkflowState")
	private String workflowState;

	@XmlElement(name = "Released")
	private Boolean released;

	@XmlElement(name = "NoOfPackages")
	private Integer noOfPackages;

	@XmlElement(name = "NoOfUnReleasedPackageVersions")
	private Integer noOfUnReleasedPackageVersions;

	@XmlElement(name = "NoOfReleasedPackageVersions")
	private Integer noOfReleasedPackageVersions;

	public Boolean getInUse() {
		return inUse;
	}

	public void setInUse(Boolean inUse) {
		this.inUse = inUse;
	}

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public Integer getPackageTemplate1Up() {
		return packageTemplate1Up;
	}

	public void setPackageTemplate1Up(Integer packageTemplate1Up) {
		this.packageTemplate1Up = packageTemplate1Up;
	}

	public String getTemplateID() {
		return templateID;
	}

	public void setTemplateID(String templateID) {
		this.templateID = templateID;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public String getWorkflowState() {
		return workflowState;
	}

	public void setWorkflowState(String workflowState) {
		this.workflowState = workflowState;
	}

	public Boolean getReleased() {
		return released;
	}

	public void setReleased(Boolean released) {
		this.released = released;
	}

	public Integer getNoOfPackages() {
		return noOfPackages;
	}

	public void setNoOfPackages(Integer noOfPackages) {
		this.noOfPackages = noOfPackages;
	}

	public Integer getNoOfUnReleasedPackageVersions() {
		return noOfUnReleasedPackageVersions;
	}

	public void setNoOfUnReleasedPackageVersions(
			Integer noOfUnReleasedPackageVersions) {
		this.noOfUnReleasedPackageVersions = noOfUnReleasedPackageVersions;
	}

	public Integer getNoOfReleasedPackageVersions() {
		return noOfReleasedPackageVersions;
	}

	public void setNoOfReleasedPackageVersions(
			Integer noOfReleasedPackageVersions) {
		this.noOfReleasedPackageVersions = noOfReleasedPackageVersions;
	}

}
