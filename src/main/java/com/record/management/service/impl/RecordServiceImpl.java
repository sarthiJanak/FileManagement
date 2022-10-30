package com.record.management.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.record.management.datalayer.FileEntryAccessTrackingReposistory;
import com.record.management.datalayer.FileEntryReposistry;
import com.record.management.datalayer.FileRecordsReposistory;
import com.record.management.dto.FileEntryDto;
import com.record.management.dto.RecordResponse;
import com.record.management.exceptions.BadRequestException;
import com.record.management.exceptions.IllegalFileFormatException;
import com.record.management.model.FileEntryAccessTracking;
import com.record.management.model.FileEntry;
import com.record.management.model.FileRecords;
import com.record.management.model.UserMaster;
import com.record.management.model.impl.CustomUserDetailImpl;
import com.record.management.service.RecordProcessingService;
import com.record.management.service.RecordService;
import com.record.management.utility.FileUploadUtility;

@Service
public class RecordServiceImpl implements RecordService {

	@Value("${record.file.store.path}")
	private String dirPath;

	@Autowired
	private FileEntryReposistry fileEntryReposistry;

	@Autowired
	private RecordProcessingService recordProcessingService;

	@Autowired
	private FileRecordsReposistory fileRecordsReposistory;

	@Autowired
	private FileEntryAccessTrackingReposistory accessTrackingReposistory;

	@Override
	public RecordResponse uploadFile(MultipartFile file) throws IOException, InterruptedException {
		try {
			
			if (file == null) {
				throw new BadRequestException("File is not present in request.");
			}
			
			// Uploaded file is not a valid excel file.
			if (!FileUploadUtility.validateFileFormat(file)) {
				throw new IllegalFileFormatException("Provided file is not valid excel file.");
			}

			// Creating the file entry in DB.
			UserMaster userMaster = getLoggedInUserDetails();
			FileEntry fileEntry = new FileEntry();
			fileEntry.setFileName(file.getOriginalFilename());
			fileEntry.setUserMaster(userMaster);
			fileEntry = fileEntryReposistry.save(fileEntry);

			// Coping the file to other location.
			File updatedFile = FileUploadUtility.copyFileToLocation(file,
					dirPath + fileEntry.getFileId() + "_" + file.getOriginalFilename());

			// Store the records to database
			System.out.println("--------> File processing start <--------");
			recordProcessingService.processFile(updatedFile, fileEntry.getFileId());
			System.out.println("-------> After thread start <-----------");
			RecordResponse recordUploadResponse = new RecordResponse(
					"File uploaded successfully. System is processing file. You can check the file status by using the below mentioned URL.",
					fileEntry.getFileId());

			return recordUploadResponse;
		} catch (Exception e) {
			throw new IOException("File is not stored in file system. Please try after sometime.");
		}
	}

	@Override
	public List<FileEntryDto> getAllFiles() {
		return fileEntryReposistry.findAll().stream().map(fileEntry -> new FileEntryDto(fileEntry.getFileId(),
				fileEntry.getFileName(), fileEntry.getCreatedDate())).collect(Collectors.toList());
	}

	@Override
	public RecordResponse getFileRecords(int fileId) {
		Optional<FileEntry> fileEntryWrapper = fileEntryReposistry.findById(fileId);
		fileEntryWrapper.orElseThrow(() -> new RuntimeException("File not present with id : " + fileId));
		return generateRecordResponse(fileEntryWrapper.get(), getLoggedInUserDetails());
	}

	@Override
	public List<RecordResponse> getAllFileRecords() {
		List<FileEntry> files = fileEntryReposistry.findAll();
		UserMaster userMaster = getLoggedInUserDetails();
		return files.stream().map(fileEntry -> generateRecordResponse(fileEntry, userMaster))
				.collect(Collectors.toList());
	}

	@Override
	public RecordResponse deleteFile(int fileId) {
		Optional<FileEntry> fileEntryWrapper = fileEntryReposistry.findById(fileId);
		fileEntryWrapper.orElseThrow(() -> new RuntimeException("File not present with id : " + fileId));
		FileEntry fileEntry = fileEntryWrapper.get();
		File file = new File(dirPath + fileEntry.getFileId() + "_" + fileEntry.getFileName());
		if (file.delete()) {
			List<FileRecords> records = fileRecordsReposistory.findByFileEntry(fileEntry);
			List<FileEntryAccessTracking> accessTracking = accessTrackingReposistory.findByFileEntry(fileEntry);

			fileRecordsReposistory.deleteAll(records);
			accessTrackingReposistory.deleteAll(accessTracking);
			fileEntryReposistry.delete(fileEntry);

			return new RecordResponse("File deleted successfull.", 0);
		} else {
			return new RecordResponse("File not deleted from system. Please try after sometime.", 0);
		}
	}

	private RecordResponse generateRecordResponse(FileEntry fileEntry, UserMaster userMaster) {
		List<FileRecords> fileRecords = fileRecordsReposistory.findByFileEntry(fileEntry);
		RecordResponse recordResponse = null;
		if (fileRecords != null && !fileRecords.isEmpty()) {
			List<JSONObject> records = fileRecords.stream()
					.map(fileRecord -> new JSONObject(fileRecord.getFileRecord().toString()))
					.collect(Collectors.toList());
			recordResponse = new RecordResponse("File records fetched successfully.", fileEntry.getFileId(),
					fileEntry.getFileName(), fileEntry.getCreatedDate(), records);
		} else {
			recordResponse = new RecordResponse("File does not contains any records.", fileEntry.getFileId(),
					fileEntry.getFileName(), fileEntry.getCreatedDate(), new ArrayList<>());
		}

		logFileAccessDetails(userMaster, fileEntry);
		return recordResponse;
	}

	private UserMaster getLoggedInUserDetails() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetailImpl customUserDetail = (CustomUserDetailImpl) authentication.getPrincipal();
		UserMaster userMaster = customUserDetail.getUser();
		return userMaster;
	}

	private void logFileAccessDetails(UserMaster user, FileEntry fileEntry) {
		accessTrackingReposistory.save(new FileEntryAccessTracking(user, fileEntry));
	}
}
