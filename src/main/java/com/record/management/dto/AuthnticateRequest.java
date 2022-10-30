package com.record.management.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthnticateRequest {

	@JsonProperty("username")
	private String username;

	@JsonProperty("password")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AuthnticateRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public AuthnticateRequest() {

	}

}
