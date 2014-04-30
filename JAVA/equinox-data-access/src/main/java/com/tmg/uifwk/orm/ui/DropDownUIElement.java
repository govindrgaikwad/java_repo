package com.tmg.uifwk.orm.ui;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * The persistent class for the DropDownUIElement database table.
 * 
 */
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(schema = "UI", name = "DropDownUIElement")
@PrimaryKeyJoinColumn(name = "UIElementID")
public class DropDownUIElement extends UIElement {
	private static final long serialVersionUID = 1L;

	@Column(name = "SelectedValue")
	private String selectedValue;

	@Column(name = "IsMultiSelect")
	private boolean multiSelect;

	// bi-directional many-to-one association to DropDownElementItem
	@OneToMany(mappedBy = "dropDownUIElement", fetch = FetchType.LAZY)
	private List<DropDownElementItem> dropDownElementItems;

	// bi-directional many-to-one association to UIElementType
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UIElementTypeID")
	private UIElementType uielementType;

	public DropDownUIElement() {
	}

	public boolean isMultiSelect() {
		return multiSelect;
	}

	public void setMultiSelect(boolean multiSelect) {
		this.multiSelect = multiSelect;
	}

	public String getSelectedValue() {
		return this.selectedValue;
	}

	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}

	public List<DropDownElementItem> getDropDownElementItems() {
		return this.dropDownElementItems;
	}

	public void setDropDownElementItems(
			List<DropDownElementItem> dropDownElementItems) {
		this.dropDownElementItems = dropDownElementItems;
	}

	public DropDownElementItem addDropDownElementItem(
			DropDownElementItem dropDownElementItem) {
		getDropDownElementItems().add(dropDownElementItem);
		dropDownElementItem.setDropDownUIElement(this);

		return dropDownElementItem;
	}

	public DropDownElementItem removeDropDownElementItem(
			DropDownElementItem dropDownElementItem) {
		getDropDownElementItems().remove(dropDownElementItem);
		dropDownElementItem.setDropDownUIElement(null);

		return dropDownElementItem;
	}

	public UIElementType getUielementType() {
		return this.uielementType;
	}

	public void setUielementType(UIElementType uielementType) {
		this.uielementType = uielementType;
	}

}