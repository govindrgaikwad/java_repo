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

@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Entity
@Table(schema = "UI", name = "RegexLibrary")
public class RegexLibrary extends BaseEntityAttributes {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LibraryRegexID")
	private Integer libraryRegexID;

	@Column(name = "LibraryRegexName")
	private String libraryRegexName;

	@Column(name = "RegexValue")
	private String regexValue;

	@Column(name = "Description")
	private String description;

	@Column(name = "IsActive")
	private Boolean active;

	@OneToMany(mappedBy = "regexLibrary", fetch = FetchType.LAZY)
	private List<Validator> validators;

	public Integer getLibraryRegexID() {
		return libraryRegexID;
	}

	public void setLibraryRegexID(Integer libraryRegexID) {
		this.libraryRegexID = libraryRegexID;
	}

	public String getLibraryRegexName() {
		return libraryRegexName;
	}

	public void setLibraryRegexName(String libraryRegexName) {
		this.libraryRegexName = libraryRegexName;
	}

	public String getRegexValue() {
		return regexValue;
	}

	public void setRegexValue(String regexValue) {
		this.regexValue = regexValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<Validator> getValidators() {
		return validators;
	}

	public void setValidators(List<Validator> validators) {
		this.validators = validators;
	}

}
