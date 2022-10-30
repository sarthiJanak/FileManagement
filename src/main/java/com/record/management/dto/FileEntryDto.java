package com.record.management.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FileEntryDto {

	@JsonProperty("fileId")
	private int fileId;

	@JsonProperty("fileName")
	private String fileName;

	@JsonProperty("uploadedDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", locale = "en_IN")
	private Date uploadDate;

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public FileEntryDto(int fileId, String fileName, Date uploadDate) {
		this.fileId = fileId;
		this.fileName = fileName;
		this.uploadDate = uploadDate;
	}

	public FileEntryDto() {

	}

}
