package com.srpl.um.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.srpl.um.ejb.request.UmPersistence;


/**
 * The persistent class for the um_group_roles database table.
 * 
 */
@Entity
@Table(name="um_group_roles" , schema=UmPersistence.schema)
public class UmGroupRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="UM_GROUP_ROLES_GROUPROLEID_GENERATOR", sequenceName="UM_GROUP_ROLES_GROUP_ROLE_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="UM_GROUP_ROLES_GROUPROLEID_GENERATOR")
	@Column(name="group_role_id")
	private Integer groupRoleId;

	@ManyToOne
	@JoinColumn(name="group_id")
	private UmGroup umGroup;

	@ManyToOne
	@JoinColumn(name="role_id")
	private UmRole umRole;
	
    public UmGroupRole() {
    }
    
    public UmGroupRole(UmGroup group, UmRole role) {
    	this.umGroup = group;
    	this.umRole = role;
    }

	public Integer getGroupRoleId() {
		return this.groupRoleId;
	}

	public void setGroupRoleId(Integer groupRoleId) {
		this.groupRoleId = groupRoleId;
	}

	public UmGroup getUmGroup() {
		return umGroup;
	}

	public void setUmGroup(UmGroup umGroup) {
		this.umGroup = umGroup;
	}

	public UmRole getUmRole() {
		return umRole;
	}

	public void setUmRole(UmRole umRole) {
		this.umRole = umRole;
	}

}