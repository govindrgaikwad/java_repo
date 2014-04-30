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

@Entity
@Table(schema = "Pkg", name = "PkgConfigOptionFamily")
public class PkgConfigOptionFamily extends CoreCommonAttributes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PkgConfigOptionFamily1up")
	private Integer pkgConfigOptionFamily1up;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BenefitPackage1up")
	private BenefitPackage benefitPackage;

	@Column(name = "ConfigOptionFamilyName")
	private String configOptionFamilyName;

	@OneToMany(mappedBy = "pkgConfigOptionFamily", fetch = FetchType.LAZY)
	private Set<PkgConfigOption> configOptionFamily = new HashSet<PkgConfigOption>();

	public Set<PkgConfigOption> getConfigOptionFamily() {
		return configOptionFamily;
	}

	public void setConfigOptionFamily(Set<PkgConfigOption> configOptionFamily) {
		this.configOptionFamily = configOptionFamily;
	}

	public Integer getPkgConfigOptionFamily1up() {
		return pkgConfigOptionFamily1up;
	}

	public void setPkgConfigOptionFamily1up(Integer pkgConfigOptionFamily1up) {
		this.pkgConfigOptionFamily1up = pkgConfigOptionFamily1up;
	}

	public BenefitPackage getBenefitPackage() {
		return benefitPackage;
	}

	public void setBenefitPackage(BenefitPackage benefitPackage) {
		this.benefitPackage = benefitPackage;
	}

	public String getConfigOptionFamilyName() {
		return configOptionFamilyName;
	}

	public void setConfigOptionFamilyName(String configOptionFamilyName) {
		this.configOptionFamilyName = configOptionFamilyName;
	}

}
