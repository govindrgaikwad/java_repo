package com.tmg.ebscore.dto.template;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tmg.ebscore.dto.CoreCommonAttributes;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TemplateAllowsMessageValData", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "TemplateAllowsMessageValData", namespace = "http://www.tmg.com/coreservices/data")
public class TemplateAllowsMessageValData extends CoreCommonAttributes {

	@XmlElement(name = "InUse")
	private Boolean inUse;

	@XmlElement(name = "Locked")
	private Boolean locked;

	@XmlElement(name = "Allowed")
	private Boolean allowed;

	@XmlElement(name = "MessageCategory")
	private String messageCategory;

	@XmlElement(name = "MessageType")
	private String messageType;

	@XmlElement(name = "MessageValue")
	private String messageValue;

	@XmlElement(name = "PackageTemplate1Up")
	private Integer packageTemplate1Up;

	@XmlElement(name = "MasterListMessageVal1up")
	private Integer masterListMessageVal1up;

	public Integer getPackageTemplate1Up() {
		return packageTemplate1Up;
	}

	public void setPackageTemplate1Up(Integer packageTemplate1Up) {
		this.packageTemplate1Up = packageTemplate1Up;
	}

	public Integer getMasterListMessageVal1up() {
		return masterListMessageVal1up;
	}

	public void setMasterListMessageVal1up(Integer masterListMessageVal1up) {
		this.masterListMessageVal1up = masterListMessageVal1up;
	}

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

	public String getMessageCategory() {
		return messageCategory;
	}

	public void setMessageCategory(String messageCategory) {
		this.messageCategory = messageCategory;
	}

	public String getMessageValue() {
		return messageValue;
	}

	public void setMessageValue(String messageValue) {
		this.messageValue = messageValue;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

}
