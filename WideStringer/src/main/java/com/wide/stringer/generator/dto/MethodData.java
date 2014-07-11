package com.wide.stringer.generator.dto;

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

}