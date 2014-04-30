package com.tmg.ebscore.orm.template;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tmg.ebscore.orm.masterlist.MasterListStringVal;

@Embeddable
public class TemplateAllowsStringValId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PackageTemplate1up")
	private PackageTemplate packageTemplate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MasterListStringVal1up")
	private MasterListStringVal masterListStringVal;

	public PackageTemplate getPackageTemplate() {
		return packageTemplate;
	}

	public void setPackageTemplate(PackageTemplate packageTemplate) {
		this.packageTemplate = packageTemplate;
	}

	public MasterListStringVal getMasterListStringVal() {
		return masterListStringVal;
	}

	public void setMasterListStringVal(MasterListStringVal masterListStringVal) {
		this.masterListStringVal = masterListStringVal;
	}
	
	public int hashCode() {
		return (int) this.packageTemplate.hashCode() + this.masterListStringVal.hashCode();
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof TemplateAllowsStringValId)) {
			return false;
		}
		TemplateAllowsStringValId Id = (TemplateAllowsStringValId) obj;
		return Id.packageTemplate.equals(this.packageTemplate)
				&& Id.masterListStringVal.equals(this.masterListStringVal);
	}

}
