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
 * The persistent class for the UIElementType database table.
 * 
 */
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Entity
@Table(schema = "UI", name = "UIElementType")
public class UIElementType extends BaseEntityAttributes {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UIElementTypeID")
	private int UIElementTypeID;

	@Column(name = "Description")
	private String description;

	@Column(name = "DisplayText")
	private String displayText;

	@Column(name = "IsActive")
	private boolean active;

	private String UIElementTypeCode;

	// bi-directional many-to-one association to CalendarUIElement
	@OneToMany(mappedBy = "uielementType", fetch = FetchType.LAZY)
	private List<CalendarUIElement> calendarUielements;

	// bi-directional many-to-one association to CheckBoxUIElement
	@OneToMany(mappedBy = "uielementType", fetch = FetchType.LAZY)
	private List<CheckBoxUIElement> checkBoxUielements;

	// bi-directional many-to-one association to DropDownUIElement
	@OneToMany(mappedBy = "uielementType", fetch = FetchType.LAZY)
	private List<DropDownUIElement> dropDownUielements;

	// bi-directional many-to-one association to RadioButtonUIElement
	@OneToMany(mappedBy = "uielementType", fetch = FetchType.LAZY)
	private List<RadioButtonUIElement> radioButtonUielements;

	// bi-directional many-to-one association to SectionUIElement
	@OneToMany(mappedBy = "uielementType", fetch = FetchType.LAZY)
	private List<SectionUIElement> sectionUielements;

	// bi-directional many-to-one association to TabUIElement
	@OneToMany(mappedBy = "uielementType", fetch = FetchType.LAZY)
	private List<TabUIElement> tabUielements;

	// bi-directional many-to-one association to TextBoxUIElement
	@OneToMany(mappedBy = "uielementType", fetch = FetchType.LAZY)
	private List<TextBoxUIElement> textBoxUielements;

	public UIElementType() {
	}

	public int getUIElementTypeID() {
		return this.UIElementTypeID;
	}

	public void setUIElementTypeID(int UIElementTypeID) {
		this.UIElementTypeID = UIElementTypeID;
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

	public String getUIElementTypeCode() {
		return this.UIElementTypeCode;
	}

	public void setUIElementTypeCode(String UIElementTypeCode) {
		this.UIElementTypeCode = UIElementTypeCode;
	}

	public List<CalendarUIElement> getCalendarUielements() {
		return this.calendarUielements;
	}

	public void setCalendarUielements(List<CalendarUIElement> calendarUielements) {
		this.calendarUielements = calendarUielements;
	}

	public CalendarUIElement addCalendarUielement(
			CalendarUIElement calendarUielement) {
		getCalendarUielements().add(calendarUielement);
		calendarUielement.setUielementType(this);

		return calendarUielement;
	}

	public CalendarUIElement removeCalendarUielement(
			CalendarUIElement calendarUielement) {
		getCalendarUielements().remove(calendarUielement);
		calendarUielement.setUielementType(null);

		return calendarUielement;
	}

	public List<CheckBoxUIElement> getCheckBoxUielements() {
		return this.checkBoxUielements;
	}

	public void setCheckBoxUielements(List<CheckBoxUIElement> checkBoxUielements) {
		this.checkBoxUielements = checkBoxUielements;
	}

	public CheckBoxUIElement addCheckBoxUielement(
			CheckBoxUIElement checkBoxUielement) {
		getCheckBoxUielements().add(checkBoxUielement);
		checkBoxUielement.setUielementType(this);

		return checkBoxUielement;
	}

	public CheckBoxUIElement removeCheckBoxUielement(
			CheckBoxUIElement checkBoxUielement) {
		getCheckBoxUielements().remove(checkBoxUielement);
		checkBoxUielement.setUielementType(null);

		return checkBoxUielement;
	}

	public List<DropDownUIElement> getDropDownUielements() {
		return this.dropDownUielements;
	}

	public void setDropDownUielements(List<DropDownUIElement> dropDownUielements) {
		this.dropDownUielements = dropDownUielements;
	}

	public DropDownUIElement addDropDownUielement(
			DropDownUIElement dropDownUielement) {
		getDropDownUielements().add(dropDownUielement);
		dropDownUielement.setUielementType(this);

		return dropDownUielement;
	}

	public DropDownUIElement removeDropDownUielement(
			DropDownUIElement dropDownUielement) {
		getDropDownUielements().remove(dropDownUielement);
		dropDownUielement.setUielementType(null);

		return dropDownUielement;
	}

	public List<RadioButtonUIElement> getRadioButtonUielements() {
		return this.radioButtonUielements;
	}

	public void setRadioButtonUielements(
			List<RadioButtonUIElement> radioButtonUielements) {
		this.radioButtonUielements = radioButtonUielements;
	}

	public RadioButtonUIElement addRadioButtonUielement(
			RadioButtonUIElement radioButtonUielement) {
		getRadioButtonUielements().add(radioButtonUielement);
		radioButtonUielement.setUielementType(this);

		return radioButtonUielement;
	}

	public RadioButtonUIElement removeRadioButtonUielement(
			RadioButtonUIElement radioButtonUielement) {
		getRadioButtonUielements().remove(radioButtonUielement);
		radioButtonUielement.setUielementType(null);

		return radioButtonUielement;
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
		sectionUielement.setUielementType(this);

		return sectionUielement;
	}

	public SectionUIElement removeSectionUielement(
			SectionUIElement sectionUielement) {
		getSectionUielements().remove(sectionUielement);
		sectionUielement.setUielementType(null);

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
		tabUielement.setUielementType(this);

		return tabUielement;
	}

	public TabUIElement removeTabUielement(TabUIElement tabUielement) {
		getTabUielements().remove(tabUielement);
		tabUielement.setUielementType(null);

		return tabUielement;
	}

	public List<TextBoxUIElement> getTextBoxUielements() {
		return this.textBoxUielements;
	}

	public void setTextBoxUielements(List<TextBoxUIElement> textBoxUielements) {
		this.textBoxUielements = textBoxUielements;
	}

	public TextBoxUIElement addTextBoxUielement(
			TextBoxUIElement textBoxUielement) {
		getTextBoxUielements().add(textBoxUielement);
		textBoxUielement.setUielementType(this);

		return textBoxUielement;
	}

	public TextBoxUIElement removeTextBoxUielement(
			TextBoxUIElement textBoxUielement) {
		getTextBoxUielements().remove(textBoxUielement);
		textBoxUielement.setUielementType(null);

		return textBoxUielement;
	}

}