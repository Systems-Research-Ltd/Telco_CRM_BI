package com.srpl.crm.web.model.user;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.srpl.um.ejb.entity.UmUser;

@ManagedBean(name="historyBackingBean")
@ViewScoped
public class HistoryBackingBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer userHistoryId;
    private Timestamp loginTime;
    private Timestamp logoutTime;
	private Long userId;
	private UmUser user;
	
	
	
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
	public Timestamp getLogoutTime() {
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
