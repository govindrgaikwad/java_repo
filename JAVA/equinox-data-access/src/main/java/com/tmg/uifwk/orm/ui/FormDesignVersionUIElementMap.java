package com.tmg.uifwk.orm.ui;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.sql.Timestamp;

/**
 * The persistent class for the FormDesignVersionUIElementMap database table.
 * 
 */
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(schema = "UI", name = "FormDesignVersionUIElementMap")
public class FormDesignVersionUIElementMap implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FormDesignVersionUIElementMapID")
	private int formDesignVersionUIElementMapID;

	@Column(name = "EffectiveDate")
	private Timestamp effectiveDate;

	@Column(name = "EffectiveDateOfRemoval")
	private Timestamp effectiveDateOfRemoval;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FormDesignVersionID")
	private FormDesignVersion formDesignVersion;

	// bi-directional many-to-one association to UIElement
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UIElementID")
	private UIElement uielement;

	public FormDesignVersionUIElementMap() {
	}

	public int getFormDesignVersionUIElementMapID() {
		return this.formDesignVersionUIElementMapID;
	}

	public void setFormDesignVersionUIElementMapID(
			int formDesignVersionUIElementMapID) {
		this.formDesignVersionUIElementMapID = formDesignVersionUIElementMapID;
	}

	public Timestamp getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Timestamp effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Timestamp getEffectiveDateOfRemoval() {
		return this.effectiveDateOfRemoval;
	}

	public void setEffectiveDateOfRemoval(Timestamp effectiveDateOfRemoval) {
		this.effectiveDateOfRemoval = effectiveDateOfRemoval;
	}

	public FormDesignVersion getFormDesignVersion() {
		return formDesignVersion;
	}

	public void setFormDesignVersion(FormDesignVersion formDesignVersion) {
		this.formDesignVersion = formDesignVersion;
	}

	public UIElement getUielement() {
		return this.uielement;
	}

	public void setUielement(UIElement uielement) {
		this.uielement = uielement;
	}

}