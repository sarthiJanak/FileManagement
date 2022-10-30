package com.record.management.exceptions;

public class DuplicateUserPermissionException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String message;

	public DuplicateUserPermissionException() {

	}

	public DuplicateUserPermissionException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
