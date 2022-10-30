package com.record.management.dto;

public class AuthnticateResponse {

	private final String token;

	public String getToken() {
		return token;
	}

	public AuthnticateResponse(String token) {
		this.token = token;
	}

}
