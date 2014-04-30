package com.tmg.ebscore.dto.packageversion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tmg.ebscore.dto.CoreCommonAttributes;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PackageVersionSummaryData", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "PackageVersionSummaryData", namespace = "http://www.tmg.com/coreservices/data")
public class PackageVersionSummaryData extends CoreCommonAttributes {

	@XmlElement(name = "PkgVersion1up")
	private Integer pkgVersion1up;

	@XmlElement(name = "VersionID")
	private String versionID;

	@XmlElement(name = "VersionName")
	private String versionName;

	@XmlElement(name = "VersionDescription")
	private String versionDescription;

	@XmlElement(name = "Workflowstate1up")
	private Integer workflowstate1up;

	@XmlElement(name = "Released")
	private Boolean released;

	@XmlElement(name = "TemplateName")
	private String templateName;

	@XmlElement(name = "TemplateId")
	private String templateId;

	@XmlElement(name = "PackageName")
	private String packageName;

	@XmlElement(name = "PackageId")
	private String packageId;

	public Integer getPkgVersion1up() {
		return pkgVersion1up;
	}

	public void setPkgVersion1up(Integer pkgVersion1up) {
		this.pkgVersion1up = pkgVersion1up;
	}

	public String getVersionID() {
		return versionID;
	}

	public void setVersionID(String versionID) {
		this.versionID = versionID;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getVersionDescription() {
		return versionDescription;
	}

	public void setVersionDescription(String versionDescription) {
		this.versionDescription = versionDescription;
	}

	public Integer getWorkflowstate1up() {
		return workflowstate1up;
	}

	public void setWorkflowstate1up(Integer workflowstate1up) {
		this.workflowstate1up = workflowstate1up;
	}

	public Boolean getReleased() {
		return released;
	}

	public void setReleased(Boolean released) {
		this.released = released;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

}
