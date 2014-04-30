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
 * The persistent class for the DropDownElementItem database table.
 * 
 */
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(schema = "UI", name = "DropDownElementItem")
public class DropDownElementItem extends BaseEntityAttributes {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ItemID")
	private int itemID;

	@Column(name = "DisplayText")
	private String displayText;

	@Column(name = "IsActive")
	private boolean active;

	@Column(name = "Sequence")
	private int sequence;

	@Column(name = "Value")
	private String value;

	// bi-directional many-to-one association to DropDownUIElement
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "UIElementID")
	private DropDownUIElement dropDownUIElement;

	public DropDownElementItem() {
	}

	public int getItemID() {
		return this.itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
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

	public int getSequence() {
		return this.sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public DropDownUIElement getDropDownUIElement() {
		return dropDownUIElement;
	}

	public void setDropDownUIElement(DropDownUIElement dropDownUIElement) {
		this.dropDownUIElement = dropDownUIElement;
	}

}