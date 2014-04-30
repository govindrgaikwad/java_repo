package com.tmg.ebscore.orm.template;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tmg.ebscore.orm.masterlist.MasterListMessageVal;

@Embeddable
public class TemplateAllowsMessageValId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PackageTemplate1up")
	private PackageTemplate packageTemplate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MasterListMessageVal1up")
	private MasterListMessageVal masterListMessageVal;

	public PackageTemplate getPackageTemplate() {
		return packageTemplate;
	}

	public void setPackageTemplate(PackageTemplate packageTemplate) {
		this.packageTemplate = packageTemplate;
	}

	public MasterListMessageVal getMasterListMessageVal() {
		return masterListMessageVal;
	}

	public void setMasterListMessageVal(
			MasterListMessageVal masterListMessageVal) {
		this.masterListMessageVal = masterListMessageVal;
	}
	
	public int hashCode() {
		return (int) this.packageTemplate.hashCode() + this.masterListMessageVal.hashCode();
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof TemplateAllowsMessageValId)) {
			return false;
		}
		TemplateAllowsMessageValId Id = (TemplateAllowsMessageValId) obj;
		return Id.packageTemplate.equals(this.packageTemplate)
				&& Id.masterListMessageVal.equals(this.masterListMessageVal);
	}

}
