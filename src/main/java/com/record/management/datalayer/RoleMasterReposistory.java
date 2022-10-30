package com.record.management.datalayer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.record.management.model.RoleMaster;

@Repository
public interface RoleMasterReposistory extends JpaRepository<RoleMaster, Integer> {
	
	public Optional<RoleMaster> findByRoleName(String roleName);

}
