package com.record.management.exceptions;

public class IllegalFileFormatException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String message;

	public IllegalFileFormatException() {

	}

	public IllegalFileFormatException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
