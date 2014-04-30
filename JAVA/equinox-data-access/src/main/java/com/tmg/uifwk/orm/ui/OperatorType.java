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
 * The persistent class for the OperatorType database table.
 * 
 */
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(schema = "UI", name = "OperatorType")
public class OperatorType extends BaseEntityAttributes {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OperatorTypeID")
	private int operatorTypeID;

	@Column(name = "Description")
	private String description;

	@Column(name = "DisplayText")
	private String displayText;

	@Column(name = "IsActive")
	private boolean active;

	@Column(name = "OperatorTypeCode")
	private String operatorTypeCode;

	// bi-directional many-to-one association to Expression
	@OneToMany(mappedBy = "operatorType", fetch = FetchType.LAZY)
	private List<Expression> expressions;

	public OperatorType() {
	}

	public int getOperatorTypeID() {
		return this.operatorTypeID;
	}

	public void setOperatorTypeID(int operatorTypeID) {
		this.operatorTypeID = operatorTypeID;
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

	public String getOperatorTypeCode() {
		return this.operatorTypeCode;
	}

	public void setOperatorTypeCode(String operatorTypeCode) {
		this.operatorTypeCode = operatorTypeCode;
	}

	public List<Expression> getExpressions() {
		return this.expressions;
	}

	public void setExpressions(List<Expression> expressions) {
		this.expressions = expressions;
	}

	public Expression addExpression(Expression expression) {
		getExpressions().add(expression);
		expression.setOperatorType(this);

		return expression;
	}

	public Expression removeExpression(Expression expression) {
		getExpressions().remove(expression);
		expression.setOperatorType(null);

		return expression;
	}

}