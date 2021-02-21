package com.srpl.um.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.srpl.um.ejb.request.UmPersistence;


/**
 * The persistent class for the um_user_groups database table.
 * 
 */
@Entity
@Table(name="um_user_groups" , schema=UmPersistence.schema)
public class UmUserGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="UM_USER_GROUPS_USERGROUPID_GENERATOR_G", sequenceName="UM_USER_GROUPS_USER_GROUP_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="UM_USER_GROUPS_USERGROUPID_GENERATOR_G")
	@Column(name="user_group_id")
	private Integer userGroupId;

	//bi-directional many-to-one association to UmGroup
    @ManyToOne
	@JoinColumn(name="group_id")
	private UmGroup umGroup;

	//bi-directional many-to-one association to UmUser
    @ManyToOne
	@JoinColumn(name="user_id")
	private UmUser umUser;
    
    @Column(name="is_user")
	private Boolean isUser;

    public UmUserGroup() {
    }
    
    public UmUserGroup(UmUser user, UmGroup group) {
    	this.umUser = user;
    	this.umGroup = group;
    	this.isUser = true;
    }    

	public Boolean getIsUser() {
		return isUser;
	}

	public void setIsUser(Boolean isUser) {
		this.isUser = isUser;
	}

	public Integer getUserGroupId() {
		return this.userGroupId;
	}

	public void setUserGroupId(Integer userGroupId) {
		this.userGroupId = userGroupId;
	}

	public UmGroup getUmGroup() {
		return this.umGroup;
	}

	public void setUmGroup(UmGroup umGroup) {
		this.umGroup = umGroup;
	}
	
	public UmUser getUmUser() {
		return this.umUser;
	}

	public void setUmUser(UmUser umUser) {
		this.umUser = umUser;
	}
	
}