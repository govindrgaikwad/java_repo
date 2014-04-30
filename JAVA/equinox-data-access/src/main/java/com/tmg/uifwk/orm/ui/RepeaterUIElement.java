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
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

/**
 * The persistent class for the RepeaterUIElement database table.
 * 
 */
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@DynamicInsert
@DynamicUpdate
@SelectBeforeUpdate
@Table(schema = "UI", name = "RepeaterUIElement")
@PrimaryKeyJoinColumn(name = "UIElementID")
public class RepeaterUIElement extends UIElement {
	private static final long serialVersionUID = 1L;

	@Column(name = "ChildCount")
	private int childCount;

	// bi-directional many-to-one association to UIElementType
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UIElementTypeID")
	private UIElementType uielementType;

	// bi-directional many-to-one association to DataSource
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DataSourceID")
	private DataSource dataSource;

	// bi-directional many-to-one association to LayoutType
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LayoutTypeID")
	private LayoutType layoutType;

	public RepeaterUIElement() {
	}

	public int getChildCount() {
		return this.childCount;
	}

	public void setChildCount(int childCount) {
		this.childCount = childCount;
	}

	public UIElementType getUielementType() {
		return uielementType;
	}

	public void setUielementType(UIElementType uielementType) {
		this.uielementType = uielementType;
	}

	public DataSource getDataSource() {
		return this.dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public LayoutType getLayoutType() {
		return this.layoutType;
	}

	public void setLayoutType(LayoutType layoutType) {
		this.layoutType = layoutType;
	}

}