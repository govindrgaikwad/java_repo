package com.tmg.uifwk.orm.ui;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.tmg.uifwk.orm.BaseEntityAttributes;

/**
 * The persistent class for the RuleTargetType database table.
 * 
 */
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(schema = "UI", name = "RuleTargetType")
public class RuleTargetType extends BaseEntityAttributes {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RuleTargetTypeID")
	private int ruleTargetTypeID;

	@Column(name = "Description")
	private String description;

	@Column(name = "DisplayText")
	private String displayText;

	@Column(name = "IsActive")
	private boolean active;

	@Column(name = "RuleTargetTypeCode")
	private String ruleTargetTypeCode;

	// bi-directional many-to-one association to Rule
	@OneToMany(mappedBy = "ruleTargetType", fetch = FetchType.LAZY)
	private List<Rule> rules;

	public RuleTargetType() {
	}

	public int getRuleTargetTypeID() {
		return this.ruleTargetTypeID;
	}

	public void setRuleTargetTypeID(int ruleTargetTypeID) {
		this.ruleTargetTypeID = ruleTargetTypeID;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDisplayText() {
		return this.displayText;
	}

	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getRuleTargetTypeCode() {
		return this.ruleTargetTypeCode;
	}

	public void setRuleTargetTypeCode(String ruleTargetTypeCode) {
		this.ruleTargetTypeCode = ruleTargetTypeCode;
	}

	public List<Rule> getRules() {
		return this.rules;
	}

	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}

	public Rule addRule(Rule rule) {
		getRules().add(rule);
		rule.setRuleTargetType(this);

		return rule;
	}

	public Rule removeRule(Rule rule) {
		getRules().remove(rule);
		rule.setRuleTargetType(null);

		return rule;
	}

}