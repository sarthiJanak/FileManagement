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
@Table(name = "file_entry_access_tracking")
public class FileEntryAccessTracking {

	@Id
	@SequenceGenerator(name = "file_entry_access_sequence", sequenceName = "file_entry_access_sequence", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "file_entry_access_sequence")
	@Column(name = "accessId")
	private int accessId;

	@ManyToOne(cascade = { CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinColumn(referencedColumnName = "userId", nullable = true)
	private UserMaster userMaster;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.EAGER)
	@JoinColumn(referencedColumnName = "fileid", nullable = true)
	private FileEntry fileEntry;

	@Column(name = "last_access_date")
	@CreationTimestamp
	private Date lastAccessDate;

	public int getAccessId() {
		return accessId;
	}

	public void setAccessId(int accessId) {
		this.accessId = accessId;
	}

	public UserMaster getUserMaster() {
		return userMaster;
	}

	public void setUserMaster(UserMaster userMaster) {
		this.userMaster = userMaster;
	}

	public FileEntry getFileEntry() {
		return fileEntry;
	}

	public void setFileEntry(FileEntry fileEntry) {
		this.fileEntry = fileEntry;
	}

	public Date getLastAccessDate() {
		return lastAccessDate;
	}

	public void setLastAccessDate(Date lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}

	public FileEntryAccessTracking(UserMaster userMaster, FileEntry fileEntry) {
		this.userMaster = userMaster;
		this.fileEntry = fileEntry;
	}

	public FileEntryAccessTracking() {

	}

}
