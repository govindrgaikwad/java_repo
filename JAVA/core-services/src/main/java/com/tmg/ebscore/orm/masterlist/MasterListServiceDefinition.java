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
import com.tmg.ebscore.orm.ebspackage.PkgMasterListForBig3CO;
import com.tmg.ebscore.orm.template.TemplateAllowsServiceDefinition;

@Entity
@Table(schema = "Mast", name = "MasterListServiceDefinition")
public class MasterListServiceDefinition extends CoreCommonAttributes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MasterListServiceDefinition1up")
	private Integer masterListServiceDefinition1up;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MasterList1Up")
	private MasterList masterListService;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BenCat1StringVal1up")
	private MasterListStringVal masterListStringValCat1;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BenCat2StringVal1up")
	private MasterListStringVal masterListStringValCat2;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BenCat3StringVal1up")
	private MasterListStringVal masterListStringValCat3;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "POSStringVal1up")
	private MasterListStringVal masterListStringValPostString;

	@OneToMany(mappedBy = "templateAllowsServiceDefinitionId.masterListServiceDefinition", fetch = FetchType.LAZY)
	private Set<TemplateAllowsServiceDefinition> templateAllowService = new HashSet<TemplateAllowsServiceDefinition>();

	@OneToMany(mappedBy = "masterListServiceDefinition", fetch = FetchType.LAZY)
	private Set<PkgMasterListForBig3CO> benPkgMasterList = new HashSet<PkgMasterListForBig3CO>();

	public Set<PkgMasterListForBig3CO> getBenPkgMasterList() {
		return benPkgMasterList;
	}

	public void setBenPkgMasterList(Set<PkgMasterListForBig3CO> benPkgMasterList) {
		this.benPkgMasterList = benPkgMasterList;
	}

	public Set<TemplateAllowsServiceDefinition> getTemplateAllowService() {
		return templateAllowService;
	}

	public void setTemplateAllowService(
			Set<TemplateAllowsServiceDefinition> templateAllowService) {
		this.templateAllowService = templateAllowService;
	}

	public Integer getMasterListServiceDefinition1up() {
		return masterListServiceDefinition1up;
	}

	public void setMasterListServiceDefinition1up(
			Integer masterListServiceDefinition1up) {
		this.masterListServiceDefinition1up = masterListServiceDefinition1up;
	}

	public MasterList getMasterListService() {
		return masterListService;
	}

	public void setMasterListService(MasterList masterListService) {
		this.masterListService = masterListService;
	}

	public MasterListStringVal getMasterListStringValCat1() {
		return masterListStringValCat1;
	}

	public void setMasterListStringValCat1(
			MasterListStringVal masterListStringValCat1) {
		this.masterListStringValCat1 = masterListStringValCat1;
	}

	public MasterListStringVal getMasterListStringValCat2() {
		return masterListStringValCat2;
	}

	public void setMasterListStringValCat2(
			MasterListStringVal masterListStringValCat2) {
		this.masterListStringValCat2 = masterListStringValCat2;
	}

	public MasterListStringVal getMasterListStringValCat3() {
		return masterListStringValCat3;
	}

	public void setMasterListStringValCat3(
			MasterListStringVal masterListStringValCat3) {
		this.masterListStringValCat3 = masterListStringValCat3;
	}

	public MasterListStringVal getMasterListStringValPostString() {
		return masterListStringValPostString;
	}

	public void setMasterListStringValPostString(
			MasterListStringVal masterListStringValPostString) {
		this.masterListStringValPostString = masterListStringValPostString;
	}

}
