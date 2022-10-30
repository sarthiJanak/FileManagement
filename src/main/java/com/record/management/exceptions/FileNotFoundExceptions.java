package com.record.management.exceptions;

public class FileNotFoundExceptions extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;

	public FileNotFoundExceptions() {

	}

	public FileNotFoundExceptions(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
