package com.tmg.ebscore.dto.masterlist;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tmg.ebscore.dto.CoreCommonAttributes;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MasterListAccumulationValData", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "MasterListAccumulationValData", namespace = "http://www.tmg.com/coreservices/data")
public class MasterListAccumulationValData extends CoreCommonAttributes {

	@XmlElement(name = "Number")
	private Integer number;

	@XmlElement(name = "Description")
	private String description;

	@XmlElement(name = "InUse")
	private Boolean inUse;

	@XmlElement(name = "IsLocked")
	private Boolean isLocked;

	@XmlElement(name = "Type")
	private AccumulationType type;

	@XmlElement(name = "Accumulation1up")
	private Integer accumulation1up;

	@XmlElement(name = "MasterList1up")
	private Integer masterList1up;

	public Integer getMasterList1up() {
		return masterList1up;
	}

	public void setMasterList1up(Integer masterList1up) {
		this.masterList1up = masterList1up;
	}

	public Boolean getInUse() {
		return inUse;
	}

	public void setInUse(Boolean inUse) {
		this.inUse = inUse;
	}

	public Boolean getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AccumulationType getType() {
		return type;
	}

	public void setType(AccumulationType type) {
		this.type = type;
	}

	public Integer getAccumulation1up() {
		return accumulation1up;
	}

	public void setAccumulation1up(Integer accumulation1up) {
		this.accumulation1up = accumulation1up;
	}

}
