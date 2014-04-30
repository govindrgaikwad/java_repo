package com.tmg.ebscore.orm;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class CoreCommonAttributes {

	@Column(name = "CreateBy")
	protected String createBy;

	@Column(name = "UpdateBy")
	protected String updateBy;

	@Column(name = "CreateDt")
	protected Date createDt;

	@Column(name = "UpdateDt")
	protected Date updateDt;

	@Column(name = "eBSInstance1up")
	protected Integer eBSInstance1up;

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public Date getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

	public int geteBSInstance1up() {
		return eBSInstance1up;
	}

	public void seteBSInstance1up(Integer eBSInstance1up) {
		this.eBSInstance1up = eBSInstance1up;
	}

}
