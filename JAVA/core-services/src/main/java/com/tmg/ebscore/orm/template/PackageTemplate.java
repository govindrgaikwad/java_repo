package com.tmg.ebscore.orm.template;

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
import com.tmg.ebscore.orm.ebspackage.AdminContentDesign;
import com.tmg.ebscore.orm.ebspackage.BenefitPackage;
import com.tmg.ebscore.orm.ebspackage.ExcelViewInfo;
import com.tmg.ebscore.orm.masterlist.MasterListStringVal;
import com.tmg.ebscore.orm.packageversion.PkgVersionWorkflowState;

@Entity
@Table(schema = "Tmplt", name = "PackageTemplate")
public class PackageTemplate extends CoreCommonAttributes

{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PackageTemplate1up")
	private Integer packageTemplate1up;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BenefitPackageTypeAsString1up")
	private MasterListStringVal benefitPackageTypeAsString;

	@Column(name = "ID")
	private String id;

	@Column(name = "Name")
	private String name;

	@Column(name = "Description")
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TemplateWorkflowState1up")
	private TemplateWorkflowState templateWorkflowState;

	@OneToMany(mappedBy = "templateAllowsIntValId.packageTemplate", fetch = FetchType.LAZY)
	private Set<TemplateAllowsIntVal> templateAllowIntVal = new HashSet<TemplateAllowsIntVal>();

	@OneToMany(mappedBy = "templateAllowsStringValId.packageTemplate", fetch = FetchType.LAZY)
	private Set<TemplateAllowsStringVal> templateAllowStringVal = new HashSet<TemplateAllowsStringVal>();

	@OneToMany(mappedBy = "templateAllowsLimitValId.packageTemplate", fetch = FetchType.LAZY)
	private Set<TemplateAllowsLimitVal> templateAllowLimitVal = new HashSet<TemplateAllowsLimitVal>();

	@OneToMany(mappedBy = "templateAllowsMessageValId.packageTemplate", fetch = FetchType.LAZY)
	private Set<TemplateAllowsMessageVal> templateAllowMessageVal = new HashSet<TemplateAllowsMessageVal>();

	@OneToMany(mappedBy = "templateAllowsServiceDefinitionId.packageTemplate", fetch = FetchType.LAZY)
	private Set<TemplateAllowsServiceDefinition> templateAllowServiceDefinition = new HashSet<TemplateAllowsServiceDefinition>();

	@OneToMany(mappedBy = "packageTemplate", fetch = FetchType.LAZY)
	private Set<PkgVersionWorkflowState> pkgVerWorkflow = new HashSet<PkgVersionWorkflowState>();

	@OneToMany(mappedBy = "packageTemplate", fetch = FetchType.LAZY)
	private Set<TemplateTree> templateTree = new HashSet<TemplateTree>();

	@OneToMany(mappedBy = "packageTemplate", fetch = FetchType.LAZY)
	private Set<AdminContentDesign> templateAdminContent = new HashSet<AdminContentDesign>();

	@OneToMany(mappedBy = "packageTemplate", fetch = FetchType.LAZY)
	private Set<BenefitPackage> templateBenefitPkg = new HashSet<BenefitPackage>();

	@OneToMany(mappedBy = "packageTemplate", fetch = FetchType.LAZY)
	private Set<ExcelViewInfo> templateTreeExcel = new HashSet<ExcelViewInfo>();

	public Set<ExcelViewInfo> getTemplateTreeExcel() {
		return templateTreeExcel;
	}

	public void setTemplateTreeExcel(Set<ExcelViewInfo> templateTreeExcel) {
		this.templateTreeExcel = templateTreeExcel;
	}

	public Set<BenefitPackage> getTemplateBenefitPkg() {
		return templateBenefitPkg;
	}

	public void setTemplateBenefitPkg(Set<BenefitPackage> templateBenefitPkg) {
		this.templateBenefitPkg = templateBenefitPkg;
	}

	public Set<AdminContentDesign> getTemplateAdminContent() {
		return templateAdminContent;
	}

	public void setTemplateAdminContent(
			Set<AdminContentDesign> templateAdminContent) {
		this.templateAdminContent = templateAdminContent;
	}

	public Set<PkgVersionWorkflowState> getPkgVerWorkflow() {
		return pkgVerWorkflow;
	}

	public void setPkgVerWorkflow(Set<PkgVersionWorkflowState> pkgVerWorkflow) {
		this.pkgVerWorkflow = pkgVerWorkflow;
	}

	public Set<TemplateAllowsIntVal> getTemplateAllowIntVal() {
		return templateAllowIntVal;
	}

	public void setTemplateAllowIntVal(
			Set<TemplateAllowsIntVal> templateAllowIntVal) {
		this.templateAllowIntVal = templateAllowIntVal;
	}

	public Set<TemplateAllowsStringVal> getTemplateAllowStringVal() {
		return templateAllowStringVal;
	}

	public void setTemplateAllowStringVal(
			Set<TemplateAllowsStringVal> templateAllowStringVal) {
		this.templateAllowStringVal = templateAllowStringVal;
	}

	public Set<TemplateAllowsLimitVal> getTemplateAllowLimitVal() {
		return templateAllowLimitVal;
	}

	public void setTemplateAllowLimitVal(
			Set<TemplateAllowsLimitVal> templateAllowLimitVal) {
		this.templateAllowLimitVal = templateAllowLimitVal;
	}

	public Set<TemplateAllowsMessageVal> getTemplateAllowMessageVal() {
		return templateAllowMessageVal;
	}

	public void setTemplateAllowMessageVal(
			Set<TemplateAllowsMessageVal> templateAllowMessageVal) {
		this.templateAllowMessageVal = templateAllowMessageVal;
	}

	public Set<TemplateAllowsServiceDefinition> getTemplateAllowServiceDefinition() {
		return templateAllowServiceDefinition;
	}

	public void setTemplateAllowServiceDefinition(
			Set<TemplateAllowsServiceDefinition> templateAllowServiceDefinition) {
		this.templateAllowServiceDefinition = templateAllowServiceDefinition;
	}

	public Set<TemplateTree> getTemplateTree() {
		return templateTree;
	}

	public void setTemplateTree(Set<TemplateTree> templateTree) {
		this.templateTree = templateTree;
	}

	public Integer getPackageTemplate1up() {
		return packageTemplate1up;
	}

	public void setPackageTemplate1up(Integer packageTemplate1up) {
		this.packageTemplate1up = packageTemplate1up;
	}

	public MasterListStringVal getBenefitPackageTypeAsString() {
		return benefitPackageTypeAsString;
	}

	public void setBenefitPackageTypeAsString(
			MasterListStringVal benefitPackageTypeAsString) {
		this.benefitPackageTypeAsString = benefitPackageTypeAsString;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public TemplateWorkflowState getTemplateWorkflowState() {
		return templateWorkflowState;
	}

	public void setTemplateWorkflowState(
			TemplateWorkflowState templateWorkflowState) {
		this.templateWorkflowState = templateWorkflowState;
	}

}
