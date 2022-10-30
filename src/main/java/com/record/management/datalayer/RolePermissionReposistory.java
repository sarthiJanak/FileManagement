package com.record.management.datalayer;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.record.management.model.RoleMaster;
import com.record.management.model.RolePermission;

public interface RolePermissionReposistory extends JpaRepository<RolePermission, Integer> {
	
	public Optional<RolePermission> findByRoleMaster(RoleMaster roleMaster);
	public List<RolePermission> findByIsAdmin(boolean isAdmin);

}
