package com.tmg.ebscore.orm.template;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tmg.ebscore.orm.masterlist.MasterListLimitVal;

@Embeddable
public class TemplateAllowsLimitValId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PackageTemplate1up")
	private PackageTemplate packageTemplate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MasterListLimitVal1up")
	private MasterListLimitVal masterListLimitVal;

	public PackageTemplate getPackageTemplate() {
		return packageTemplate;
	}

	public void setPackageTemplate(PackageTemplate packageTemplate) {
		this.packageTemplate = packageTemplate;
	}

	public MasterListLimitVal getMasterListLimitVal() {
		return masterListLimitVal;
	}

	public void setMasterListLimitVal(MasterListLimitVal masterListLimitVal) {
		this.masterListLimitVal = masterListLimitVal;
	}

	public int hashCode() {
		return (int) this.packageTemplate.hashCode()
				+ this.masterListLimitVal.hashCode();
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof TemplateAllowsLimitValId)) {
			return false;
		}
		TemplateAllowsLimitValId Id = (TemplateAllowsLimitValId) obj;
		return Id.masterListLimitVal.equals(this.masterListLimitVal)
				&& Id.packageTemplate.equals(this.packageTemplate);
	}

}
