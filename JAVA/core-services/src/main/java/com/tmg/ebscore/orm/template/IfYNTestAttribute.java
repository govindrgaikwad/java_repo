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
@Table(schema = "Const", name = "IfYNTestAttribute")
public class IfYNTestAttribute extends CoreCommonAttributes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IfYNTestAttribute1up")
	private Integer ifYNTestAttribute1up;

	@Column(name = "Name")
	private String name;

	@Column(name = "Description")
	private String description;

	@Column(name = "Abbrev")
	private String abbrev;

	@Column(name = "IsActiveFlag")
	private Boolean activeFlag;

	@OneToMany(mappedBy = "ifYNTestAttribute", fetch = FetchType.LAZY)
	private Set<TreeShape> shapeTreeTestYN = new HashSet<TreeShape>();

	public Set<TreeShape> getShapeTreeTestYN() {
		return shapeTreeTestYN;
	}

	public void setShapeTreeTestYN(Set<TreeShape> shapeTreeTestYN) {
		this.shapeTreeTestYN = shapeTreeTestYN;
	}

	public Integer getIfYNTestAttribute1up() {
		return ifYNTestAttribute1up;
	}

	public void setIfYNTestAttribute1up(Integer ifYNTestAttribute1up) {
		this.ifYNTestAttribute1up = ifYNTestAttribute1up;
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
