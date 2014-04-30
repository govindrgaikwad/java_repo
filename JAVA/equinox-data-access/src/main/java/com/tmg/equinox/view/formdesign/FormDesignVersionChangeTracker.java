package com.tmg.equinox.view.formdesign;

import com.tmg.uifwk.orm.ui.FormDesignVersion;

public class FormDesignVersionChangeTracker {

	private FormDesignVersion formDesignVersion;

	private String action = "N";

	public FormDesignVersion getFormDesignVersion() {
		return formDesignVersion;
	}

	public void setFormDesignVersion(FormDesignVersion formDesignVersion) {
		this.formDesignVersion = formDesignVersion;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
