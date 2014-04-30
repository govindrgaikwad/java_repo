package com.tmg.ebscore.orm.template;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.tmg.ebscore.orm.CoreCommonAttributes;

@Entity
@Table(schema = "Tmplt", name = "TemplateAllowsServiceDefinition")
public class TemplateAllowsServiceDefinition extends CoreCommonAttributes {

	@EmbeddedId
	private TemplateAllowsServiceDefinitionId templateAllowsServiceDefinitionId;

	public TemplateAllowsServiceDefinitionId getTemplateAllowsServiceDefinitionId() {
		return templateAllowsServiceDefinitionId;
	}

	public void setTemplateAllowsServiceDefinitionId(
			TemplateAllowsServiceDefinitionId templateAllowsServiceDefinitionId) {
		this.templateAllowsServiceDefinitionId = templateAllowsServiceDefinitionId;
	}

}
