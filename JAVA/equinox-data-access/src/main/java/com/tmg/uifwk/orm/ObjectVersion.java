package com.tmg.uifwk.orm;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "DM", name = "ObjectVersion")
public class ObjectVersion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "VersionID")
	private Integer versionID;

	@Column(name = "VersionName")
	private String versionName;

	@Column(name = "EffectiveFrom")
	private Date effectiveFrom;

	@Column(name = "EffectiveTo")
	private Date effectiveTo;

	public Integer getVersionID() {
		return versionID;
	}

	public void setVersionID(Integer versionID) {
		this.versionID = versionID;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public Date getEffectiveFrom() {
		return effectiveFrom;
	}

	public void setEffectiveFrom(Date effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public Date getEffectiveTo() {
		return effectiveTo;
	}

	public void setEffectiveTo(Date effectiveTo) {
		this.effectiveTo = effectiveTo;
	}

}
