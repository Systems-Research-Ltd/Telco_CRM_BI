package com.srpl.um.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.srpl.um.ejb.request.UmPersistence;


/**
 * The persistent class for the group_permissions database table.
 * 
 */
@Entity
@Table(name="group_permissions", schema=UmPersistence.schema)
public class GroupPermission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="GROUP_PERMISSIONS_PERMISSIONID_GENERATOR", sequenceName="GROUP_PERMISSIONS_PERMISSION_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GROUP_PERMISSIONS_PERMISSIONID_GENERATOR")
	@Column(name="permission_id")
	private Long permissionId;

	@Column(name="permission_code")
	private Long permissionCode;
	
	@ManyToOne
	@JoinColumn(name="permission_group")
	private UmGroup permissionGroup;

	@ManyToOne	
	@JoinColumn(name="permission_service")
	private UmService permissionService;

    public GroupPermission() {
    }
    
    public GroupPermission(UmService permissionService, UmGroup permissionGroup, Long permissionCode) {
    	this.permissionService = permissionService;
    	this.permissionGroup = permissionGroup;
    	this.permissionCode = permissionCode;
    }

	public Long getPermissionId() {
		return this.permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}

	public Long getPermissionCode() {
		return this.permissionCode;
	}

	public void setPermissionCode(Long permissionCode) {
		this.permissionCode = permissionCode;
	}

	public UmGroup getPermissionGroup() {
		return this.permissionGroup;
	}

	public void setPermissionGroup(UmGroup permissionGroup) {
		this.permissionGroup = permissionGroup;
	}

	public UmService getPermissionService() {
		return this.permissionService;
	}

	public void setPermissionService(UmService permissionService) {
		this.permissionService = permissionService;
	}

}