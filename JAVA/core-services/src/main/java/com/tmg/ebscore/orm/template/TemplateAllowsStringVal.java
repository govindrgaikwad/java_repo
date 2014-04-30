package com.tmg.ebscore.orm.template;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.tmg.ebscore.orm.CoreCommonAttributes;

@Entity
@Table(schema = "Tmplt", name = "TemplateAllowsStringVal")
public class TemplateAllowsStringVal extends CoreCommonAttributes {

	@EmbeddedId
	private TemplateAllowsStringValId templateAllowsStringValId;

	@Column(name = "IsDefaultValFlag")
	private Boolean defaultValFlag;

	public TemplateAllowsStringValId getTemplateAllowsStringValId() {
		return templateAllowsStringValId;
	}

	public void setTemplateAllowsStringValId(
			TemplateAllowsStringValId templateAllowsStringValId) {
		this.templateAllowsStringValId = templateAllowsStringValId;
	}

	public Boolean getDefaultValFlag() {
		return defaultValFlag;
	}

	public void setDefaultValFlag(Boolean defaultValFlag) {
		this.defaultValFlag = defaultValFlag;
	}

}
