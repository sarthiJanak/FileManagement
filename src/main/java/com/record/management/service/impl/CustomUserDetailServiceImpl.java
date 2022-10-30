package com.record.management.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.record.management.datalayer.UserMasterReposistory;
import com.record.management.datalayer.UserRolePermissionReposistory;
import com.record.management.model.UserMaster;
import com.record.management.model.UserRolePermission;
import com.record.management.model.impl.CustomUserDetailImpl;

@Service
public class CustomUserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserMasterReposistory userMasterReposistory;

	@Autowired
	private UserRolePermissionReposistory userRolePermissionReposistory;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<UserMaster> userMasterWrapper = userMasterReposistory.findByUserName(username);
		userMasterWrapper.orElseThrow(() -> new UsernameNotFoundException(
				"Provided username is not correct. Please provide correct username."));

		Optional<UserRolePermission> userPermissionWrapper = userRolePermissionReposistory
				.findByUserMaster(userMasterWrapper.get());
		userPermissionWrapper.orElseThrow(
				() -> new RuntimeException("User does not have required permission to access this ressource."));

		return new CustomUserDetailImpl(userMasterWrapper.get(), userPermissionWrapper.get().getRoleMaster().getRoleName());
	}

}