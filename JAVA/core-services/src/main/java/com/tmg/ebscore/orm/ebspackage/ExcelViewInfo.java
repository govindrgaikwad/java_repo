package com.tmg.ebscore.orm.ebspackage;

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
import com.tmg.ebscore.orm.masterlist.MasterList;
import com.tmg.ebscore.orm.packageversion.PkgVerInstanceTree;
import com.tmg.ebscore.orm.template.PackageTemplate;
import com.tmg.ebscore.orm.template.TreeShape;

@Entity
@Table(schema = "Pkg", name = "ExcelViewInfo")
public class ExcelViewInfo extends CoreCommonAttributes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Column1up")
	private Integer column1up;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TreeShape1up")
	private TreeShape treeShape;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PkgVerInstanceTree1up")
	private PkgVerInstanceTree pkgVerInstanceTree;

	@Column(name = "PackageVersion1up")
	private Integer packageVersion1up;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Template1up")
	private PackageTemplate packageTemplate;

	@Column(name = "Package1up")
	private Integer package1up;

	@Column(name = "Sequence")
	private Integer sequence;

	@Column(name = "DefaultValue")
	private String defaultValue;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MasterList1up")
	private MasterList masterList;

	@Column(name = "ColumnType")
	private String columnType;

	@Column(name = "ColumnDataType")
	private String columnDataType;

	@Column(name = "Header")
	private String header;

	@Column(name = "IsReadOnly")
	private Boolean readOnly;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ParentColumn1up")
	private ExcelViewInfo parentColumn;

	@Column(name = "MasterListVal1Up")
	private Integer masterListVal1Up;

	@Column(name = "DependsOn")
	private String dependsOn;

	@Column(name = "IsOverride")
	private Boolean oIsOverrideverride;

	@OneToMany(mappedBy = "parentColumn", fetch = FetchType.LAZY)
	private Set<ExcelViewInfo> parentTreeExcel = new HashSet<ExcelViewInfo>();

	public Set<ExcelViewInfo> getParentTreeExcel() {
		return parentTreeExcel;
	}

	public void setParentTreeExcel(Set<ExcelViewInfo> parentTreeExcel) {
		this.parentTreeExcel = parentTreeExcel;
	}

	public Integer getColumn1up() {
		return column1up;
	}

	public void setColumn1up(Integer column1up) {
		this.column1up = column1up;
	}

	public TreeShape getTreeShape() {
		return treeShape;
	}

	public void setTreeShape(TreeShape treeShape) {
		this.treeShape = treeShape;
	}

	public PkgVerInstanceTree getPkgVerInstanceTree() {
		return pkgVerInstanceTree;
	}

	public void setPkgVerInstanceTree(PkgVerInstanceTree pkgVerInstanceTree) {
		this.pkgVerInstanceTree = pkgVerInstanceTree;
	}

	public Integer getPackageVersion1up() {
		return packageVersion1up;
	}

	public void setPackageVersion1up(Integer packageVersion1up) {
		this.packageVersion1up = packageVersion1up;
	}

	public PackageTemplate getPackageTemplate() {
		return packageTemplate;
	}

	public void setPackageTemplate(PackageTemplate packageTemplate) {
		this.packageTemplate = packageTemplate;
	}

	public Integer getPackage1up() {
		return package1up;
	}

	public void setPackage1up(Integer package1up) {
		this.package1up = package1up;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public MasterList getMasterList() {
		return masterList;
	}

	public void setMasterList(MasterList masterList) {
		this.masterList = masterList;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getColumnDataType() {
		return columnDataType;
	}

	public void setColumnDataType(String columnDataType) {
		this.columnDataType = columnDataType;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public Boolean getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
	}

	public ExcelViewInfo getParentColumn() {
		return parentColumn;
	}

	public void setParentColumn(ExcelViewInfo parentColumn) {
		this.parentColumn = parentColumn;
	}

	public Integer getMasterListVal1Up() {
		return masterListVal1Up;
	}

	public void setMasterListVal1Up(Integer masterListVal1Up) {
		this.masterListVal1Up = masterListVal1Up;
	}

	public String getDependsOn() {
		return dependsOn;
	}

	public void setDependsOn(String dependsOn) {
		this.dependsOn = dependsOn;
	}

	public Boolean getoIsOverrideverride() {
		return oIsOverrideverride;
	}

	public void setoIsOverrideverride(Boolean oIsOverrideverride) {
		this.oIsOverrideverride = oIsOverrideverride;
	}

}
