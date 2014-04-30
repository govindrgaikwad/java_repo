package com.tmg.ebscore.dto.template;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tmg.ebscore.dto.CoreCommonAttributes;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TemplateAllowsServiceDefinitionData", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "TemplateAllowsServiceDefinitionData", namespace = "http://www.tmg.com/coreservices/data")
public class TemplateAllowsServiceDefinitionData extends CoreCommonAttributes {

	@XmlElement(name = "InUse")
	private Boolean inUse;

	@XmlElement(name = "Locked")
	private Boolean locked;

	@XmlElement(name = "Allowed")
	private Boolean allowed;

	@XmlElement(name = "Category1")
	private String category1;

	@XmlElement(name = "Category2")
	private String category2;

	@XmlElement(name = "Category3")
	private String category3;

	@XmlElement(name = "PlaceOfService")
	private String placeOfService;

	@XmlElement(name = "PackageTemplate1Up")
	private Integer packageTemplate1Up;

	@XmlElement(name = "MasterListServiceDefinition1up")
	private Integer masterListServiceDefinition1up;

	public Integer getPackageTemplate1Up() {
		return packageTemplate1Up;
	}

	public void setPackageTemplate1Up(Integer packageTemplate1Up) {
		this.packageTemplate1Up = packageTemplate1Up;
	}

	public Integer getMasterListServiceDefinition1up() {
		return masterListServiceDefinition1up;
	}

	public void setMasterListServiceDefinition1up(
			Integer masterListServiceDefinition1up) {
		this.masterListServiceDefinition1up = masterListServiceDefinition1up;
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

	public String getCategory1() {
		return category1;
	}

	public void setCategory1(String category1) {
		this.category1 = category1;
	}

	public String getCategory2() {
		return category2;
	}

	public void setCategory2(String category2) {
		this.category2 = category2;
	}

	public String getCategory3() {
		return category3;
	}

	public void setCategory3(String category3) {
		this.category3 = category3;
	}

	public String getPlaceOfService() {
		return placeOfService;
	}

	public void setPlaceOfService(String placeOfService) {
		this.placeOfService = placeOfService;
	}

}
