package com.wide.stringer.generator.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema="Data",name = "Method")
public class Method {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MethodId")
	private Integer methodId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ObjectId")
	private ObjectDefination objectDefination;

	@Column(name = "Name")
	private String name;

	@Column(name = "CamelCaseName")
	private String camelCaseName;

	@Column(name = "AccessSpecifier")
	private String accessSpecifier;

	@Column(name = "ReturnType")
	private String returnType;

	@Column(name = "MethodType")
	private String methodType;

	@Column(name = "SoapOperationName")
	private String soapOperationName;

	@Column(name = "SoapAction")
	private String soapAction;

	@Column(name = "Exceptions")
	private String exceptions;

	@OneToMany(mappedBy = "method", fetch = FetchType.LAZY)
	private Set<MethodParameter> methodParameters = new HashSet<MethodParameter>();

	public void setMethodId(Integer methodId) {
		this.methodId = methodId;
	}

	public Integer getMethodId() {
		return this.methodId;
	}

	public void setObjectDefination(ObjectDefination objectDefination) {
		this.objectDefination = objectDefination;
	}

	public ObjectDefination getObjectDefination() {
		return this.objectDefination;
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

	public void setMethodParameters(Set<MethodParameter> methodParameters) {
		this.methodParameters = methodParameters;
	}

	public Set<MethodParameter> getMethodParameters() {
		return methodParameters;
	}

}