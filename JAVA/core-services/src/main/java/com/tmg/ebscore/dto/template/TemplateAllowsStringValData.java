package com.tmg.ebscore.dto.template;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tmg.ebscore.dto.CoreCommonAttributes;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TemplateAllowsStringValData", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "TemplateAllowsStringValData", namespace = "http://www.tmg.com/coreservices/data")
public class TemplateAllowsStringValData extends CoreCommonAttributes {

	@XmlElement(name = "InUse")
	private Boolean inUse;

	@XmlElement(name = "Locked")
	private Boolean locked;

	@XmlElement(name = "Allowed")
	private Boolean allowed;

	@XmlElement(name = "Value")
	private String value;

	@XmlElement(name = "Abbrev")
	private String abbrev;

	@XmlElement(name = "PackageTemplate1Up")
	private Integer packageTemplate1Up;

	@XmlElement(name = "MasterListStringVal1up")
	private Integer masterListStringVal1up;

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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getAbbrev() {
		return abbrev;
	}

	public void setAbbrev(String abbrev) {
		this.abbrev = abbrev;
	}

	public Integer getPackageTemplate1Up() {
		return packageTemplate1Up;
	}

	public void setPackageTemplate1Up(Integer packageTemplate1Up) {
		this.packageTemplate1Up = packageTemplate1Up;
	}

	public Integer getMasterListStringVal1up() {
		return masterListStringVal1up;
	}

	public void setMasterListStringVal1up(Integer masterListStringVal1up) {
		this.masterListStringVal1up = masterListStringVal1up;
	}

	public Boolean getDefaultValFlag() {
		return defaultValFlag;
	}

	public void setDefaultValFlag(Boolean defaultValFlag) {
		this.defaultValFlag = defaultValFlag;
	}

}
