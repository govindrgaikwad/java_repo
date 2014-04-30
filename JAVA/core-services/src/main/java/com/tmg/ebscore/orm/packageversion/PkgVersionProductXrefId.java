package com.tmg.ebscore.orm.packageversion;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tmg.ebscore.orm.ebspackage.PkgConfigOption;

@Embeddable
public class PkgVersionProductXrefId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PkgVersion1up")
	private PkgVersion pkgVersion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PkgConfigOption1up")
	private PkgConfigOption pkgConfigOption;

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
	
	public int hashCode() {
		return (int) this.pkgVersion.hashCode() + this.pkgConfigOption.hashCode();
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof PkgVersionProductXrefId)) {
			return false;
		}
		PkgVersionProductXrefId Id = (PkgVersionProductXrefId) obj;
		return Id.pkgVersion.equals(this.pkgVersion)
				&& Id.pkgConfigOption.equals(this.pkgConfigOption);
	}

}
