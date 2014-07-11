package com.wide.stringer.generator.exceptions;

public class DataAccessException extends Exception {

	private static final long serialVersionUID = -1837676575418053833L;

	public DataAccessException() {
		super();
	}

	public DataAccessException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataAccessException(String message) {
		super(message);
	}

	public DataAccessException(Throwable cause) {
		super(cause);
	}
}