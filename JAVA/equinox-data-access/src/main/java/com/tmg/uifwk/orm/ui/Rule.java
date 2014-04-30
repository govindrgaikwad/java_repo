package com.tmg.uifwk.orm.ui;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.tmg.uifwk.orm.BaseEntityAttributes;

/**
 * The persistent class for the Rule database table.
 * 
 */
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(schema = "UI", name = "Rule")
public class Rule extends BaseEntityAttributes {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RuleID")
	private int ruleID;

	@Column(name = "ResultFailure")
	private String resultFailure;

	@Column(name = "ResultSuccess")
	private String resultSuccess;

	@Column(name = "RuleDescription")
	private String ruleDescription;

	@Column(name = "RuleName")
	private String ruleName;

	// bi-directional many-to-one association to Expression
	@OneToMany(mappedBy = "rule",fetch=FetchType.LAZY)
	private List<Expression> expressions;

	// bi-directional many-to-one association to PropertyRuleMap
	@OneToMany(mappedBy = "rule",fetch=FetchType.LAZY)
	private List<PropertyRuleMap> propertyRuleMaps;

	// bi-directional many-to-one association to RuleTargetType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "RuleTargetTypeID")
	private RuleTargetType ruleTargetType;

	public Rule() {
	}

	public int getRuleID() {
		return this.ruleID;
	}

	public void setRuleID(int ruleID) {
		this.ruleID = ruleID;
	}

	public String getResultFailure() {
		return this.resultFailure;
	}

	public void setResultFailure(String resultFailure) {
		this.resultFailure = resultFailure;
	}

	public String getResultSuccess() {
		return this.resultSuccess;
	}

	public void setResultSuccess(String resultSuccess) {
		this.resultSuccess = resultSuccess;
	}

	public String getRuleDescription() {
		return this.ruleDescription;
	}

	public void setRuleDescription(String ruleDescription) {
		this.ruleDescription = ruleDescription;
	}

	public String getRuleName() {
		return this.ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public List<Expression> getExpressions() {
		return this.expressions;
	}

	public void setExpressions(List<Expression> expressions) {
		this.expressions = expressions;
	}

	public Expression addExpression(Expression expression) {
		getExpressions().add(expression);
		expression.setRule(this);

		return expression;
	}

	public Expression removeExpression(Expression expression) {
		getExpressions().remove(expression);
		expression.setRule(null);

		return expression;
	}

	public List<PropertyRuleMap> getPropertyRuleMaps() {
		return this.propertyRuleMaps;
	}

	public void setPropertyRuleMaps(List<PropertyRuleMap> propertyRuleMaps) {
		this.propertyRuleMaps = propertyRuleMaps;
	}

	public PropertyRuleMap addPropertyRuleMap(PropertyRuleMap propertyRuleMap) {
		getPropertyRuleMaps().add(propertyRuleMap);
		propertyRuleMap.setRule(this);

		return propertyRuleMap;
	}

	public PropertyRuleMap removePropertyRuleMap(PropertyRuleMap propertyRuleMap) {
		getPropertyRuleMaps().remove(propertyRuleMap);
		propertyRuleMap.setRule(null);

		return propertyRuleMap;
	}

	public RuleTargetType getRuleTargetType() {
		return this.ruleTargetType;
	}

	public void setRuleTargetType(RuleTargetType ruleTargetType) {
		this.ruleTargetType = ruleTargetType;
	}

}