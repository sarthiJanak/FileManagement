package com.record.management.exceptions;

public class DuplicateRoleException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String message;

	public DuplicateRoleException() {

	}

	public DuplicateRoleException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
