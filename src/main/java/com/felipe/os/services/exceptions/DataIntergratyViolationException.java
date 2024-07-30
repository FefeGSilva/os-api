package com.felipe.os.services.exceptions;

public class DataIntergratyViolationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataIntergratyViolationException(String message, Throwable cause) {
		super(message, cause);

	}

	public DataIntergratyViolationException(String message) {
		super(message);

	}

}
