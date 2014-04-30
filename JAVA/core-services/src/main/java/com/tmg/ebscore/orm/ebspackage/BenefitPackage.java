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
import com.tmg.ebscore.orm.packageversion.PkgVersion;
import com.tmg.ebscore.orm.template.PackageTemplate;

@Entity
@Table(schema = "Pkg", name = "BenefitPackage")
public class BenefitPackage extends CoreCommonAttributes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BenefitPackage1up")
	private Integer benefitPackage1up;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PackageTemplate1up")
	private PackageTemplate packageTemplate;

	@Column(name = "ID")
	private String id;

	@Column(name = "Name")
	private String name;

	@Column(name = "Description")
	private String description;

	@OneToMany(mappedBy = "benefitPackage", fetch = FetchType.LAZY)
	private Set<PkgVersion> pkgBenefitPkg = new HashSet<PkgVersion>();

	@OneToMany(mappedBy = "benefitPackage", fetch = FetchType.LAZY)
	private Set<PkgConfigOptionFamily> benPkgFamily = new HashSet<PkgConfigOptionFamily>();

	@OneToMany(mappedBy = "benefitPackage", fetch = FetchType.LAZY)
	private Set<PkgMasterListForBig3CO> benPkgMasterList = new HashSet<PkgMasterListForBig3CO>();

	public Set<PkgMasterListForBig3CO> getBenPkgMasterList() {
		return benPkgMasterList;
	}

	public void setBenPkgMasterList(Set<PkgMasterListForBig3CO> benPkgMasterList) {
		this.benPkgMasterList = benPkgMasterList;
	}

	public Set<PkgConfigOptionFamily> getBenPkgFamily() {
		return benPkgFamily;
	}

	public void setBenPkgFamily(Set<PkgConfigOptionFamily> benPkgFamily) {
		this.benPkgFamily = benPkgFamily;
	}

	public Set<PkgVersion> getPkgBenefitPkg() {
		return pkgBenefitPkg;
	}

	public void setPkgBenefitPkg(Set<PkgVersion> pkgBenefitPkg) {
		this.pkgBenefitPkg = pkgBenefitPkg;
	}

	public Integer getBenefitPackage1up() {
		return benefitPackage1up;
	}

	public void setBenefitPackage1up(Integer benefitPackage1up) {
		this.benefitPackage1up = benefitPackage1up;
	}

	public PackageTemplate getPackageTemplate() {
		return packageTemplate;
	}

	public void setPackageTemplate(PackageTemplate packageTemplate) {
		this.packageTemplate = packageTemplate;
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

}
