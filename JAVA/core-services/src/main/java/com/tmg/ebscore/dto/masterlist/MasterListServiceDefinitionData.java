package com.tmg.ebscore.dto.masterlist;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tmg.ebscore.dto.CoreCommonAttributes;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MasterListServiceDefinitionData", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "MasterListServiceDefinitionData", namespace = "http://www.tmg.com/coreservices/data")
public class MasterListServiceDefinitionData extends CoreCommonAttributes {

	@XmlElement(name = "MasterListServiceDefinitionId")
	private Integer masterListServiceDefinitionId;

	@XmlElement(name = "MasterList1Up")
	private Integer masterList1Up;

	@XmlElement(name = "Category1")
	private MasterListStringValData category1;

	@XmlElement(name = "Category2")
	private MasterListStringValData category2;

	@XmlElement(name = "Category3")
	private MasterListStringValData category3;

	@XmlElement(name = "PlaceOfService")
	private MasterListStringValData placeOfService;

	@XmlElement(name = "InUse")
	private Boolean inUse;

	@XmlElement(name = "IsLocked")
	private Boolean isLocked;

	public Boolean getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}

	public Boolean getInUse() {
		return inUse;
	}

	public void setInUse(Boolean inUse) {
		this.inUse = inUse;
	}

	public Integer getMasterListServiceDefinitionId() {
		return masterListServiceDefinitionId;
	}

	public void setMasterListServiceDefinitionId(
			Integer masterListServiceDefinitionId) {
		this.masterListServiceDefinitionId = masterListServiceDefinitionId;
	}

	public Integer getMasterList1Up() {
		return masterList1Up;
	}

	public void setMasterList1Up(Integer masterList1Up) {
		this.masterList1Up = masterList1Up;
	}

	public MasterListStringValData getCategory1() {
		return category1;
	}

	public void setCategory1(MasterListStringValData category1) {
		this.category1 = category1;
	}

	public MasterListStringValData getCategory2() {
		return category2;
	}

	public void setCategory2(MasterListStringValData category2) {
		this.category2 = category2;
	}

	public MasterListStringValData getCategory3() {
		return category3;
	}

	public void setCategory3(MasterListStringValData category3) {
		this.category3 = category3;
	}

	public MasterListStringValData getPlaceOfService() {
		return placeOfService;
	}

	public void setPlaceOfService(MasterListStringValData placeOfService) {
		this.placeOfService = placeOfService;
	}

}
