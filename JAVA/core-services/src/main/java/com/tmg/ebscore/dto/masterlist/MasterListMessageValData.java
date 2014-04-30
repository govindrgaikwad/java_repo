package com.tmg.ebscore.dto.masterlist;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tmg.ebscore.dto.CoreCommonAttributes;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MasterListMessageValData", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "MasterListMessageValData", namespace = "http://www.tmg.com/coreservices/data")
public class MasterListMessageValData extends CoreCommonAttributes {

	@XmlElement(name = "MasterListMessageVal1up")
	private Integer masterListMessageVal1up;

	@XmlElement(name = "InUse")
	private Boolean inUse;

	@XmlElement(name = "IsLocked")
	private Boolean isLocked;

	@XmlElement(name = "MasterList1up")
	private Integer masterList1up;

	@XmlElement(name = "MessageCategory")
	private String messageCategory;

	@XmlElement(name = "MessageValue")
	private String messageValue;

	@XmlElement(name = "MessageType")
	private String messageType;

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
