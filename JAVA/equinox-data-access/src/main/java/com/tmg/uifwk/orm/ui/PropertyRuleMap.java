package com.tmg.uifwk.orm.ui;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.tmg.uifwk.orm.BaseEntityAttributes;

/**
 * The persistent class for the PropertyRuleMap database table.
 * 
 */
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(schema = "UI", name = "PropertyRuleMap")
public class PropertyRuleMap extends BaseEntityAttributes {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PropertyRuleMapID")
	private int propertyRuleMapID;

	@Column(name = "IsCustomRule")
	private boolean customRule;

	// bi-directional many-to-one association to Rule
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "RuleID")
	private Rule rule;

	// bi-directional many-to-one association to TargetProperty
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "TargetPropertyID")
	private TargetProperty targetProperty;

	// bi-directional many-to-one association to UIElement
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UIElementID")
	private UIElement uielement;

	public PropertyRuleMap() {
	}

	public int getPropertyRuleMapID() {
		return this.propertyRuleMapID;
	}

	public void setPropertyRuleMapID(int propertyRuleMapID) {
		this.propertyRuleMapID = propertyRuleMapID;
	}

	public boolean isCustomRule() {
		return customRule;
	}

	public void setCustomRule(boolean customRule) {
		this.customRule = customRule;
	}

	public Rule getRule() {
		return this.rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}

	public TargetProperty getTargetProperty() {
		return this.targetProperty;
	}

	public void setTargetProperty(TargetProperty targetProperty) {
		this.targetProperty = targetProperty;
	}

	public UIElement getUielement() {
		return this.uielement;
	}

	public void setUielement(UIElement uielement) {
		this.uielement = uielement;
	}

}