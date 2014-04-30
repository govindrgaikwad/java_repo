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
 * The persistent class for the TextBoxUIElement database table.
 * 
 */
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(schema = "UI", name = "TextBoxUIElement")
@PrimaryKeyJoinColumn(name = "UIElementID")
public class TextBoxUIElement extends UIElement {
	private static final long serialVersionUID = 1L;

	@Column(name = "IsMultiline")
	private boolean multiline;

	@Column(name = "DefaultValue")
	private String defaultValue;

	@Column(name = "MaxLength")
	private int maxLength;

	@Column(name = "IsLabel")
	private Boolean label;

	@Column(name = "SpellCheck")
	private boolean spellCheck;

	// bi-directional many-to-one association to UIElementType
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UIElementTypeID")
	private UIElementType uielementType;

	public TextBoxUIElement() {
	}

	public String getDefaultValue() {
		return this.defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public Boolean isLabel() {
		return label;
	}

	public void setLabel(Boolean label) {
		this.label = label;
	}

	public boolean isMultiline() {
		return multiline;
	}

	public void setMultiline(boolean multiline) {
		this.multiline = multiline;
	}

	public int getMaxLength() {
		return this.maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public boolean getSpellCheck() {
		return this.spellCheck;
	}

	public void setSpellCheck(boolean spellCheck) {
		this.spellCheck = spellCheck;
	}

	public UIElementType getUielementType() {
		return this.uielementType;
	}

	public void setUielementType(UIElementType uielementType) {
		this.uielementType = uielementType;
	}

}