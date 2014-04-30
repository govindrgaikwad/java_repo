package com.tmg.equinox.view.formdesign;

import com.tmg.uifwk.orm.ui.UIElement;

public class UIElementChangeTracker {
	private UIElement uiElement;

	// A -- Added
	// U -- Updated
	// D -- Delete
	// N -- No Change
	private String action = "N";

	public UIElement getUiElement() {
		return uiElement;
	}

	public void setUiElement(UIElement uiElement) {
		this.uiElement = uiElement;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
