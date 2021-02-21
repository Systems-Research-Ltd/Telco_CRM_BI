package com.srpl.crm.common.utils;

import java.io.Serializable;
import java.util.List;

@Deprecated
public class UmGroupDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long groupId;
	private Long companyId;
	private String groupDetails;
	private Boolean groupStatus;
	private String groupTitle;
	private List<Integer> umRoles;
	private List<Integer> umUserGroups;
	private List<Integer> umUsers;
	
    public UmGroupDetails() {
    	
    }

    public UmGroupDetails(Long groupId, Long companyId, String groupDetails, Boolean groupStatus, String groupTitle){
    	this.groupId = groupId;
        this.companyId = companyId;
        this.groupDetails = groupDetails;
        this.groupStatus = groupStatus;
        this.groupTitle = groupTitle;

    }
    
    public UmGroupDetails(Long companyId, String groupDetails, Boolean groupStatus, String groupTitle){
        this.companyId = companyId;
        this.groupDetails = groupDetails;
        this.groupStatus = groupStatus;
        this.groupTitle = groupTitle;
    }    
    
    public UmGroupDetails(Long groupId, Long companyId, String groupDetails, Boolean groupStatus, String groupTitle, List<Integer> umUserGroups, List<Integer> umUsers, List<Integer> umRoles){
    	this.groupId = groupId;
        this.companyId = companyId;
        this.groupDetails = groupDetails;
        this.groupStatus = groupStatus;
        this.groupTitle = groupTitle;
        this.umRoles = umRoles;
        this.umUserGroups = umUserGroups;
        this.umUsers = umUsers;
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
	
	public List<Integer> getUmUserGroups() {
		return this.umUserGroups;
	}

	public void setUmUserGroups(List<Integer> umUserGroups) {
		this.umUserGroups = umUserGroups;
	}
	
	public List<Integer> getUmRoles() {
		return this.umRoles;
	}
	
	public void setUmRoles(List<Integer> umRoles) {
		this.umRoles = umRoles;
	}
	
	public List<Integer> getUmUsers() {
		return this.umUsers;
	}

	public void setUmUsers(List<Integer> umUsers) {
		this.umUsers = umUsers;
	}
	
}