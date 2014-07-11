package com.empresa.marco.data;

public class ForeignKey {

	private String parentObject;

	private String parentAttribute;

	private String childObject;

	private String childAttribute;
	
	private String referenceName;

	public String getParentObject() {
		return parentObject;
	}

	public void setParentObject(String parentObject) {
		this.parentObject = parentObject;
	}

	public String getParentAttribute() {
		return parentAttribute;
	}

	public void setParentAttribute(String parentAttribute) {
		this.parentAttribute = parentAttribute;
	}

	public String getChildObject() {
		return childObject;
	}

	public void setChildObject(String childObject) {
		this.childObject = childObject;
	}

	public String getChildAttribute() {
		return childAttribute;
	}

	public void setChildAttribute(String childAttribute) {
		this.childAttribute = childAttribute;
	}

	public String getReferenceName() {
		return referenceName;
	}

	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}

}
