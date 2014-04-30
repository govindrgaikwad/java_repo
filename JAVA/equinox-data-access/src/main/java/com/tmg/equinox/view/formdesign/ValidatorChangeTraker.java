package com.tmg.equinox.view.formdesign;

import com.tmg.uifwk.orm.ui.Validator;

public class ValidatorChangeTraker {

	private Validator validator;

	private String action = "N";

	public Validator getValidator() {
		return validator;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
