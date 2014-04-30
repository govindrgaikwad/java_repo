package com.tmg.ebscore.orm.ebspackage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tmg.ebscore.orm.CoreCommonAttributes;
import com.tmg.ebscore.orm.packageversion.PkgVerBenefitOption;

@Entity
@Table(schema = "Pkg", name = "PkgConfigOptionBenefitOptionXref")
public class PkgConfigOptionBenefitOptionXref extends CoreCommonAttributes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PkgConfigOptionBenefitOptionXref1up")
	private Integer pkgConfigOptionBenefitOptionXref1up;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PkgConfigOption1up")
	private PkgConfigOption pkgConfigOption;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PkgVerBenefitOption1up")
	private PkgVerBenefitOption pkgVerBenefitOption;

	public Integer getPkgConfigOptionBenefitOptionXref1up() {
		return pkgConfigOptionBenefitOptionXref1up;
	}

	public void setPkgConfigOptionBenefitOptionXref1up(
			Integer pkgConfigOptionBenefitOptionXref1up) {
		this.pkgConfigOptionBenefitOptionXref1up = pkgConfigOptionBenefitOptionXref1up;
	}

	public PkgConfigOption getPkgConfigOption() {
		return pkgConfigOption;
	}

	public void setPkgConfigOption(PkgConfigOption pkgConfigOption) {
		this.pkgConfigOption = pkgConfigOption;
	}

	public PkgVerBenefitOption getPkgVerBenefitOption() {
		return pkgVerBenefitOption;
	}

	public void setPkgVerBenefitOption(PkgVerBenefitOption pkgVerBenefitOption) {
		this.pkgVerBenefitOption = pkgVerBenefitOption;
	}

}
