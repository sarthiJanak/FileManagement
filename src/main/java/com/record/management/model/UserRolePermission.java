package com.record.management.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "user_role_permission")
public class UserRolePermission {

	@Id
	@Column(name = "user_role_id")
	@SequenceGenerator(name = "user_role_sequence", sequenceName = "user_role_sequence", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_role_sequence")
	private int userRolePermissionId;

	@JoinColumn(referencedColumnName = "userId")
	@OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.EAGER)
	private UserMaster userMaster;

	@JoinColumn(referencedColumnName = "roleId")
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.EAGER)
	private RoleMaster roleMaster;

	public int getUserRolePermissionId() {
		return userRolePermissionId;
	}

	public void setUserRolePermissionId(int userRolePermissionId) {
		this.userRolePermissionId = userRolePermissionId;
	}

	public UserMaster getUserMaster() {
		return userMaster;
	}

	public void setUserMaster(UserMaster userMaster) {
		this.userMaster = userMaster;
	}

	public RoleMaster getRoleMaster() {
		return roleMaster;
	}

	public void setRoleMaster(RoleMaster roleMaster) {
		this.roleMaster = roleMaster;
	}

	public UserRolePermission(int userRolePermissionId, UserMaster userMaster, RoleMaster roleMaster) {
		this.userRolePermissionId = userRolePermissionId;
		this.userMaster = userMaster;
		this.roleMaster = roleMaster;
	}
	
	public UserRolePermission() {
		
	}

}
