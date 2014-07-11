package com.wide.stringer.generator.exceptions;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "OperationResult", namespace = "http://www.tmg.com/service-generator/data")
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
