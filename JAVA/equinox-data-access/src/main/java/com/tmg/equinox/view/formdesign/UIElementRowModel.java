package com.tmg.equinox.view.formdesign;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UIElementRowModel {

	private int UIElementID;
	private String elementType;
	private String dataType;
	private int sequence;
	private int level;
	private int maxLength;
	private String required;
	private String label;
	private String parent;
	private boolean isLeaf;
	private boolean isExt;
	private boolean loaded;
	private Date addedDate;
	private Date updatedDate;
	private String addedBy;
	private String updatedBy;

	@JsonProperty(value = "UIElementID")
	public int getUIElementID() {
		return UIElementID;
	}

	public void setUIElementID(int uIElementID) {
		this.UIElementID = uIElementID;
	}

	@JsonProperty(value = "ElementType")
	public String getElementType() {
		return elementType;
	}

	public void setElementType(String elementType) {
		this.elementType = elementType;
	}

	@JsonProperty(value = "DataType")
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	@JsonProperty(value = "Sequence")
	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	@JsonProperty(value = "level")
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@JsonProperty(value = "MaxLength")
	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	@JsonProperty(value = "Required")
	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	@JsonProperty(value = "Label")
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@JsonProperty(value = "parent")
	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	@JsonProperty(value = "isLeaf")
	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	@JsonProperty(value = "isExt")
	public boolean isExt() {
		return isExt;
	}

	public void setExt(boolean isExt) {
		this.isExt = isExt;
	}

	@JsonProperty(value = "loaded")
	public boolean isLoaded() {
		return loaded;
	}

	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}

	@JsonProperty(value = "AddedDate")
	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	@JsonProperty(value = "UpdatedDate")
	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@JsonProperty(value = "AddedBy")
	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	@JsonProperty(value = "UpdatedBy")
	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}
