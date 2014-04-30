package com.tmg.ebscore.dto.template;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tmg.ebscore.dto.CoreCommonAttributes;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TemplateAllowsIntValData", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "TemplateAllowsIntValData", namespace = "http://www.tmg.com/coreservices/data")
public class TemplateAllowsIntValData extends CoreCommonAttributes {

	@XmlElement(name = "InUse")
	private Boolean inUse;

	@XmlElement(name = "Locked")
	private Boolean locked;

	@XmlElement(name = "Allowed")
	private Boolean allowed;

	@XmlElement(name = "Value")
	private double value;

	@XmlElement(name = "Abbrev")
	private String abbrev;

	@XmlElement(name = "PackageTemplate1Up")
	private Integer packageTemplate1Up;

	@XmlElement(name = "MasterListIntVal1up")
	private Integer masterListIntVal1up;

	@XmlElement(name = "MasterList1up")
	private Integer masterList1up;

	@XmlElement(name = "IsDefaultValFlag")
	private Boolean defaultValFlag;

	public Boolean getInUse() {
		return inUse;
	}

	public void setInUse(Boolean inUse) {
		this.inUse = inUse;
	}

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public Boolean getAllowed() {
		return allowed;
	}

	public void setAllowed(Boolean allowed) {
		this.allowed = allowed;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getAbbrev() {
		return abbrev;
	}

	public void setAbbrev(String abbrev) {
		this.abbrev = abbrev;
	}

	public Integer getMasterList1up() {
		return masterList1up;
	}

	public void setMasterList1up(Integer masterList1up) {
		this.masterList1up = masterList1up;
	}

	public Integer getPackageTemplate1Up() {
		return packageTemplate1Up;
	}

	public void setPackageTemplate1Up(Integer packageTemplate1Up) {
		this.packageTemplate1Up = packageTemplate1Up;
	}

	public Integer getMasterListIntVal1up() {
		return masterListIntVal1up;
	}

	public void setMasterListIntVal1up(Integer masterListIntVal1up) {
		this.masterListIntVal1up = masterListIntVal1up;
	}

	public Boolean getDefaultValFlag() {
		return defaultValFlag;
	}

	public void setDefaultValFlag(Boolean defaultValFlag) {
		this.defaultValFlag = defaultValFlag;
	}

}
