package com.record.management.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleDto {
	
	@JsonProperty("roleId")
	private int roleId;
	
	@JsonProperty("roleName")
	private String roleName;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public RoleDto(int roleId, String roleName) {
		this.roleId = roleId;
		this.roleName = roleName;
	}
	
	public RoleDto() {
		
	}
}