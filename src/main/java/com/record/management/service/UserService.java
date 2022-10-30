package com.record.management.service;

import com.record.management.dto.UserDto;

public interface UserService {
	
	public UserDto addUserDetails(UserDto user);
	
	public boolean addUserRole(UserDto user);
	
	public UserDto addAdminUserDetails(UserDto user) throws Exception;

}
