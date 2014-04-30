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
 * The persistent class for the LayoutType database table.
 * 
 */
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Entity
@Table(schema = "UI", name = "LayoutType")
public class LayoutType extends BaseEntityAttributes {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LayoutTypeID")
	private int layoutTypeID;

	@Column(name = "ClassName")
	private String className;

	@Column(name = "ColumnCount")
	private int columnCount;

	@Column(name = "DisplayText")
	private String displayText;

	@Column(name = "IsActive")
	private boolean active;

	@Column(name = "LayoutTypeCode")
	private String layoutTypeCode;

	@Column(name = "Name")
	private String name;

	// bi-directional many-to-one association to RepeaterUIElement
	@OneToMany(mappedBy = "layoutType", fetch = FetchType.LAZY)
	private List<RepeaterUIElement> repeaterUielements;

	// bi-directional many-to-one association to SectionUIElement
	@OneToMany(mappedBy = "layoutType", fetch = FetchType.LAZY)
	private List<SectionUIElement> sectionUielements;

	// bi-directional many-to-one association to TabUIElement
	@OneToMany(mappedBy = "layoutType", fetch = FetchType.LAZY)
	private List<TabUIElement> tabUielements;

	public LayoutType() {
	}

	public int getLayoutTypeID() {
		return this.layoutTypeID;
	}

	public void setLayoutTypeID(int layoutTypeID) {
		this.layoutTypeID = layoutTypeID;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getColumnCount() {
		return this.columnCount;
	}

	public void setColumnCount(int columnCount) {
		this.columnCount = columnCount;
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

	public String getLayoutTypeCode() {
		return this.layoutTypeCode;
	}

	public void setLayoutTypeCode(String layoutTypeCode) {
		this.layoutTypeCode = layoutTypeCode;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<RepeaterUIElement> getRepeaterUielements() {
		return this.repeaterUielements;
	}

	public void setRepeaterUielements(List<RepeaterUIElement> repeaterUielements) {
		this.repeaterUielements = repeaterUielements;
	}

	public RepeaterUIElement addRepeaterUielement(
			RepeaterUIElement repeaterUielement) {
		getRepeaterUielements().add(repeaterUielement);
		repeaterUielement.setLayoutType(this);

		return repeaterUielement;
	}

	public RepeaterUIElement removeRepeaterUielement(
			RepeaterUIElement repeaterUielement) {
		getRepeaterUielements().remove(repeaterUielement);
		repeaterUielement.setLayoutType(null);

		return repeaterUielement;
	}

	public List<SectionUIElement> getSectionUielements() {
		return this.sectionUielements;
	}

	public void setSectionUielements(List<SectionUIElement> sectionUielements) {
		this.sectionUielements = sectionUielements;
	}

	public SectionUIElement addSectionUielement(
			SectionUIElement sectionUielement) {
		getSectionUielements().add(sectionUielement);
		sectionUielement.setLayoutType(this);

		return sectionUielement;
	}

	public SectionUIElement removeSectionUielement(
			SectionUIElement sectionUielement) {
		getSectionUielements().remove(sectionUielement);
		sectionUielement.setLayoutType(null);

		return sectionUielement;
	}

	public List<TabUIElement> getTabUielements() {
		return this.tabUielements;
	}

	public void setTabUielements(List<TabUIElement> tabUielements) {
		this.tabUielements = tabUielements;
	}

	public TabUIElement addTabUielement(TabUIElement tabUielement) {
		getTabUielements().add(tabUielement);
		tabUielement.setLayoutType(this);

		return tabUielement;
	}

	public TabUIElement removeTabUielement(TabUIElement tabUielement) {
		getTabUielements().remove(tabUielement);
		tabUielement.setLayoutType(null);

		return tabUielement;
	}

}