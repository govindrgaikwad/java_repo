package com.socman.data.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntityAttribute implements Serializable {
	protected static final long serialVersionUID = 1L;

	@Column(name = "CreatedBy")
	private String createdBy;

	@Column(name = "CreatedDt")
	private Date createdDt;

	@Column(name = "UpdatedBy")
	private String updatedBy;

	@Column(name = "UpdatedDt")
	private Date updatedDt;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDt() {
		return updatedDt;
	}

	public void setUpdatedDt(Date updatedDt) {
		this.updatedDt = updatedDt;
	}

}
