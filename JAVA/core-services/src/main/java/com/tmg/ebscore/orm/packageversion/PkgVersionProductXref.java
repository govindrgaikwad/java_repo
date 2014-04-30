package com.tmg.ebscore.orm.packageversion;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.tmg.ebscore.orm.CoreCommonAttributes;

@Entity
@Table(schema = "Pkg", name = "PkgVersionProductXref")
public class PkgVersionProductXref extends CoreCommonAttributes {

	@EmbeddedId
	private PkgVersionProductXrefId pkgVersionProductXrefId;

	@Column(name = "IsAvailableFlag")
	private Boolean availableFlag;

	public PkgVersionProductXrefId getPkgVersionProductXrefId() {
		return pkgVersionProductXrefId;
	}

	public void setPkgVersionProductXrefId(
			PkgVersionProductXrefId pkgVersionProductXrefId) {
		this.pkgVersionProductXrefId = pkgVersionProductXrefId;
	}

	public Boolean getAvailableFlag() {
		return availableFlag;
	}

	public void setAvailableFlag(Boolean availableFlag) {
		this.availableFlag = availableFlag;
	}

}
