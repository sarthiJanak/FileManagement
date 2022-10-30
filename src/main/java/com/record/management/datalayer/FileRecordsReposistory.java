package com.record.management.datalayer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.record.management.model.FileEntry;
import com.record.management.model.FileRecords;

public interface FileRecordsReposistory extends JpaRepository<FileRecords, Integer> {
	
	public List<FileRecords> findByFileEntry(FileEntry fileEntry);
}
