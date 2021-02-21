package com.srpl.crm.common.utils;

import java.sql.Timestamp;

import com.srpl.um.ejb.entity.UmUser;

@Deprecated
public class UmUserHistoryDetails {

	
	private Integer userHistoryId;
    private Timestamp loginTime;
    private Timestamp logoutTime;
	private Long userId;
	private UmUser user;

	
	public UmUserHistoryDetails(){
		
	}
	
	
	//for update
    public UmUserHistoryDetails(Integer userHistoryId, Timestamp loginTime, Timestamp logoutTime, Long userId){
		this.userHistoryId = userHistoryId;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
		this.userId = userId;
	}
    
    //for create user history
    public UmUserHistoryDetails(Timestamp loginTime, Long userId){
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
		this.userId = userId;
	}

    // for listing
    public UmUserHistoryDetails(Integer userHistoryId, Timestamp loginTime, Timestamp logoutTime, UmUser umUser){
		this.userHistoryId = userHistoryId;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
		this.user = umUser;
	}
    
	
	

	public Integer getUserHistoryId() {
		return userHistoryId;
	}

	public void setUserHistoryId(Integer userHistoryId) {
		this.userHistoryId = userHistoryId;
	}

	public Timestamp getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}

	public Timestamp getLogout_time() {
		return logoutTime;
	}

	public void setLogoutTime(Timestamp logoutTime) {
		this.logoutTime = logoutTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public UmUser getUser() {
		return user;
	}

	public void setUser(UmUser user) {
		this.user = user;
	}

	
	
}
