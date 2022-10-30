package com.record.management.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "role_permission")
public class RolePermission {
	
	@Id
	@Column(name = "permission_id")
	@SequenceGenerator(name = "role_permission_sequence", 
		sequenceName = "role_permission_sequence", 
		initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_permission_sequence")
	private int permissionId;
	
	@Column(name = "is_admin")
	private boolean isAdmin;
	
	@Column(name = "view_permission")
	private boolean viewPermission;
	
	@Column(name = "add_edit_permission")
	private boolean addEditPermission;
	
	@Column(name = "deletePermission")
	private boolean deletePermission;
	
	@OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	@JoinColumn(referencedColumnName = "roleId")
	private RoleMaster roleMaster;

	public int getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
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

	public RoleMaster getRoleMaster() {
		return roleMaster;
	}

	public void setRoleMaster(RoleMaster roleMaster) {
		this.roleMaster = roleMaster;
	}

	public RolePermission(int permissionId, boolean isAdmin, boolean viewPermission, boolean addEditPermission,
			boolean deletePermission, RoleMaster roleMaster) {
		this.permissionId = permissionId;
		this.isAdmin = isAdmin;
		this.viewPermission = viewPermission;
		this.addEditPermission = addEditPermission;
		this.deletePermission = deletePermission;
		this.roleMaster = roleMaster;
	}
	
	public RolePermission() {
		
	}
	
}
