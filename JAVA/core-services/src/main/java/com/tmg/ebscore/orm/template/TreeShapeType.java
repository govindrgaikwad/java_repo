package com.tmg.ebscore.orm.template;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tmg.ebscore.orm.CoreCommonAttributes;

@Entity
@Table(schema = "Const", name = "TreeShapeType")
public class TreeShapeType extends CoreCommonAttributes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TreeShapeType1up")
	private Integer treeShapeType1up;

	@Column(name = "Name")
	private String name;

	@Column(name = "Description")
	private String description;

	@Column(name = "Abbrev")
	private String abbrev;

	@Column(name = "IsActiveFlag")
	private Boolean activeFlag;

	@OneToMany(mappedBy = "treeShapeType", fetch = FetchType.LAZY)
	private Set<TreeShape> shapeTreeType = new HashSet<TreeShape>();

	public Set<TreeShape> getShapeTreeType() {
		return shapeTreeType;
	}

	public void setShapeTreeType(Set<TreeShape> shapeTreeType) {
		this.shapeTreeType = shapeTreeType;
	}

	public Integer getTreeShapeType1up() {
		return treeShapeType1up;
	}

	public void setTreeShapeType1up(Integer treeShapeType1up) {
		this.treeShapeType1up = treeShapeType1up;
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

	public Boolean getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Boolean activeFlag) {
		this.activeFlag = activeFlag;
	}

}
