package com.record.management.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GlobalResponse {

	@JsonProperty("message")
	private String message;

	@JsonProperty("error_details")
	private String errorDetails;

	public GlobalResponse() {

	}

	public GlobalResponse(String message, String errorDetails) {
		this.message = message;
		this.errorDetails = errorDetails;
	}

}
