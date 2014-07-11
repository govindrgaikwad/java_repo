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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema = "Data",name = "Project")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ProjectId")
	private Integer projectId;

	@Column(name = "Name")
	private String name;

	@Column(name = "Description")
	private String description;

	@Column(name = "CompanyName")
	private String companyName;

	@Column(name = "Client")
	private String client;

	@Column(name = "Location")
	private String location;

	@Column(name = "Manager")
	private String manager;

	@Column(name = "Leader")
	private String leader;

	@Column(name = "Status")
	private String status;

	@Column(name = "StartDate")
	private Date startDate;

	@Column(name = "ReleaseDate")
	private Date releaseDate;

	@OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
	private Set<Attribute> attributes = new HashSet<Attribute>();

	@OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
	private Set<Embaddable> embaddables = new HashSet<Embaddable>();

	@OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
	private Set<Method> methods = new HashSet<Method>();

	@OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
	private Set<ObjectDefination> objectDefinations = new HashSet<ObjectDefination>();

	@OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
	private Set<ProjectVersion> projectVersions = new HashSet<ProjectVersion>();

	@OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
	private Set<Relationship> relationships = new HashSet<Relationship>();

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getProjectId() {
		return this.projectId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getClient() {
		return this.client;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation() {
		return this.location;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getManager() {
		return this.manager;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	public String getLeader() {
		return this.leader;
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

	public void setAttributes(Set<Attribute> attributes) {
		this.attributes = attributes;
	}

	public Set<Attribute> getAttributes() {
		return attributes;
	}

	public void setEmbaddables(Set<Embaddable> embaddables) {
		this.embaddables = embaddables;
	}

	public Set<Embaddable> getEmbaddables() {
		return embaddables;
	}

	public void setMethods(Set<Method> methods) {
		this.methods = methods;
	}

	public Set<Method> getMethods() {
		return methods;
	}

	public void setObjectDefinations(Set<ObjectDefination> objectDefinations) {
		this.objectDefinations = objectDefinations;
	}

	public Set<ObjectDefination> getObjectDefinations() {
		return objectDefinations;
	}

	public void setProjectVersions(Set<ProjectVersion> projectVersions) {
		this.projectVersions = projectVersions;
	}

	public Set<ProjectVersion> getProjectVersions() {
		return projectVersions;
	}

	public void setRelationships(Set<Relationship> relationships) {
		this.relationships = relationships;
	}

	public Set<Relationship> getRelationships() {
		return relationships;
	}

}