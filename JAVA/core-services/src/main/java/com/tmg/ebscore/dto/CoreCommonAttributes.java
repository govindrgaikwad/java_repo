package com.tmg.ebscore.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CoreCommonAttributes", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "CoreCommonAttributes", namespace = "http://www.tmg.com/coreservices/data")
public class CoreCommonAttributes {

	@XmlElement(name = "CreateBy")
	protected String createBy;

	@XmlElement(name = "UpdateBy")
	protected String updateBy;

	@XmlElement(name = "CreateDt")
	protected Date createDt;

	@XmlElement(name = "UpdateDt")
	protected Date updateDt;

	@XmlElement(name = "eBSInstance1up")
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
