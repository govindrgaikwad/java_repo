package com.tmg.ebscore.orm.masterlist;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tmg.ebscore.orm.CoreCommonAttributes;

@Entity
@Table(schema = "Const", name = "MasterListDataType")
public class MasterListDataType extends CoreCommonAttributes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MasterListDataType1up")
	private Integer masterListDataType1up;

	@Column(name = "Name")
	private String name;

	@Column(name = "Description")
	private String description;

	@Column(name = "Abbrev")
	private String abbrev;

	@Column(name = "ValueSchema")
	private String valueSchema;

	@Column(name = "ValueTable")
	private String valueTable;

	@Column(name = "UseInPackageSchema")
	private String useInPackageSchema;

	@Column(name = "UseInPackageTable")
	private String useInPackageTable;

	@Column(name = "IsActiveFlag")
	private Boolean activeFlag;

	@OneToMany(mappedBy = "masterListDataType", fetch = FetchType.LAZY)
	private Set<MasterList> masterDataType = new HashSet<MasterList>();

	public Set<MasterList> getMasterDataType() {
		return masterDataType;
	}

	public void setMasterDataType(Set<MasterList> masterDataType) {
		this.masterDataType = masterDataType;
	}

	public Integer getMasterListDataType1up() {
		return masterListDataType1up;
	}

	public void setMasterListDataType1up(Integer masterListDataType1up) {
		this.masterListDataType1up = masterListDataType1up;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAbbrev() {
		return abbrev;
	}

	public void setAbbrev(String abbrev) {
		this.abbrev = abbrev;
	}

	public String getValueSchema() {
		return valueSchema;
	}

	public void setValueSchema(String valueSchema) {
		this.valueSchema = valueSchema;
	}

	public String getValueTable() {
		return valueTable;
	}

	public void setValueTable(String valueTable) {
		this.valueTable = valueTable;
	}

	public String getUseInPackageSchema() {
		return useInPackageSchema;
	}

	public void setUseInPackageSchema(String useInPackageSchema) {
		this.useInPackageSchema = useInPackageSchema;
	}

	public String getUseInPackageTable() {
		return useInPackageTable;
	}

	public void setUseInPackageTable(String useInPackageTable) {
		this.useInPackageTable = useInPackageTable;
	}

	public Boolean getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Boolean activeFlag) {
		this.activeFlag = activeFlag;
	}

}
