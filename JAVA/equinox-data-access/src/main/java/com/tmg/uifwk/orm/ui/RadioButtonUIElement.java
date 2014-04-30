package com.tmg.uifwk.orm.ui;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * The persistent class for the RadioButtonUIElement database table.
 * 
 */
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(schema = "UI", name = "RadioButtonUIElement")
@PrimaryKeyJoinColumn(name = "UIElementID")
public class RadioButtonUIElement extends UIElement {
	private static final long serialVersionUID = 1L;

	@Column(name = "DefaultValue")
	private Boolean defaultValue;

	@Column(name = "IsYesNo")
	private Boolean yesNo;

	@Column(name = "OptionLabel")
	private String optionLabel;

	@Column(name = "OptionLabelNo")
	private String optionLabelNo;

	// bi-directional many-to-one association to UIElementType
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UIElementTypeID")
	private UIElementType uielementType;

	public RadioButtonUIElement() {
	}

	public Boolean getDefaultValue() {
		return this.defaultValue;
	}

	public void setDefaultValue(Boolean defaultValue) {
		this.defaultValue = defaultValue;
	}

	public Boolean isYesNo() {
		return yesNo;
	}

	public void setYesNo(Boolean yesNo) {
		this.yesNo = yesNo;
	}

	public String getOptionLabel() {
		return this.optionLabel;
	}

	public void setOptionLabel(String optionLabel) {
		this.optionLabel = optionLabel;
	}

	public String getOptionLabelNo() {
		return this.optionLabelNo;
	}

	public void setOptionLabelNo(String optionLabelNo) {
		this.optionLabelNo = optionLabelNo;
	}

	public UIElementType getUielementType() {
		return this.uielementType;
	}

	public void setUielementType(UIElementType uielementType) {
		this.uielementType = uielementType;
	}

}