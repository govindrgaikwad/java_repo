package com.tmg.ebscore.orm.packageversion;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tmg.ebscore.orm.CoreCommonAttributes;
import com.tmg.ebscore.orm.ebspackage.PkgConfigOption;
import com.tmg.ebscore.orm.template.PackageTemplate;

@Entity
@Table(schema = "Tmplt", name = "PkgVersionWorkflowState")
public class PkgVersionWorkflowState extends CoreCommonAttributes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PkgVersionWorkflowState1up")
	private Integer pkgVersionWorkflowState1up;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PackageTemplate1up")
	private PackageTemplate packageTemplate;

	@Column(name = "Name")
	private String name;

	@Column(name = "Description")
	private String description;

	@Column(name = "IsReleasedFlag")
	private Boolean releasedFlag;

	@Column(name = "IsDefaultValFlag")
	private Boolean defaultValFlag;

	@Column(name = "DisplayOrder")
	private Integer displayOrder;

	@OneToMany(mappedBy = "pkgVersionWorkflowState", fetch = FetchType.LAZY)
	private Set<PkgVersion> pkgVerWorkFlow = new HashSet<PkgVersion>();

	@OneToMany(mappedBy = "pkgVersionWorkflowState", fetch = FetchType.LAZY)
	private Set<PkgConfigOption> configOptionVerState = new HashSet<PkgConfigOption>();

	public Set<PkgConfigOption> getConfigOptionVerState() {
		return configOptionVerState;
	}

	public void setConfigOptionVerState(
			Set<PkgConfigOption> configOptionVerState) {
		this.configOptionVerState = configOptionVerState;
	}

	public Set<PkgVersion> getPkgVerWorkFlow() {
		return pkgVerWorkFlow;
	}

	public void setPkgVerWorkFlow(Set<PkgVersion> pkgVerWorkFlow) {
		this.pkgVerWorkFlow = pkgVerWorkFlow;
	}

	public Integer getPkgVersionWorkflowState1up() {
		return pkgVersionWorkflowState1up;
	}

	public void setPkgVersionWorkflowState1up(Integer pkgVersionWorkflowState1up) {
		this.pkgVersionWorkflowState1up = pkgVersionWorkflowState1up;
	}

	public PackageTemplate getPackageTemplate() {
		return packageTemplate;
	}

	public void setPackageTemplate(PackageTemplate packageTemplate) {
		this.packageTemplate = packageTemplate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getReleasedFlag() {
		return releasedFlag;
	}

	public void setReleasedFlag(Boolean releasedFlag) {
		this.releasedFlag = releasedFlag;
	}

	public Boolean getDefaultValFlag() {
		return defaultValFlag;
	}

	public void setDefaultValFlag(Boolean defaultValFlag) {
		this.defaultValFlag = defaultValFlag;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

}
