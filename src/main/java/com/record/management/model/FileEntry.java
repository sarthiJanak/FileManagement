package com.record.management.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "file_entry")
public class FileEntry {

	@Id
	@Column(name = "fileid")
	@SequenceGenerator(name = "file_entry_sequence", sequenceName = "file_entry_sequence", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "file_entry_sequence")
	private int fileId;

	@Column(name = "file_name")
	private String fileName;

	@Column(name = "file_status")
	private String fileStatus;

	@Column(name = "file_path")
	private String filePath;

	@ManyToOne(cascade = { CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinColumn(referencedColumnName = "userId", nullable = true)
	private UserMaster userMaster;

	@Column(name = "created_Date")
	@CreationTimestamp
	private Date createdDate;

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

	public String getFileStatus() {
		return fileStatus;
	}

	public void setFileStatus(String fileStatus) {
		this.fileStatus = fileStatus;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public UserMaster getUserMaster() {
		return userMaster;
	}

	public void setUserMaster(UserMaster userMaster) {
		this.userMaster = userMaster;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public FileEntry(int fileId, String fileName, String fileStatus, String filePath, UserMaster userMaster,
			Date createdDate) {
		this.fileId = fileId;
		this.fileName = fileName;
		this.fileStatus = fileStatus;
		this.filePath = filePath;
		this.userMaster = userMaster;
		this.createdDate = createdDate;
	}

	public FileEntry() {

	}
}
