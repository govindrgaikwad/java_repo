package com.empresa.marco.data;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class EmbaddableData {

	private Integer embaddableId;

	private Integer objectId;

	private String name;

	private String camelCaseName;

	private Integer projectId;

	private Integer projectVersionId;

	private String createdBy;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd,HH:00", timezone = "CET")
	private Date createdDate;

	private String updatedBy;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd,HH:00", timezone = "CET")
	private Date updatedDate;

	public void setEmbaddableId(Integer embaddableId) {
		this.embaddableId = embaddableId;
	}

	public Integer getEmbaddableId() {
		return this.embaddableId;
	}

	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}

	public Integer getObjectId() {
		return this.objectId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setCamelCaseName(String camelCaseName) {
		this.camelCaseName = camelCaseName;
	}

	public String getCamelCaseName() {
		return this.camelCaseName;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getProjectId() {
		return this.projectId;
	}

	public void setProjectVersionId(Integer projectVersionId) {
		this.projectVersionId = projectVersionId;
	}

	public Integer getProjectVersionId() {
		return this.projectVersionId;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

}