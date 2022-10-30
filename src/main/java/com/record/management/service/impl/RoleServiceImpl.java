package com.record.management.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.record.management.datalayer.RoleMasterReposistory;
import com.record.management.datalayer.RolePermissionReposistory;
import com.record.management.dto.RoleDto;
import com.record.management.dto.RolePermissionDto;
import com.record.management.exceptions.BadRequestException;
import com.record.management.exceptions.DuplicateRoleException;
import com.record.management.exceptions.DuplicateRolePermissionException;
import com.record.management.exceptions.RoleNotFoundException;
import com.record.management.model.RoleMaster;
import com.record.management.model.RolePermission;
import com.record.management.service.RoleService;

@Service("roleServiceImpl")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMasterReposistory roleMasterReposistory;

	@Autowired
	private RolePermissionReposistory rolePermissionReposistory;

	/**
	 * Method is used to create the role.
	 */
	@Override
	public RoleDto addRoleDetails(RoleDto role) {

		if ("".equalsIgnoreCase(role.getRoleName())) {
			throw new BadRequestException("Role name is not provided.");
		}
		
		// Role name is already present in DB. So same name can not be stored again.
		Optional<RoleMaster> roleMasterWrapper = roleMasterReposistory.findByRoleName(role.getRoleName());
		if (roleMasterWrapper.isPresent()) {
			throw new DuplicateRoleException("Role already present with role name " + role.getRoleName());
		}

		RoleMaster roleMaster = new RoleMaster(role.getRoleName());
		roleMaster = roleMasterReposistory.save(roleMaster);
		BeanUtils.copyProperties(roleMaster, role);
		return role;
	}

	/**
	 * This method is used to add the permission for each role. based on the
	 * permission use can add update and delete the records.
	 */
	@Override
	public RolePermissionDto addRolePermissionDetails(RolePermissionDto rolePermission) {

		// Permissions are present with role id.
		Optional<RoleMaster> roleMasterWrapper = roleMasterReposistory.findById(rolePermission.getRoleId());
		if (roleMasterWrapper.isEmpty()) {
			throw new RoleNotFoundException("Role is not present with provided role details.");
		}
		
		Optional<RolePermission> rolePermissionWrapper = rolePermissionReposistory
				.findByRoleMaster(roleMasterWrapper.get());
		if (rolePermissionWrapper.isPresent()) {
			throw new DuplicateRolePermissionException("Role permission already present in the system.");
		}

		RolePermission permission = new RolePermission();
		BeanUtils.copyProperties(rolePermission, permission);
		permission.setRoleMaster(roleMasterWrapper.get());
		permission = rolePermissionReposistory.save(permission);
		BeanUtils.copyProperties(permission, rolePermission);
		return rolePermission;
	}
	
	@Override
	public List<String> getAdminRoles() {
		return rolePermissionReposistory.findByIsAdmin(Boolean.TRUE).stream()
			.map(permission -> permission.getRoleMaster().getRoleName()).collect(Collectors.toList());
	}
	
	@Override
	public List<String> getAllRoles() {
		return roleMasterReposistory.findAll().stream().map(roles -> roles.getRoleName()).collect(Collectors.toList());
	}

}
