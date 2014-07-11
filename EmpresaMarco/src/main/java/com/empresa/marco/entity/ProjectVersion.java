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
@Table(schema = "Data",name = "ProjectVersion")
public class ProjectVersion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ProjectVersionId")
	private Integer projectVersionId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ProjectId")
	private Project project;

	@Column(name = "VersionNumber")
	private Double versionNumber;

	@Column(name = "Description")
	private String description;

	@Column(name = "Status")
	private String status;

	@Column(name = "StartDate")
	private Date startDate;

	@Column(name = "ReleaseDate")
	private Date releaseDate;

	@OneToMany(mappedBy = "projectVersion", fetch = FetchType.LAZY)
	private Set<Attribute> attributes = new HashSet<Attribute>();

	@OneToMany(mappedBy = "projectVersion", fetch = FetchType.LAZY)
	private Set<Embaddable> embaddables = new HashSet<Embaddable>();

	@OneToMany(mappedBy = "projectVersion", fetch = FetchType.LAZY)
	private Set<Method> methods = new HashSet<Method>();

	@OneToMany(mappedBy = "projectVersion", fetch = FetchType.LAZY)
	private Set<ObjectDefination> objectDefinations = new HashSet<ObjectDefination>();

	@OneToMany(mappedBy = "projectVersion", fetch = FetchType.LAZY)
	private Set<Relationship> relationships = new HashSet<Relationship>();

	public void setProjectVersionId(Integer projectVersionId) {
		this.projectVersionId = projectVersionId;
	}

	public Integer getProjectVersionId() {
		return this.projectVersionId;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Project getProject() {
		return this.project;
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

	public void setRelationships(Set<Relationship> relationships) {
		this.relationships = relationships;
	}

	public Set<Relationship> getRelationships() {
		return relationships;
	}

}