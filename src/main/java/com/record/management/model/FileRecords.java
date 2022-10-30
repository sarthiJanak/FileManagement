package com.record.management.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "file_records")
public class FileRecords {
	
	@Id
	@Column(name = "recordid")
	@SequenceGenerator(name = "record_sequence", sequenceName = "record_sequence", 
			initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "record_sequence")
	private int recordId;
	
	@Column(name = "file_record", length = 10000)
	private String fileRecord;
	
	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(referencedColumnName = "fileId")
	private FileEntry fileEntry;

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public String getFileRecord() {
		return fileRecord;
	}

	public void setFileRecord(String fileRecord) {
		this.fileRecord = fileRecord;
	}

	public FileEntry getFileEntry() {
		return fileEntry;
	}

	public void setFileEntry(FileEntry fileEntry) {
		this.fileEntry = fileEntry;
	}

	public FileRecords(int recordId, String fileRecord, FileEntry fileEntry) {
		this.recordId = recordId;
		this.fileRecord = fileRecord;
		this.fileEntry = fileEntry;
	}
	
	public FileRecords() {
		
	}

}
