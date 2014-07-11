package com.wide.stringer.generator.exceptions;

import javax.xml.ws.WebFault;

@WebFault(name = "ServiceFault", targetNamespace = "http://www.tmg.com/service-generator/")
public class ServiceFault extends Exception {

	private static final long serialVersionUID = 893148131254881340L;

	public ServiceFault() {
		super();

	}

	public ServiceFault(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public ServiceFault(String message, Throwable cause) {
		super(message, cause);

	}

	public ServiceFault(String message) {
		super(message);

	}

	public ServiceFault(Throwable cause) {
		super(cause);

	}

}
