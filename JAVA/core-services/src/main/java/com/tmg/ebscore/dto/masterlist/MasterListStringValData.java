package com.tmg.ebscore.dto.masterlist;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tmg.ebscore.dto.CoreCommonAttributes;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MasterListStringValData", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "MasterListStringValData", namespace = "http://www.tmg.com/coreservices/data")
public class MasterListStringValData extends CoreCommonAttributes {

	@XmlElement(name = "MasterListStringVal1up")
	private Integer masterListStringVal1up;

	@XmlElement(name = "MasterList1up")
	private Integer masterList1up;

	@XmlElement(name = "StringValue")
	private String stringValue;

	@XmlElement(name = "InUse")
	private Boolean inUse;

	@XmlElement(name = "IsLocked")
	private Boolean isLocked;

	@XmlElement(name = "Abbrev")
	private String abbrev;

	public Integer getMasterListStringVal1up() {
		return masterListStringVal1up;
	}

	public void setMasterListStringVal1up(Integer masterListStringVal1up) {
		this.masterListStringVal1up = masterListStringVal1up;
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

	public Integer getMasterList1up() {
		return masterList1up;
	}

	public void setMasterList1up(Integer masterList1up) {
		this.masterList1up = masterList1up;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public String getAbbrev() {
		return abbrev;
	}

	public void setAbbrev(String abbrev) {
		this.abbrev = abbrev;
	}

}
