package com.record.management.model.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.record.management.model.UserMaster;

public class CustomUserDetailImpl implements UserDetails {

	private String userName;
	private String password;
	private List<GrantedAuthority> authorities;
	private UserMaster userMaster;
	public CustomUserDetailImpl(UserMaster user, String role) {
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.authorities = Arrays.asList(new SimpleGrantedAuthority(role));
		this.userMaster = user;
	}

	public UserMaster getUser() {
		return userMaster;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return Boolean.TRUE;
	}

	@Override
	public boolean isAccountNonLocked() {
		return Boolean.TRUE;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return Boolean.TRUE;
	}

	@Override
	public boolean isEnabled() {
		return Boolean.TRUE;
	}

}
