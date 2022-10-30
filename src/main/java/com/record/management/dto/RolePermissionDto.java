package com.record.management.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RolePermissionDto {

	@JsonProperty("permissionId")
	private int permissionId;
	
	@JsonProperty("roleId")
	private int roleId;

	@JsonProperty("isAdmin")
	private boolean isAdmin;

	@JsonProperty("viewPermission")
	private boolean viewPermission;

	@JsonProperty("addEditPermission")
	private boolean addEditPermission;

	@JsonProperty("deletePermission")
	private boolean deletePermission;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isViewPermission() {
		return viewPermission;
	}

	public void setViewPermission(boolean viewPermission) {
		this.viewPermission = viewPermission;
	}

	public boolean isAddEditPermission() {
		return addEditPermission;
	}

	public void setAddEditPermission(boolean addEditPermission) {
		this.addEditPermission = addEditPermission;
	}

	public boolean isDeletePermission() {
		return deletePermission;
	}

	public void setDeletePermission(boolean deletePermission) {
		this.deletePermission = deletePermission;
	}
	
	public int getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}

	public RolePermissionDto(int roleId, boolean isAdmin, boolean viewPermission, boolean addEditPermission,
			boolean deletePermission) {
		this.roleId = roleId;
		this.isAdmin = isAdmin;
		this.viewPermission = viewPermission;
		this.addEditPermission = addEditPermission;
		this.deletePermission = deletePermission;
	}
	
	public RolePermissionDto() {
		
	}

}
