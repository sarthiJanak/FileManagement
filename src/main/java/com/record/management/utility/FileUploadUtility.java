package com.record.management.utility;

import static com.record.management.constant.GlobalConstant.FILE_TYPE_EXCEL;
import static com.record.management.constant.GlobalConstant.FILE_TYPE_OPEN_EXCEL;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtility {

	private FileUploadUtility() {

	}

	public static boolean validateFileFormat(MultipartFile file) {
		return (FILE_TYPE_OPEN_EXCEL.equals(file.getContentType()) || FILE_TYPE_EXCEL.equals(file.getContentType()))
				? Boolean.TRUE
				: Boolean.FALSE;
	}

	public static File copyFileToLocation(MultipartFile file, String directoryPath)
			throws IOException, IllegalStateException {
		File newFile = new File(directoryPath);
		newFile.createNewFile();
		file.transferTo(newFile);
		return newFile;
	}
	
	public static String getFileExtension(File file) {
		return FilenameUtils.getExtension(file.getPath()); 
	}
}
