package com.tmg.uifwk.orm.ui;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * The persistent class for the DataSourceMapping database table.
 * 
 */
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(schema = "UI", name = "DataSourceMapping")
public class DataSourceMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DataSourceMappingID")
	private int dataSourceMappingID;

	// bi-directional many-to-one association to DataSource
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "DataSourceID")
	private DataSource dataSource;

	// bi-directional many-to-one association to UIElement
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "UIElementID")
	private UIElement dataSourceForUIElement;

	// bi-directional many-to-one association to UIElement
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "MappedUIElementID")
	private UIElement UIElementsInDataSource;

	public DataSourceMapping() {
	}

	public int getDataSourceMappingID() {
		return this.dataSourceMappingID;
	}

	public void setDataSourceMappingID(int dataSourceMappingID) {
		this.dataSourceMappingID = dataSourceMappingID;
	}

	public DataSource getDataSource() {
		return this.dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public UIElement getDataSourceForUIElement() {
		return dataSourceForUIElement;
	}

	public void setDataSourceForUIElement(UIElement dataSourceForUIElement) {
		this.dataSourceForUIElement = dataSourceForUIElement;
	}

	public UIElement getUIElementsInDataSource() {
		return UIElementsInDataSource;
	}

	public void setUIElementsInDataSource(UIElement uIElementsInDataSource) {
		UIElementsInDataSource = uIElementsInDataSource;
	}

}