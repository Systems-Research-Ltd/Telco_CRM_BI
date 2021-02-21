package com.srpl.um.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.srpl.um.ejb.request.UmPersistence;

import java.util.Set;


/**
 * The persistent class for the um_groups database table.
 * 
 */
@Entity
@Table(name="um_groups" , schema=UmPersistence.schema)
public class UmGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="UM_GROUPS_GROUPID_GENERATOR2", sequenceName="UM_GROUPS_GROUP_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="UM_GROUPS_GROUPID_GENERATOR2")
	@Column(name="group_id")
	private Long groupId;

	@Column(name="company_id")
	private Long companyId;

	@Column(name="group_details")
	private String groupDetails;

	@Column(name="group_status")
	private Boolean groupStatus;

	@Column(name="group_title")
	private String groupTitle;

	//bi-directional many-to-one association to UmRole
	@OneToMany(mappedBy="umRole")
	private Set<UmGroupRole> umRole;

	//bi-directional many-to-one association to UmUserGroup
	@OneToMany(mappedBy="umGroup")
	private Set<UmUserGroup> umUserGroups;

	//bi-directional many-to-many association to UmUser
	//@ManyToMany(mappedBy="umGroups")
	//private Set<UmUser> umUsers;	

    public UmGroup() {
    }
    
    public UmGroup(Long companyId, String groupDetails, Boolean groupStatus, String groupTitle){
        this.companyId = companyId;
        this.groupDetails = groupDetails;
        this.groupStatus = groupStatus;
        this.groupTitle = groupTitle;
    } 
    
    public UmGroup(Long companyId, String groupDetails, Boolean groupStatus, String groupTitle, UmGroupRole role){
        this.companyId = companyId;
        this.groupDetails = groupDetails;
        this.groupStatus = groupStatus;
        this.groupTitle = groupTitle;
        this.umRole.add(role);
    }    
 
	public Long getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getGroupDetails() {
		return this.groupDetails;
	}

	public void setGroupDetails(String groupDetails) {
		this.groupDetails = groupDetails;
	}

	public Boolean getGroupStatus() {
		return this.groupStatus;
	}

	public void setGroupStatus(Boolean groupStatus) {
		this.groupStatus = groupStatus;
	}

	public String getGroupTitle() {
		return this.groupTitle;
	}

	public void setGroupTitle(String groupTitle) {
		this.groupTitle = groupTitle;
	}

	public Set<UmGroupRole> getUmRole() {
		return this.umRole;
	}

	public void setUmRole(Set<UmGroupRole> umRole) {
		this.umRole = umRole;
	}
	
	public Set<UmUserGroup> getUmUserGroups() {
		return this.umUserGroups;
	}

	public void setUmUserGroups(Set<UmUserGroup> umUserGroups) {
		this.umUserGroups = umUserGroups;
	}
	
	/*public Set<UmUser> getUmUsers() {
		return this.umUsers;
	}

	public void setUmUsers(Set<UmUser> umUsers) {
		this.umUsers = umUsers;
	}*/
	
}