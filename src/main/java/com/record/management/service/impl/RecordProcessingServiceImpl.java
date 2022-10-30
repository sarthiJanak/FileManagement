package com.record.management.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.record.management.datalayer.FileEntryReposistry;
import com.record.management.datalayer.FileRecordsReposistory;
import com.record.management.exceptions.FileNotFoundExceptions;
import com.record.management.model.FileEntry;
import com.record.management.model.FileRecords;
import com.record.management.service.RecordProcessingService;
import com.record.management.utility.FileUploadUtility;

@Service
public class RecordProcessingServiceImpl implements RecordProcessingService {

	@Autowired
	private FileEntryReposistry fileEntryReposistry;

	@Autowired
	private FileRecordsReposistory fileRecordsReposistory;

	@Async("asyncExecutor")
	@Override
	public void processFile(File file, int fileEntryId) throws IOException, InterruptedException {

		FileEntry fileEntry = getFileEntry(fileEntryId);

		Workbook workbook = null;
		String fileExtension = FileUploadUtility.getFileExtension(file);
		if ("XLSX".equalsIgnoreCase(fileExtension)) {
			workbook = new XSSFWorkbook(new FileInputStream(file));
		} else if ("XLS".equalsIgnoreCase(fileExtension)) {
			workbook = new HSSFWorkbook(new FileInputStream(file));
		} else {
			throw new RuntimeException();
		}

		Sheet workSheet = workbook.getSheetAt(0);
		long totalRecords = workSheet.getLastRowNum();
		Iterator<Row> rowIterator = workSheet.iterator();
		int rowCount = 0;
		int cellIndex = 0;
		Map<Integer, String> headers = new TreeMap<>();
		List<JSONObject> records = new ArrayList<>();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			cellIndex = 0;
			JSONObject rowRecords = new JSONObject();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				// First row is header of Sheet.
				if (rowCount == 0) {
					headers.put(cellIndex, cell.getStringCellValue());
				} else {
					if (CellType.BOOLEAN.equals(cell.getCellType())) {
						rowRecords.put(headers.get(cellIndex), cell.getBooleanCellValue());
					} else if (CellType.STRING.equals(cell.getCellType())) {
						rowRecords.put(headers.get(cellIndex), cell.getStringCellValue());
					} else if (CellType.NUMERIC.equals(cell.getCellType())) {

						if (HSSFDateUtil.isCellDateFormatted(cell)) {
							rowRecords.put(headers.get(cellIndex), cell.getDateCellValue());
						} else {
							rowRecords.put(headers.get(cellIndex), cell.getNumericCellValue());
						}

					}
				}
				cellIndex = cellIndex + 1;
			}
			if (rowCount > 0) {
				records.add(rowRecords);
			}
			rowCount = rowCount + 1;
		}

		// Code added for the pushing the data in db
		List<FileRecords> fileRecords = records.stream().map(obj -> {
			FileRecords filerecord = new FileRecords();
			filerecord.setFileEntry(fileEntry);
			filerecord.setFileRecord(obj.toString());
			return filerecord;
		}).collect(Collectors.toList());
		fileRecordsReposistory.saveAll(fileRecords);
	}

	private FileEntry getFileEntry(int fileEntryId) {
		Optional<FileEntry> fileEntryWrapper = fileEntryReposistry.findById(fileEntryId);
		return fileEntryWrapper.orElseThrow(() -> new FileNotFoundExceptions("File with file is " + fileEntryId + " is not present in system."));
	}

}
