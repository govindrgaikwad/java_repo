package com.tmg.uifwk.orm.ui;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * The persistent class for the FormDesignGroupMapping database table.
 * 
 */
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(schema = "UI", name = "FormDesignGroupMapping")
public class FormDesignGroupMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FormDesignGroupMappingID")
	private int formDesignGroupMappingID;

	@Column(name = "AccessibleToRoles")
	private String accessibleToRoles;

	@Column(name = "AllowMultipleInstance")
	private boolean allowMultipleInstance;

	@Column(name = "Sequence")
	private int sequence;

	// bi-directional many-to-one association to FormDesign
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "FormID")
	private FormDesign formDesign;

	// bi-directional many-to-one association to FormDesignGroup
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "FormDesignGroupID")
	private FormDesignGroup formDesignGroup;

	public FormDesignGroupMapping() {
	}

	public int getFormDesignGroupMappingID() {
		return this.formDesignGroupMappingID;
	}

	public void setFormDesignGroupMappingID(int formDesignGroupMappingID) {
		this.formDesignGroupMappingID = formDesignGroupMappingID;
	}

	public String getAccessibleToRoles() {
		return this.accessibleToRoles;
	}

	public void setAccessibleToRoles(String accessibleToRoles) {
		this.accessibleToRoles = accessibleToRoles;
	}

	public boolean getAllowMultipleInstance() {
		return this.allowMultipleInstance;
	}

	public void setAllowMultipleInstance(boolean allowMultipleInstance) {
		this.allowMultipleInstance = allowMultipleInstance;
	}

	public int getSequence() {
		return this.sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public FormDesign getFormDesign() {
		return this.formDesign;
	}

	public void setFormDesign(FormDesign formDesign) {
		this.formDesign = formDesign;
	}

	public FormDesignGroup getFormDesignGroup() {
		return this.formDesignGroup;
	}

	public void setFormDesignGroup(FormDesignGroup formDesignGroup) {
		this.formDesignGroup = formDesignGroup;
	}

}