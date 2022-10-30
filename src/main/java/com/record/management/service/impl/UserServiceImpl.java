package com.record.management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.record.management.datalayer.RoleMasterReposistory;
import com.record.management.datalayer.UserMasterReposistory;
import com.record.management.datalayer.UserRolePermissionReposistory;
import com.record.management.dto.RoleDto;
import com.record.management.dto.RolePermissionDto;
import com.record.management.dto.UserDto;
import com.record.management.exceptions.BadRequestException;
import com.record.management.exceptions.DuplicateUserException;
import com.record.management.exceptions.DuplicateUserPermissionException;
import com.record.management.exceptions.RoleNotFoundException;
import com.record.management.exceptions.UserNotFoundException;
import com.record.management.model.RoleMaster;
import com.record.management.model.UserMaster;
import com.record.management.model.UserRolePermission;
import com.record.management.service.RoleService;
import com.record.management.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMasterReposistory userMasterReposistory;

	@Autowired
	private RoleMasterReposistory roleMasterReposistory;

	@Autowired
	private UserRolePermissionReposistory userRolePermissionReposistory;
	
	@Autowired
	private RoleService roleService;

	@Override
	public UserDto addUserDetails(UserDto user) {

		if ("".equalsIgnoreCase(user.getFirstName()) || "".equalsIgnoreCase(user.getLastName())
				|| "".equalsIgnoreCase(user.getUserName()) || "".equalsIgnoreCase(user.getPassword())) {
			throw new BadRequestException("Provided user details is not proper.");
		}

		Optional<UserMaster> userWrapper = userMasterReposistory.findByUserName(user.getUserName());
		if (userWrapper.isPresent()) {
			throw new DuplicateUserException("Provided username is already taken. Please provide other username.");
		}

		UserMaster userMaster = new UserMaster(user.getFirstName(), user.getLastName(), user.getUserName(),
				user.getPassword());
		userMaster = userMasterReposistory.save(userMaster);
		user.setUserId(userMaster.getUserId());
		user.setLastName(userMaster.getLastName());
		user.setFirstName(userMaster.getFirstName());
		user.setUserName(null);
		user.setPassword(null);
		return user;
	}

	@Override
	public UserDto addAdminUserDetails(UserDto user) throws Exception {

		if ("".equalsIgnoreCase(user.getFirstName()) || "".equalsIgnoreCase(user.getLastName())
				|| "".equalsIgnoreCase(user.getUserName()) || "".equalsIgnoreCase(user.getPassword())) {
			throw new BadRequestException("Provided user details is not proper.");
		}

		List<UserMaster> users = userMasterReposistory.findAll();
		if (users == null || users.isEmpty()) {
			UserMaster userMaster = new UserMaster(user.getFirstName(), user.getLastName(), user.getUserName(),
					user.getPassword());
			userMaster = userMasterReposistory.save(userMaster);
			user.setUserId(userMaster.getUserId());
			user.setLastName(userMaster.getLastName());
			user.setFirstName(userMaster.getFirstName());
			user.setUserName(userMaster.getUserName());
			user.setPassword(null);
			
			
			RoleDto roleDto = roleService.addRoleDetails(new RoleDto(0, "Admin"));
			roleService.addRolePermissionDetails(new RolePermissionDto(roleDto.getRoleId(), Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE));
			
			UserRolePermission userRolePermission = new UserRolePermission(0, userMaster, roleMasterReposistory.findById(roleDto.getRoleId()).get());
			userRolePermissionReposistory.save(userRolePermission);
			return user;
		} else {
			throw new Exception();
		}
		
	}
	
	@Override
	public boolean addUserRole(UserDto user) {

		Optional<UserMaster> userMasterWrapper = userMasterReposistory.findById(user.getUserId());
		userMasterWrapper
				.orElseThrow(() -> new UserNotFoundException("User is not present with provided user details."));

		Optional<RoleMaster> roleMasterWrapper = roleMasterReposistory.findById(user.getRoleId());
		roleMasterWrapper
				.orElseThrow(() -> new RoleNotFoundException("Role is not present with provided role details."));

		if (userRolePermissionReposistory.findByUserMaster(userMasterWrapper.get()).isPresent()) {
			throw new DuplicateUserPermissionException("Permission already assigne to the user.");
		}

		UserRolePermission permission = new UserRolePermission(0, userMasterWrapper.get(), roleMasterWrapper.get());
		permission = userRolePermissionReposistory.save(permission);
		return permission.getUserRolePermissionId() > 0 ? Boolean.TRUE : Boolean.FALSE;
	}

}
