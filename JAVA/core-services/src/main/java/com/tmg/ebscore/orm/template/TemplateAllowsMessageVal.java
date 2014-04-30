package com.tmg.ebscore.orm.template;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.tmg.ebscore.orm.CoreCommonAttributes;

@Entity
@Table(schema = "Tmplt", name = "TemplateAllowsMessageVal")
public class TemplateAllowsMessageVal extends CoreCommonAttributes {

	@EmbeddedId
	private TemplateAllowsMessageValId templateAllowsMessageValId;

	public TemplateAllowsMessageValId getTemplateAllowsMessageValId() {
		return templateAllowsMessageValId;
	}

	public void setTemplateAllowsMessageValId(
			TemplateAllowsMessageValId templateAllowsMessageValId) {
		this.templateAllowsMessageValId = templateAllowsMessageValId;
	}

}
