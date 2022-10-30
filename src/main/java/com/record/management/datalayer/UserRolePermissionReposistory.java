package com.record.management.datalayer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.record.management.model.UserMaster;
import com.record.management.model.UserRolePermission;

public interface UserRolePermissionReposistory extends JpaRepository<UserRolePermission, Integer> {

	public Optional<UserRolePermission> findByUserMaster(UserMaster userMaster);
	
}
