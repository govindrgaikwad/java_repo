package com.wide.stringer.generator.dto;

public class EmbaddableData {

	private Integer embaddableId;

	private String name;

	private String camelCaseName;

	public void setEmbaddableId(Integer embaddableId) {
		this.embaddableId = embaddableId;
	}

	public Integer getEmbaddableId() {
		return this.embaddableId;
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

}