package com.record.management.exceptions;

public class DuplicateRolePermissionException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String message;

	public DuplicateRolePermissionException() {

	}

	public DuplicateRolePermissionException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
