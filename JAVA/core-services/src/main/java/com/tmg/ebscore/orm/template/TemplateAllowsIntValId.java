package com.tmg.ebscore.orm.template;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tmg.ebscore.orm.masterlist.MasterListIntVal;

@Embeddable
public class TemplateAllowsIntValId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PackageTemplate1up")
	private PackageTemplate packageTemplate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MasterListIntVal1up")
	private MasterListIntVal masterListIntVal;

	public PackageTemplate getPackageTemplate() {
		return packageTemplate;
	}

	public void setPackageTemplate(PackageTemplate packageTemplate) {
		this.packageTemplate = packageTemplate;
	}

	public MasterListIntVal getMasterListIntVal() {
		return masterListIntVal;
	}

	public void setMasterListIntVal(MasterListIntVal masterListIntVal) {
		this.masterListIntVal = masterListIntVal;
	}
	
	public int hashCode() {
		return (int) this.packageTemplate.hashCode() + this.masterListIntVal.hashCode();
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof TemplateAllowsIntValId)) {
			return false;
		}
		TemplateAllowsIntValId Id = (TemplateAllowsIntValId) obj;
		return Id.packageTemplate.equals(this.packageTemplate)
				&& Id.masterListIntVal.equals(this.masterListIntVal);
	}

}
