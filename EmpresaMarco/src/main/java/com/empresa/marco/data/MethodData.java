package com.empresa.marco.data;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class MethodData {

	private Integer methodId;

	private Integer objectId;

	private String name;

	private String camelCaseName;

	private String accessSpecifier;

	private String returnType;

	private String methodType;

	private String soapOperationName;

	private String soapAction;

	private String exceptions;

	private Integer projectId;

	private Integer projectVersionId;

	private String createdBy;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd,HH:00", timezone = "CET")
	private Date createdDate;

	private String updatedBy;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd,HH:00", timezone = "CET")
	private Date updatedDate;

	public void setMethodId(Integer methodId) {
		this.methodId = methodId;
	}

	public Integer getMethodId() {
		return this.methodId;
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

	public void setAccessSpecifier(String accessSpecifier) {
		this.accessSpecifier = accessSpecifier;
	}

	public String getAccessSpecifier() {
		return this.accessSpecifier;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public String getReturnType() {
		return this.returnType;
	}

	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}

	public String getMethodType() {
		return this.methodType;
	}

	public void setSoapOperationName(String soapOperationName) {
		this.soapOperationName = soapOperationName;
	}

	public String getSoapOperationName() {
		return this.soapOperationName;
	}

	public void setSoapAction(String soapAction) {
		this.soapAction = soapAction;
	}

	public String getSoapAction() {
		return this.soapAction;
	}

	public void setExceptions(String exceptions) {
		this.exceptions = exceptions;
	}

	public String getExceptions() {
		return this.exceptions;
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