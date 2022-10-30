package com.record.management.dto;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecordResponse {

	@JsonProperty("message")
	private String message;

	@JsonProperty("fileId")
	private int fileId;

	@JsonProperty("fileName")
	private String fileName;

	@JsonProperty("uploadedDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", locale = "en_IN")
	private Date uploadDate;

	@JsonProperty("records")
	@JsonRawValue
	List<JSONObject> records;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public List<JSONObject> getRecords() {
		return records;
	}

	public void setRecords(List<JSONObject> records) {
		this.records = records;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public RecordResponse(String message, int fileId) {
		this.message = message;
		this.fileId = fileId;
	}

	public RecordResponse(String message, int fileId, String fileName, Date uploadDate, List<JSONObject> records) {
		this.message = message;
		this.fileId = fileId;
		this.fileName = fileName;
		this.uploadDate = uploadDate;
		this.records = records;
	}

	public RecordResponse() {

	}

}
