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
 * The persistent class for the FormDesignGroup database table.
 * 
 */
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(schema = "UI", name = "FormDesignGroup")
public class FormDesignGroup extends BaseEntityAttributes {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FormDesignGroupID")
	private int formDesignGroupID;

	@Column(name = "GroupName")
	private String groupName;

	@Column(name = "TenantID")
	private int tenantID;

	// bi-directional many-to-one association to FormDesignGroupMapping
	@OneToMany(mappedBy = "formDesignGroup",fetch=FetchType.LAZY)
	private List<FormDesignGroupMapping> formDesignGroupMappings;

	public FormDesignGroup() {
	}

	public int getFormDesignGroupID() {
		return this.formDesignGroupID;
	}

	public void setFormDesignGroupID(int formDesignGroupID) {
		this.formDesignGroupID = formDesignGroupID;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getTenantID() {
		return this.tenantID;
	}

	public void setTenantID(int tenantID) {
		this.tenantID = tenantID;
	}

	public List<FormDesignGroupMapping> getFormDesignGroupMappings() {
		return this.formDesignGroupMappings;
	}

	public void setFormDesignGroupMappings(
			List<FormDesignGroupMapping> formDesignGroupMappings) {
		this.formDesignGroupMappings = formDesignGroupMappings;
	}

	public FormDesignGroupMapping addFormDesignGroupMapping(
			FormDesignGroupMapping formDesignGroupMapping) {
		getFormDesignGroupMappings().add(formDesignGroupMapping);
		formDesignGroupMapping.setFormDesignGroup(this);

		return formDesignGroupMapping;
	}

	public FormDesignGroupMapping removeFormDesignGroupMapping(
			FormDesignGroupMapping formDesignGroupMapping) {
		getFormDesignGroupMappings().remove(formDesignGroupMapping);
		formDesignGroupMapping.setFormDesignGroup(null);

		return formDesignGroupMapping;
	}

}