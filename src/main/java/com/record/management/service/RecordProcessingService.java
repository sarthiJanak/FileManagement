package com.record.management.service;

import java.io.File;
import java.io.IOException;

public interface RecordProcessingService {
	public void processFile(File file, int fileEntryId) throws IOException, InterruptedException;
}
