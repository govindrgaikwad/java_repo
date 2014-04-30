package com.tmg.ebscore.dto;

import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IsLockedData", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "IsLockedData", namespace = "http://www.tmg.com/coreservices/data")
@Entity
@SqlResultSetMappings({ @SqlResultSetMapping(name = "IsLockedData", columns = {
		@ColumnResult(name = "MasterListVal1up"),
		@ColumnResult(name = "IsInUse"), @ColumnResult(name = "IsLocked") })

})
public class IsLockedData {

	@XmlElement(name = "IsInUse")
	private Boolean isInUse;

	@XmlElement(name = "IsLocked")
	private Boolean isLocked;

	@Id
	@XmlElement(name = "Id")
	private Integer masterListVal1up;

	public Boolean getIsInUse() {
		return isInUse;
	}

	public void setIsInUse(Boolean isInUse) {
		this.isInUse = isInUse;
	}

	public Boolean getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}

	public Integer getMasterListVal1up() {
		return masterListVal1up;
	}

	public void setMasterListVal1up(Integer masterListVal1up) {
		this.masterListVal1up = masterListVal1up;
	}

}
