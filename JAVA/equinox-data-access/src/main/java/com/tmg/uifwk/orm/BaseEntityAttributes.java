package com.tmg.uifwk.orm;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntityAttributes implements Serializable {

	protected static final long serialVersionUID = 1L;

	@Column(name = "AddedBy")
	protected String addedBy;

	@Column(name = "AddedDate")
	protected Timestamp addedDate;

	@Column(name = "UpdatedBy")
	protected String updatedBy;

	@Column(name = "UpdatedDate")
	protected Timestamp updatedDate;

	public String getAddedBy() {
		return this.addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	public Timestamp getAddedDate() {
		return this.addedDate;
	}

	public void setAddedDate(Timestamp addedDate) {
		this.addedDate = addedDate;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

}
