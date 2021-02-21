package com.srpl.um.ejb.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
//import javax.persistence.Persistence;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.srpl.um.ejb.request.UmPersistence;
/**
 * The persistent class for the um_roles database table.
 * 
 */
@Entity
@Table(name="um_roles" , schema=UmPersistence.schema)
public class UmRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="UM_ROLES_ROLEID_GENERATOR", sequenceName="UM_ROLES_ROLE_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="UM_ROLES_ROLEID_GENERATOR")
	@Column(name="role_id")
	private Integer roleId;

	@Column(name="role_details")
	private String roleDetails;

	@Column(name="role_title")
	private String roleTitle;

	//bi-directional many-to-one association to UmGroup
	@OneToMany(mappedBy="umRole")
	private Set<UmGroupRole> umGroups;

    public UmRole() {
    }

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleDetails() {
		return this.roleDetails;
	}

	public void setRoleDetails(String roleDetails) {
		this.roleDetails = roleDetails;
	}

	public String getRoleTitle() {
		return this.roleTitle;
	}

	public void setRoleTitle(String roleTitle) {
		this.roleTitle = roleTitle;
	}

	public Set<UmGroupRole> getUmGroups() {
		return this.umGroups;
	}

	public void setUmGroups(Set<UmGroupRole> umGroups) {
		this.umGroups = umGroups;
	}
	
}