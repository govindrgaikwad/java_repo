package com.wide.stringer.generator.exceptions;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceOperationResult", namespace = "http://www.tmg.com/service-generator/data")
@XmlRootElement(name = "ServiceOperationResult", namespace = "http://www.tmg.com/service-generator/data")
public class ServiceOperationResult {

	@XmlElement(name = "OperationResult", namespace = "http://www.tmg.com/service-generator/data")
	private OperationResult operationResult = OperationResult.SUCCESS;

	@XmlElementWrapper(name = "Messages", namespace = "http://www.tmg.com/service-generator/data")
	@XmlElement(name = "Message", namespace = "http://www.tmg.com/service-generator/data")
	private List<String> messages = new ArrayList<String>();

	public OperationResult getOperationResult() {
		return operationResult;
	}

	public void setOperationResult(OperationResult operationResult) {
		this.operationResult = operationResult;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public void addMessage(String message) {
		this.messages.add(message);
	}

}