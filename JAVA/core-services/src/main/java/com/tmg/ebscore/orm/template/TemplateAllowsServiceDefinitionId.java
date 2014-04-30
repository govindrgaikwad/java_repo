package com.tmg.ebscore.orm.template;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tmg.ebscore.orm.masterlist.MasterListServiceDefinition;

@Embeddable
public class TemplateAllowsServiceDefinitionId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PackageTemplate1up")
	private PackageTemplate packageTemplate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MasterListServiceDefinition1up")
	private MasterListServiceDefinition masterListServiceDefinition;

	public PackageTemplate getPackageTemplate() {
		return packageTemplate;
	}

	public void setPackageTemplate(PackageTemplate packageTemplate) {
		this.packageTemplate = packageTemplate;
	}

	public MasterListServiceDefinition getMasterListServiceDefinition() {
		return masterListServiceDefinition;
	}

	public void setMasterListServiceDefinition(
			MasterListServiceDefinition masterListServiceDefinition) {
		this.masterListServiceDefinition = masterListServiceDefinition;
	}

	public int hashCode() {
		return (int) this.packageTemplate.hashCode()
				+ this.masterListServiceDefinition.hashCode();
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
		TemplateAllowsServiceDefinitionId Id = (TemplateAllowsServiceDefinitionId) obj;
		return Id.packageTemplate.equals(this.packageTemplate)
				&& Id.masterListServiceDefinition
						.equals(this.masterListServiceDefinition);
	}

}
