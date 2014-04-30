package com.tmg.ebscore.dto.template;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tmg.ebscore.dto.CoreCommonAttributes;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TemplateWorkFlowStateData", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "TemplateWorkFlowStateData", namespace = "http://www.tmg.com/coreservices/data")
public class TemplateWorkFlowStateData extends CoreCommonAttributes {

	@XmlElement(name = "TemplateWorkflowState1up")
	private Integer templateWorkflowState1up;

	@XmlElement(name = "WorkflowName")
	private String workflowName;

	@XmlElement(name = "WorkflowDescription")
	private String workflowDescription;

	@XmlElement(name = "IsReleased")
	private Boolean released;

	@XmlElement(name = "DisplayOrder")
	private Integer displayOrder;

	public Integer getTemplateWorkflowState1up() {
		return templateWorkflowState1up;
	}

	public void setTemplateWorkflowState1up(Integer templateWorkflowState1up) {
		this.templateWorkflowState1up = templateWorkflowState1up;
	}

	public String getWorkflowName() {
		return workflowName;
	}

	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}

	public String getWorkflowDescription() {
		return workflowDescription;
	}

	public void setWorkflowDescription(String workflowDescription) {
		this.workflowDescription = workflowDescription;
	}

	public Boolean getReleased() {
		return released;
	}

	public void setReleased(Boolean released) {
		this.released = released;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

}
