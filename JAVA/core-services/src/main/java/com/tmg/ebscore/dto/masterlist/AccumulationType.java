package com.tmg.ebscore.dto.masterlist;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccumulationType", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "AccumulationType", namespace = "http://www.tmg.com/coreservices/data")
public class AccumulationType {

	@XmlElement(name = "ID")
	private Integer id;

	@XmlElement(name = "Name")
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

}
