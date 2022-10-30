package com.record.management.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.record.management.dto.FileEntryDto;
import com.record.management.dto.RecordResponse;
import com.record.management.service.RecordService;
import com.record.management.service.RoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("records/api/v1")
@Api(description = "Controller performed the file read and data upload operation based on user role.")
public class RecordManagementController {

	@Autowired
	private RecordService recordService;

	@PostMapping(path = "/file/upload")
	//@PreAuthorize("hasAnyAuthority('Admin')")
	@PreAuthorize("hasAnyAuthority(@roleServiceImpl.getAdminRoles)")
	@ApiOperation(value = "Upload the excel file." , response = RecordResponse.class)
	public ResponseEntity<RecordResponse> uploadRecordFile(@RequestParam("file") MultipartFile file)
			throws IOException, InterruptedException {
		return new ResponseEntity<RecordResponse>(recordService.uploadFile(file), HttpStatus.OK);
	}

	@GetMapping(path = "/file/getAllFiles")
	//@PreAuthorize("hasAnyAuthority('Admin','User')")
	@PreAuthorize("hasAnyAuthority(@roleServiceImpl.getAllRoles)")
	@ApiOperation(value = "Provide the list of files." , response = FileEntryDto.class)
	public ResponseEntity<List<FileEntryDto>> getAllFiles() {
		return new ResponseEntity<List<FileEntryDto>>(recordService.getAllFiles(), HttpStatus.OK);
	}

	@ApiOperation(value = "Provide the list of records present in the file." , response = RecordResponse.class)
	@GetMapping(path = "/file/getFileRecords", produces = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasAnyAuthority('Admin','User')")
	@PreAuthorize("hasAnyAuthority(@roleServiceImpl.getAllRoles)")
	public ResponseEntity<RecordResponse> getFileRecords(@RequestParam(name = "fileId") int fileId) {
		RecordResponse recordResponse = recordService.getFileRecords(fileId);
		return new ResponseEntity<RecordResponse>(recordResponse, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Provide the list of records present in the all file." , response = RecordResponse.class)
	@GetMapping(path = "/file/getAllFileRecords", produces = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasAnyAuthority('Admin','User')")
	@PreAuthorize("hasAnyAuthority(@roleServiceImpl.getAllRoles)")
	public ResponseEntity<List<RecordResponse>> getAllFileRecords() {
		return new ResponseEntity<List<RecordResponse>>(recordService.getAllFileRecords(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Delete the file from the system and remove the records of that file." , response = RecordResponse.class)
	@GetMapping(path = "/file/deleteFile", produces = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasAnyAuthority('Admin')")
	@PreAuthorize("hasAnyAuthority(@roleServiceImpl.getAdminRoles)")
	public ResponseEntity<RecordResponse> deleteFile(@RequestParam(name = "fileId") int fileId) {
		return new ResponseEntity<RecordResponse>(recordService.deleteFile(fileId), HttpStatus.OK);
	}

}
