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
 * The persistent class for the DataSource database table.
 * 
 */
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(schema = "UI", name = "DataSource")
public class DataSource extends BaseEntityAttributes {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DataSourceID")
	private int dataSourceID;

	@Column(name = "DataSourceName")
	private String dataSourceName;

	@Column(name = "IsActive")
	private boolean active;

	@Column(name = "Type")
	private String type;

	// bi-directional many-to-one association to DataSourceMapping
	@OneToMany(mappedBy = "dataSource",fetch=FetchType.LAZY)
	private List<DataSourceMapping> dataSourceMappings;

	// bi-directional many-to-one association to RepeaterUIElement
	@OneToMany(mappedBy = "dataSource",fetch=FetchType.LAZY)
	private List<RepeaterUIElement> repeaterUIElements;

	// bi-directional many-to-one association to SectionUIElement
	@OneToMany(mappedBy = "dataSource",fetch=FetchType.LAZY)
	private List<SectionUIElement> sectionUIElements;

	public DataSource() {
	}

	public int getDataSourceID() {
		return this.dataSourceID;
	}

	public void setDataSourceID(int dataSourceID) {
		this.dataSourceID = dataSourceID;
	}

	public String getDataSourceName() {
		return dataSourceName;
	}

	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<DataSourceMapping> getDataSourceMappings() {
		return this.dataSourceMappings;
	}

	public void setDataSourceMappings(List<DataSourceMapping> dataSourceMappings) {
		this.dataSourceMappings = dataSourceMappings;
	}

	public DataSourceMapping addDataSourceMapping(
			DataSourceMapping dataSourceMapping) {
		getDataSourceMappings().add(dataSourceMapping);
		dataSourceMapping.setDataSource(this);

		return dataSourceMapping;
	}

	public DataSourceMapping removeDataSourceMapping(
			DataSourceMapping dataSourceMapping) {
		getDataSourceMappings().remove(dataSourceMapping);
		dataSourceMapping.setDataSource(null);

		return dataSourceMapping;
	}

	public List<RepeaterUIElement> getRepeaterUielements() {
		return this.repeaterUIElements;
	}

	public void setRepeaterUielements(List<RepeaterUIElement> repeaterUielements) {
		this.repeaterUIElements = repeaterUielements;
	}

	public RepeaterUIElement addRepeaterUielement(
			RepeaterUIElement repeaterUielement) {
		getRepeaterUielements().add(repeaterUielement);
		repeaterUielement.setDataSource(this);

		return repeaterUielement;
	}

	public RepeaterUIElement removeRepeaterUielement(
			RepeaterUIElement repeaterUielement) {
		getRepeaterUielements().remove(repeaterUielement);
		repeaterUielement.setDataSource(null);

		return repeaterUielement;
	}

	public List<SectionUIElement> getSectionUielements() {
		return this.sectionUIElements;
	}

	public void setSectionUielements(List<SectionUIElement> sectionUielements) {
		this.sectionUIElements = sectionUielements;
	}

	public SectionUIElement addSectionUielement(
			SectionUIElement sectionUielement) {
		getSectionUielements().add(sectionUielement);
		sectionUielement.setDataSource(this);

		return sectionUielement;
	}

	public SectionUIElement removeSectionUielement(
			SectionUIElement sectionUielement) {
		getSectionUielements().remove(sectionUielement);
		sectionUielement.setDataSource(null);

		return sectionUielement;
	}

}