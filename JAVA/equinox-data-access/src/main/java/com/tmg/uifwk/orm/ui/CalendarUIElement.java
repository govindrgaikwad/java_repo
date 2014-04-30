package com.tmg.uifwk.orm.ui;

import java.sql.Timestamp;

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
 * The persistent class for the CalendarUIElement database table.
 * 
 */
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(schema = "UI", name = "CalendarUIElement")
@PrimaryKeyJoinColumn(name = "UIElementID")
public class CalendarUIElement extends UIElement {
	private static final long serialVersionUID = 1L;

	@Column(name = "MinDate")
	private Timestamp minDate;

	@Column(name = "MaxDate")
	private Timestamp maxDate;

	@Column(name = "DefaultDate")
	private Timestamp defaultDate;

	// bi-directional many-to-one association to UIElementType
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UIElementTypeID")
	private UIElementType uielementType;

	public CalendarUIElement() {
	}

	public Timestamp getDefaultDate() {
		return this.defaultDate;
	}

	public void setDefaultDate(Timestamp defaultDate) {
		this.defaultDate = defaultDate;
	}

	public Timestamp getMaxDate() {
		return this.maxDate;
	}

	public void setMaxDate(Timestamp maxDate) {
		this.maxDate = maxDate;
	}

	public Timestamp getMinDate() {
		return this.minDate;
	}

	public void setMinDate(Timestamp minDate) {
		this.minDate = minDate;
	}

	public UIElementType getUielementType() {
		return this.uielementType;
	}

	public void setUielementType(UIElementType uielementType) {
		this.uielementType = uielementType;
	}

}