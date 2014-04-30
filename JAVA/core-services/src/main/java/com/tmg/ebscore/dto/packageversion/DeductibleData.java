package com.tmg.ebscore.dto.packageversion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tmg.ebscore.dto.masterlist.MasterListIntValData;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeductibleData", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "DeductibleData", namespace = "http://www.tmg.com/coreservices/data")
public class DeductibleData {

	@XmlElement(name = "DeductibleName")
	private String name;

	@XmlElement(name = "DeductibleAmount")
	private MasterListIntValData value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MasterListIntValData getValue() {
		return value;
	}

	public void setValue(MasterListIntValData value) {
		this.value = value;
	}

}
