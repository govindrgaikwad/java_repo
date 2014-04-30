package com.tmg.ebscore.orm.template;

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
import com.tmg.ebscore.orm.masterlist.MasterList;

@Entity
@Table(schema = "Const", name = "SwitchCriteria")
public class SwitchCriteria extends CoreCommonAttributes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SwitchCriteria1up")
	private Integer switchCriteria1up;

	@Column(name = "Name")
	private String name;

	@Column(name = "Description")
	private String description;

	@Column(name = "Abbrev")
	private String abbrev;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MasterList1Up")
	private MasterList masterListSwitch;

	@Column(name = "IsActiveFlag")
	private Boolean activeFlag;

	@OneToMany(mappedBy = "switchCriteria", fetch = FetchType.LAZY)
	private Set<TreeShape> shapeTreeSwitch = new HashSet<TreeShape>();

	public Set<TreeShape> getShapeTreeSwitch() {
		return shapeTreeSwitch;
	}

	public void setShapeTreeSwitch(Set<TreeShape> shapeTreeSwitch) {
		this.shapeTreeSwitch = shapeTreeSwitch;
	}

	public Integer getSwitchCriteria1up() {
		return switchCriteria1up;
	}

	public void setSwitchCriteria1up(Integer switchCriteria1up) {
		this.switchCriteria1up = switchCriteria1up;
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

	public String getAbbrev() {
		return abbrev;
	}

	public void setAbbrev(String abbrev) {
		this.abbrev = abbrev;
	}

	public MasterList getMasterListSwitch() {
		return masterListSwitch;
	}

	public void setMasterListSwitch(MasterList masterListSwitch) {
		this.masterListSwitch = masterListSwitch;
	}

	public Boolean getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Boolean activeFlag) {
		this.activeFlag = activeFlag;
	}

}
