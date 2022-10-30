package com.record.management.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.record.management.dto.FileEntryDto;
import com.record.management.dto.RecordResponse;

public interface RecordService {

	public RecordResponse uploadFile(MultipartFile file) throws IOException, InterruptedException;
	
	public List<FileEntryDto> getAllFiles();
	
	public RecordResponse getFileRecords(int fileId);
	
	public List<RecordResponse> getAllFileRecords();
	
	public RecordResponse deleteFile(int fileId);
}
