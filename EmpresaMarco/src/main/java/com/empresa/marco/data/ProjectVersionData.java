package com.empresa.marco.data;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ProjectVersionData {

	private Integer projectVersionId;

	private Integer projectId;

	private Double versionNumber;

	private String description;

	private String status;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd,HH:00", timezone = "CET")
	private Date startDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd,HH:00", timezone = "CET")
	private Date releaseDate;

	public void setProjectVersionId(Integer projectVersionId) {
		this.projectVersionId = projectVersionId;
	}

	public Integer getProjectVersionId() {
		return this.projectVersionId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getProjectId() {
		return this.projectId;
	}

	public void setVersionNumber(Double versionNumber) {
		this.versionNumber = versionNumber;
	}

	public Double getVersionNumber() {
		return this.versionNumber;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Date getReleaseDate() {
		return this.releaseDate;
	}

}