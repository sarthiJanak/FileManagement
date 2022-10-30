package com.record.management.datalayer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.record.management.model.UserMaster;

@Repository
public interface UserMasterReposistory extends JpaRepository<UserMaster, Integer> {
	
	public Optional<UserMaster> findByUserName(String userName);
	
}
