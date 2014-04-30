package com.tmg.ebscore.orm.template;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.tmg.ebscore.orm.CoreCommonAttributes;

@Entity
@Table(schema = "Tmplt", name = "TemplateAllowsIntVal")
public class TemplateAllowsIntVal extends CoreCommonAttributes {

	@EmbeddedId
	private TemplateAllowsIntValId templateAllowsIntValId;
	
	@Column(name = "IsDefaultValFlag")
	private Boolean defaultValFlag;

	public TemplateAllowsIntValId getTemplateAllowsIntValId() {
		return templateAllowsIntValId;
	}

	public void setTemplateAllowsIntValId(
			TemplateAllowsIntValId templateAllowsIntValId) {
		this.templateAllowsIntValId = templateAllowsIntValId;
	}

	public Boolean getDefaultValFlag() {
		return defaultValFlag;
	}

	public void setDefaultValFlag(Boolean defaultValFlag) {
		this.defaultValFlag = defaultValFlag;
	}

}
