package com.tmg.ebscore.orm.template;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tmg.ebscore.orm.CoreCommonAttributes;

@Entity
@Table(schema = "Setup", name = "TemplateWorkflowState")
public class TemplateWorkflowState extends CoreCommonAttributes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TemplateWorkflowState1up")
	private Integer templateWorkflowState1up;

	@Column(name = "Name")
	private String name;

	@Column(name = "Description")
	private String description;

	@Column(name = "IsReleasedFlag")
	private Boolean releasedFlag;

	@Column(name = "DisplayOrder")
	private Integer displayOrder;

	@OneToMany(mappedBy = "templateWorkflowState", fetch = FetchType.LAZY)
	private Set<PackageTemplate> templateWorkflow = new HashSet<PackageTemplate>();

	public Set<PackageTemplate> getTemplateWorkflow() {
		return templateWorkflow;
	}

	public void setTemplateWorkflow(Set<PackageTemplate> templateWorkflow) {
		this.templateWorkflow = templateWorkflow;
	}

	public Integer getTemplateWorkflowState1up() {
		return templateWorkflowState1up;
	}

	public void setTemplateWorkflowState1up(Integer templateWorkflowState1up) {
		this.templateWorkflowState1up = templateWorkflowState1up;
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

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

}
