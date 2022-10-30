package com.record.management.exceptions;

public class RoleNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	private String message;

	public RoleNotFoundException() {

	}

	public RoleNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
