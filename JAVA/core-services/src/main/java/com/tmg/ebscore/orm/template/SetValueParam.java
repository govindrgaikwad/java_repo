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
@Table(schema = "Const", name = "SetValueParam")
public class SetValueParam extends CoreCommonAttributes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SetValueParam1up")
	private Integer setValueParam1up;

	@Column(name = "Name")
	private String name;

	@Column(name = "Description")
	private String description;

	@Column(name = "Abbrev")
	private String abbrev;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MasterList1Up")
	private MasterList masterListSetValueParam;

	@OneToMany(mappedBy = "setValueParam", fetch = FetchType.LAZY)
	private Set<TreeShape> shapeTreeParam = new HashSet<TreeShape>();

	public Set<TreeShape> getShapeTreeParam() {
		return shapeTreeParam;
	}

	public void setShapeTreeParam(Set<TreeShape> shapeTreeParam) {
		this.shapeTreeParam = shapeTreeParam;
	}

	@Column(name = "IsActiveFlag")
	private Boolean activeFlag;

	public Integer getSetValueParam1up() {
		return setValueParam1up;
	}

	public void setSetValueParam1up(Integer setValueParam1up) {
		this.setValueParam1up = setValueParam1up;
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

	public MasterList getMasterListSetValueParam() {
		return masterListSetValueParam;
	}

	public void setMasterListSetValueParam(MasterList masterListSetValueParam) {
		this.masterListSetValueParam = masterListSetValueParam;
	}

	public Boolean getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Boolean activeFlag) {
		this.activeFlag = activeFlag;
	}

}
