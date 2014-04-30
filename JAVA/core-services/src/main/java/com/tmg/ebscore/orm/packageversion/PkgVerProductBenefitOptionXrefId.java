package com.tmg.ebscore.orm.packageversion;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tmg.ebscore.orm.ebspackage.PkgConfigOption;

@Embeddable
public class PkgVerProductBenefitOptionXrefId implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PkgVersion1up")
	private PkgVersion pkgVersion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PkgConfigOption1up")
	private PkgConfigOption pkgConfigOption;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PkgVerBenefitOption1up")
	private PkgVerBenefitOption pkgVerBenefitOption;

	public PkgVersion getPkgVersion() {
		return pkgVersion;
	}

	public void setPkgVersion(PkgVersion pkgVersion) {
		this.pkgVersion = pkgVersion;
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
	
	public int hashCode() {
		return (int) this.pkgVersion.hashCode() + this.pkgConfigOption.hashCode()
				+ this.pkgVerBenefitOption.hashCode();
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof PkgVerProductBenefitOptionXrefId)) {
			return false;
		}
		PkgVerProductBenefitOptionXrefId Id = (PkgVerProductBenefitOptionXrefId) obj;
		return Id.pkgVersion.equals(this.pkgVersion)
				&& Id.pkgConfigOption.equals(this.pkgConfigOption)
				&& Id.pkgVerBenefitOption.equals(this.pkgVerBenefitOption);
	}

}
