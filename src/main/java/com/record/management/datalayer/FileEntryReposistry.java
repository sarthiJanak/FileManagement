package com.record.management.datalayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.record.management.model.FileEntry;

@Repository
public interface FileEntryReposistry extends JpaRepository<FileEntry, Integer>{

}
