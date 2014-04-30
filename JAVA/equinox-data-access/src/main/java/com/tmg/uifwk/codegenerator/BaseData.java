package com.tmg.uifwk.codegenerator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BaseData", namespace = "http://equinox.tmg.com/services/base")
@XmlRootElement(name = "BaseData", namespace = "http://equinox.tmg.com/services/base")
public class BaseData {
	
	@XmlElement(name = "InstanceID")
	private Long instanceID;

	public Long getInstanceID() {
		return instanceID;
	}

	public void setInstanceID(Long instanceID) {
		this.instanceID = instanceID;
	}

}
