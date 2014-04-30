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
 * The persistent class for the Expression database table.
 * 
 */
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(schema = "UI", name = "Expression")
public class Expression extends BaseEntityAttributes {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ExpressionID")
	private int expressionID;

	@Column(name = "LeftOperand")
	private String leftOperand;

	@Column(name = "RightOperand")
	private String rightOperand;

	// bi-directional many-to-one association to LogicalOperatorType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "LogicalOperatorTypeID")
	private LogicalOperatorType logicalOperatorType;

	// bi-directional many-to-one association to OperatorType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "OperatorTypeID")
	private OperatorType operatorType;

	// bi-directional many-to-one association to Rule
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "RuleID")
	private Rule rule;

	public Expression() {
	}

	public int getExpressionID() {
		return this.expressionID;
	}

	public void setExpressionID(int expressionID) {
		this.expressionID = expressionID;
	}

	public String getLeftOperand() {
		return this.leftOperand;
	}

	public void setLeftOperand(String leftOperand) {
		this.leftOperand = leftOperand;
	}

	public String getRightOperand() {
		return this.rightOperand;
	}

	public void setRightOperand(String rightOperand) {
		this.rightOperand = rightOperand;
	}

	public LogicalOperatorType getLogicalOperatorType() {
		return this.logicalOperatorType;
	}

	public void setLogicalOperatorType(LogicalOperatorType logicalOperatorType) {
		this.logicalOperatorType = logicalOperatorType;
	}

	public OperatorType getOperatorType() {
		return this.operatorType;
	}

	public void setOperatorType(OperatorType operatorType) {
		this.operatorType = operatorType;
	}

	public Rule getRule() {
		return this.rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}

}