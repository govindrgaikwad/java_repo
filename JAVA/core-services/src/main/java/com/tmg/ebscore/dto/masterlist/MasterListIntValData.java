package com.tmg.ebscore.dto.masterlist;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tmg.ebscore.dto.CoreCommonAttributes;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MasterListIntValData", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "MasterListIntValData", namespace = "http://www.tmg.com/coreservices/data")
public class MasterListIntValData extends CoreCommonAttributes {

	@XmlElement(name = "MasterListIntVal1up")
	private Integer masterListIntVal1up;

	@XmlElement(name = "MasterList1up")
	private Integer masterList1up;

	@XmlElement(name = "IntAsDecimalValue")
	private Double intAsDecimalValue;

	@XmlElement(name = "Abbrev")
	private String abbrev;

	@XmlElement(name = "Accumulation1up")
	private Integer accumulation1up;

	@XmlElement(name = "InUse")
	private Boolean inUse;

	@XmlElement(name = "IsLocked")
	private Boolean isLocked;

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

	public Integer getMasterListIntVal1up() {
		return masterListIntVal1up;
	}

	public void setMasterListIntVal1up(Integer masterListIntVal1up) {
		this.masterListIntVal1up = masterListIntVal1up;
	}

	public Integer getMasterList1up() {
		return masterList1up;
	}

	public void setMasterList1up(Integer masterList1up) {
		this.masterList1up = masterList1up;
	}

	public Double getIntAsDecimalValue() {
		return intAsDecimalValue;
	}

	public void setIntAsDecimalValue(Double intAsDecimalValue) {
		this.intAsDecimalValue = intAsDecimalValue;
	}

	public String getAbbrev() {
		return abbrev;
	}

	public void setAbbrev(String abbrev) {
		this.abbrev = abbrev;
	}

	public Integer getAccumulation1up() {
		return accumulation1up;
	}

	public void setAccumulation1up(Integer accumulation1up) {
		this.accumulation1up = accumulation1up;
	}

}
