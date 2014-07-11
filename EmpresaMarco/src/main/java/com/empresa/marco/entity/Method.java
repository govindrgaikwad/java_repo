package com.empresa.marco.entity;

import java.util.Date;
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
@Table(schema = "Data",name = "Method")
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ProjectId")
	private Project project;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ProjectVersionId")
	private ProjectVersion projectVersion;

	@Column(name = "CreatedBy")
	private String createdBy;

	@Column(name = "CreatedDate")
	private Date createdDate;

	@Column(name = "UpdatedBy")
	private String updatedBy;

	@Column(name = "UpdatedDate")
	private Date updatedDate;

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

	public void setProject(Project project) {
		this.project = project;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProjectVersion(ProjectVersion projectVersion) {
		this.projectVersion = projectVersion;
	}

	public ProjectVersion getProjectVersion() {
		return this.projectVersion;
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

	public void setMethodParameters(Set<MethodParameter> methodParameters) {
		this.methodParameters = methodParameters;
	}

	public Set<MethodParameter> getMethodParameters() {
		return methodParameters;
	}

}