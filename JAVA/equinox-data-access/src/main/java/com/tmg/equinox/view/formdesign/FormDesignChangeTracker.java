package com.tmg.equinox.view.formdesign;

import com.tmg.uifwk.orm.ui.FormDesign;

public class FormDesignChangeTracker {

	private FormDesign formDesign;

	private String action = "N";

	public FormDesign getFormDesign() {
		return formDesign;
	}

	public void setFormDesign(FormDesign formDesign) {
		this.formDesign = formDesign;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
