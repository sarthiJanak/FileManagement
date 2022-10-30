package com.record.management.service;

import java.util.List;

import com.record.management.dto.RoleDto;
import com.record.management.dto.RolePermissionDto;

public interface RoleService {
	
	public RoleDto addRoleDetails(RoleDto role);
	
	public RolePermissionDto addRolePermissionDetails(RolePermissionDto rolePermission);
	
	public List<String> getAdminRoles();
	
	public List<String> getAllRoles();
}
