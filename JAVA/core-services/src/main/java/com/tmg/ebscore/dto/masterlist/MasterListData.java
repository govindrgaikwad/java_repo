package com.tmg.ebscore.dto.masterlist;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tmg.ebscore.dto.CoreCommonAttributes;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MasterListData", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "MasterListData", namespace = "http://www.tmg.com/coreservices/data")
public class MasterListData extends CoreCommonAttributes {

	@XmlElement(name = "MasterList1up")
	private Integer id;

	@XmlElement(name = "DataType")
	private String dataType;

	@XmlElement(name = "Name")
	private String name;

	@XmlElement(name = "Abbrev")
	private String abbrev;

	@XmlElement(name = "Description")
	private String description;

	@XmlElement(name = "AllowsNAFlag")
	private Boolean allowsNAFlag;

	@XmlElement(name = "Length")
	private Integer length;

	@XmlElement(name = "Precision")
	private Integer precision;

	@XmlElement(name = "Scale")
	private Integer scale;

	@XmlElement(name = "Active")
	private Boolean active;

	@XmlElement(name = "AccumulationAvailableFlag")
	private Boolean accumulationAvailableFlag;

	@XmlElement(name = "VisibleAtTemplate")
	private Boolean visibleAtTemplate;

	@XmlElement(name = "VisibleAtMasterList")
	private Boolean visibleAtMasterList;

	@XmlElement(name = "hasExtensionForFacets")
	private Boolean hasExtensionForFacets;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbrev() {
		return abbrev;
	}

	public void setAbbrev(String abbrev) {
		this.abbrev = abbrev;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getAllowsNAFlag() {
		return allowsNAFlag;
	}

	public void setAllowsNAFlag(Boolean allowsNAFlag) {
		this.allowsNAFlag = allowsNAFlag;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getPrecision() {
		return precision;
	}

	public void setPrecision(Integer precision) {
		this.precision = precision;
	}

	public Integer getScale() {
		return scale;
	}

	public void setScale(Integer scale) {
		this.scale = scale;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getAccumulationAvailableFlag() {
		return accumulationAvailableFlag;
	}

	public void setAccumulationAvailableFlag(Boolean accumulationAvailableFlag) {
		this.accumulationAvailableFlag = accumulationAvailableFlag;
	}

	public Boolean getVisibleAtTemplate() {
		return visibleAtTemplate;
	}

	public void setVisibleAtTemplate(Boolean visibleAtTemplate) {
		this.visibleAtTemplate = visibleAtTemplate;
	}

	public Boolean getVisibleAtMasterList() {
		return visibleAtMasterList;
	}

	public void setVisibleAtMasterList(Boolean visibleAtMasterList) {
		this.visibleAtMasterList = visibleAtMasterList;
	}

	public Boolean getHasExtensionForFacets() {
		return hasExtensionForFacets;
	}

	public void setHasExtensionForFacets(Boolean hasExtensionForFacets) {
		this.hasExtensionForFacets = hasExtensionForFacets;
	}

}
