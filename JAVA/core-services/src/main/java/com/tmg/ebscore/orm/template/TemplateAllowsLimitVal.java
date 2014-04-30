package com.tmg.ebscore.orm.template;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.tmg.ebscore.orm.CoreCommonAttributes;

@Entity
@Table(schema = "Tmplt", name = "TemplateAllowsLimitVal")
public class TemplateAllowsLimitVal extends CoreCommonAttributes {

	@EmbeddedId
	private TemplateAllowsLimitValId templateAllowsLimitValId;

	public TemplateAllowsLimitValId getTemplateAllowsLimitValId() {
		return templateAllowsLimitValId;
	}

	public void setTemplateAllowsLimitValId(
			TemplateAllowsLimitValId templateAllowsLimitValId) {
		this.templateAllowsLimitValId = templateAllowsLimitValId;
	}

}
