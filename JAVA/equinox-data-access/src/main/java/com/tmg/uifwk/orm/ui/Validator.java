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
 * The persistent class for the Validator database table.
 * 
 */
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(schema = "UI", name = "Validator")
public class Validator extends BaseEntityAttributes {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ValidatorID")
	private Integer validatorID;

	@Column(name = "IsRequired")
	private Boolean required;

	@Column(name = "Regex")
	private String regex;

	@Column(name = "IsLibraryRegex")
	private Boolean libraryRegex;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LibraryRegexID")
	private RegexLibrary regexLibrary;

	@Column(name = "IsActive")
	private Boolean active;

	@Column(name = "Message")
	private String message;

	// bi-directional many-to-one association to UIElement
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UIElementID")
	private UIElement uielement;

	public Validator() {
	}

	public Integer getValidatorID() {
		return validatorID;
	}

	public void setValidatorID(Integer validatorID) {
		this.validatorID = validatorID;
	}

	public Boolean getRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public Boolean getLibraryRegex() {
		return libraryRegex;
	}

	public void setLibraryRegex(Boolean libraryRegex) {
		this.libraryRegex = libraryRegex;
	}

	public RegexLibrary getRegexLibrary() {
		return regexLibrary;
	}

	public void setRegexLibrary(RegexLibrary regexLibrary) {
		this.regexLibrary = regexLibrary;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UIElement getUielement() {
		return uielement;
	}

	public void setUielement(UIElement uielement) {
		this.uielement = uielement;
	}

}