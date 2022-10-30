package com.record.management.datalayer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.record.management.model.FileEntryAccessTracking;
import com.record.management.model.FileEntry;

public interface FileEntryAccessTrackingReposistory extends JpaRepository<FileEntryAccessTracking, Integer> {
	
	public List<FileEntryAccessTracking> findByFileEntry(FileEntry fileEntry);
	
}
