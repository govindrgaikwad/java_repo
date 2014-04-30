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

/**
 * The persistent class for the Status database table.
 * 
 */
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Entity
@Table(schema = "UI", name = "Status")
public class Status extends BaseEntityAttributes {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "StatusID")
	private int statusID;

	@Column(name = "IsActive")
	private boolean active;

	@Column(name = "Status")
	private String status;

	// bi-directional many-to-one association to FormDesignVersion
	@OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
	private List<FormDesignVersion> formDesignVersions;

	public Status() {
	}

	public int getStatusID() {
		return this.statusID;
	}

	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<FormDesignVersion> getFormDesignVersions() {
		return this.formDesignVersions;
	}

	public void setFormDesignVersions(List<FormDesignVersion> formDesignVersions) {
		this.formDesignVersions = formDesignVersions;
	}

	public FormDesignVersion addFormDesignVersion(
			FormDesignVersion formDesignVersion) {
		getFormDesignVersions().add(formDesignVersion);
		formDesignVersion.setStatus(this);

		return formDesignVersion;
	}

	public FormDesignVersion removeFormDesignVersion(
			FormDesignVersion formDesignVersion) {
		getFormDesignVersions().remove(formDesignVersion);
		formDesignVersion.setStatus(null);

		return formDesignVersion;
	}

}