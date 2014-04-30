package com.tmg.ebscore.orm.masterlist;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tmg.ebscore.orm.CoreCommonAttributes;
import com.tmg.ebscore.orm.ebspackage.ExcelViewInfo;
import com.tmg.ebscore.orm.template.SetValueParam;
import com.tmg.ebscore.orm.template.SwitchCriteria;

@Entity
@Table(schema = "Mast", name = "MasterList")
public class MasterList extends CoreCommonAttributes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MasterList1up")
	private Integer masterList1up;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MasterListDataType1up")
	private MasterListDataType masterListDataType;

	@Column(name = "SpecialListName")
	private String specialListName;

	@Column(name = "Name")
	private String name;

	@Column(name = "Abbrev")
	private String abbrev;

	@Column(name = "Description")
	private String description;

	@Column(name = "AllowsNAFlag")
	private Boolean allowsNAFlag;

	@Column(name = "Length")
	private Integer length;

	@Column(name = "Precision")
	private Integer precision;

	@Column(name = "Scale")
	private Integer scale;

	@Column(name = "IsActiveFlag")
	private Boolean activeFlag;

	@Column(name = "IsAccumulationAvailableFlag")
	private Boolean accumulationAvailableFlag;

	@Column(name = "IsVisibleAtTemplate")
	private Boolean visibleAtTemplate;

	@Column(name = "IsVisibleAtMasterList")
	private Boolean visibleAtMasterList;

	@Column(name = "HasExtensionForFacets")
	private Boolean hasExtensionForFacets;

	@OneToMany(mappedBy = "masterListAccumVal", fetch = FetchType.LAZY)
	private Set<MasterListAccumulationVal> masterAccumVal = new HashSet<MasterListAccumulationVal>();

	@OneToMany(mappedBy = "masterListInt", fetch = FetchType.LAZY)
	private Set<MasterListIntVal> masterInt = new HashSet<MasterListIntVal>();

	@OneToMany(mappedBy = "masterListLimit", fetch = FetchType.LAZY)
	private Set<MasterListLimitVal> masterLimit = new HashSet<MasterListLimitVal>();

	@OneToMany(mappedBy = "masterListMessage", fetch = FetchType.LAZY)
	private Set<MasterListMessageVal> masterMessage = new HashSet<MasterListMessageVal>();

	@OneToMany(mappedBy = "masterListService", fetch = FetchType.LAZY)
	private Set<MasterListServiceDefinition> masterService = new HashSet<MasterListServiceDefinition>();

	@OneToMany(mappedBy = "masterListString", fetch = FetchType.LAZY)
	private Set<MasterListStringVal> masterString = new HashSet<MasterListStringVal>();

	@OneToMany(mappedBy = "masterListSetValueParam", fetch = FetchType.LAZY)
	private Set<SetValueParam> masterParam = new HashSet<SetValueParam>();

	@OneToMany(mappedBy = "masterListSwitch", fetch = FetchType.LAZY)
	private Set<SwitchCriteria> masterSwitch = new HashSet<SwitchCriteria>();

	@OneToMany(mappedBy = "masterList", fetch = FetchType.LAZY)
	private Set<ExcelViewInfo> masterTreeExcel = new HashSet<ExcelViewInfo>();

	public Set<SwitchCriteria> getMasterSwitch() {
		return masterSwitch;
	}

	public void setMasterSwitch(Set<SwitchCriteria> masterSwitch) {
		this.masterSwitch = masterSwitch;
	}

	public Set<ExcelViewInfo> getMasterTreeExcel() {
		return masterTreeExcel;
	}

	public void setMasterTreeExcel(Set<ExcelViewInfo> masterTreeExcel) {
		this.masterTreeExcel = masterTreeExcel;
	}

	public Set<SetValueParam> getMasterParam() {
		return masterParam;
	}

	public void setMasterParam(Set<SetValueParam> masterParam) {
		this.masterParam = masterParam;
	}

	public Set<MasterListAccumulationVal> getMasterAccumVal() {
		return masterAccumVal;
	}

	public void setMasterAccumVal(Set<MasterListAccumulationVal> masterAccumVal) {
		this.masterAccumVal = masterAccumVal;
	}

	public Set<MasterListIntVal> getMasterInt() {
		return masterInt;
	}

	public void setMasterInt(Set<MasterListIntVal> masterInt) {
		this.masterInt = masterInt;
	}

	public Set<MasterListLimitVal> getMasterLimit() {
		return masterLimit;
	}

	public void setMasterLimit(Set<MasterListLimitVal> masterLimit) {
		this.masterLimit = masterLimit;
	}

	public Set<MasterListMessageVal> getMasterMessage() {
		return masterMessage;
	}

	public void setMasterMessage(Set<MasterListMessageVal> masterMessage) {
		this.masterMessage = masterMessage;
	}

	public Set<MasterListServiceDefinition> getMasterService() {
		return masterService;
	}

	public void setMasterService(Set<MasterListServiceDefinition> masterService) {
		this.masterService = masterService;
	}

	public Set<MasterListStringVal> getMasterString() {
		return masterString;
	}

	public void setMasterString(Set<MasterListStringVal> masterString) {
		this.masterString = masterString;
	}

	public Integer getMasterList1up() {
		return masterList1up;
	}

	public void setMasterList1up(Integer masterList1up) {
		this.masterList1up = masterList1up;
	}

	public MasterListDataType getMasterListDataType() {
		return masterListDataType;
	}

	public void setMasterListDataType(MasterListDataType masterListDataType) {
		this.masterListDataType = masterListDataType;
	}

	public String getSpecialListName() {
		return specialListName;
	}

	public void setSpecialListName(String specialListName) {
		this.specialListName = specialListName;
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

	public Boolean getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Boolean activeFlag) {
		this.activeFlag = activeFlag;
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
