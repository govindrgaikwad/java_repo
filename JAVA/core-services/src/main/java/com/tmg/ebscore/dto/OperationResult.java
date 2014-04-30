package com.tmg.ebscore.dto;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "OperationResult", namespace = "http://www.tmg.com/coreservices/data")
@XmlEnum
public enum OperationResult {

	SUCCESS, FAILURE, WARNING;

	public String value() {
		return name();
	}

	public static OperationResult fromValue(String v) {
		return valueOf(v);
	}

}
