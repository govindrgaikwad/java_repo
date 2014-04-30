package com.tmg.ebscore.dto.template;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tmg.ebscore.dto.CoreCommonAttributes;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PackageTemplateData", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "PackageTemplateData", namespace = "http://www.tmg.com/coreservices/data")
public class PackageTemplateData extends CoreCommonAttributes {

	@XmlElement(name = "PackageTemplate1Up")
	private Integer packageTemplate1Up;

	@XmlElement(name = "BenefitPackageTypeAsString1up")
	private Integer benefitPackageTypeAsString1up;

	@XmlElement(name = "TemplateID")
	private String templateID;

	@XmlElement(name = "TemplateName")
	private String templateName;

	@XmlElement(name = "Description")
	private String description;

	@XmlElement(name = "TemplateWorkflowState1up")
	private Integer templateWorkflowState1up;

	public Integer getPackageTemplate1Up() {
		return packageTemplate1Up;
	}

	public void setPackageTemplate1Up(Integer packageTemplate1Up) {
		this.packageTemplate1Up = packageTemplate1Up;
	}

	public Integer getBenefitPackageTypeAsString1up() {
		return benefitPackageTypeAsString1up;
	}

	public void setBenefitPackageTypeAsString1up(
			Integer benefitPackageTypeAsString1up) {
		this.benefitPackageTypeAsString1up = benefitPackageTypeAsString1up;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getTemplateWorkflowState1up() {
		return templateWorkflowState1up;
	}

	public void setTemplateWorkflowState1up(Integer templateWorkflowState1up) {
		this.templateWorkflowState1up = templateWorkflowState1up;
	}

}
