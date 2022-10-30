package com.record.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.record.management.dto.RoleDto;
import com.record.management.dto.RolePermissionDto;
import com.record.management.dto.UserDto;
import com.record.management.service.RoleService;
import com.record.management.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping(path = "/user/api/v1")
@Api(description = "Operations performed to create user and roles. This all operations done by admin user.")
public class UserManagementController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@ApiOperation(value = "To create the user." , response = UserDto.class)
	@PostMapping(path = "/addUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasAnyAuthority('Admin')")
	@PreAuthorize("hasAnyAuthority(@roleServiceImpl.getAdminRoles)")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
		return new ResponseEntity<UserDto>(userService.addUserDetails(user), HttpStatus.CREATED);
	}

	@ApiOperation(value = "To create the user role." , response = RoleDto.class)
	@PostMapping(path = "/addRole", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasAnyAuthority('Admin')")
	@PreAuthorize("hasAnyAuthority(@roleServiceImpl.getAdminRoles)")
	public ResponseEntity<RoleDto> createRole(@RequestBody RoleDto role) {
		return new ResponseEntity<>(roleService.addRoleDetails(role), HttpStatus.CREATED);
	}

	@ApiOperation(value = "To create the role permission." , response = RolePermissionDto.class)
	@PostMapping(path = "/addRolePermission", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasAnyAuthority('Admin')")
	@PreAuthorize("hasAnyAuthority(@roleServiceImpl.getAdminRoles)")
	public ResponseEntity<RolePermissionDto> createRolePermission(@RequestBody RolePermissionDto rolePermission) {
		return new ResponseEntity<RolePermissionDto>(roleService.addRolePermissionDetails(rolePermission),
				HttpStatus.CREATED);
	}

	@ApiOperation(value = "To create the role permission." , response = RolePermissionDto.class)
	@PostMapping(path = "/addUserRole", consumes = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasAnyAuthority('Admin')")
	@PreAuthorize("hasAnyAuthority(@roleServiceImpl.getAdminRoles)")
	public ResponseEntity<String> assigneUserRole(@RequestBody UserDto user) {
		String response = userService.addUserRole(user) ? "Role assigned to user successfully"
				: "Role is not assigne to user. Please try after sometime.";
		return new ResponseEntity<String>(response, HttpStatus.CREATED);
	}
}
