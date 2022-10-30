package com.record.management.exceptions;

public class DuplicateUserException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;

	public DuplicateUserException() {

	}

	public DuplicateUserException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
