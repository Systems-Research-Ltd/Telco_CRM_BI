package com.srpl.um.ejb.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.srpl.um.ejb.request.UmPersistence;

@Entity
@Table(name = "um_user_history" , schema=UmPersistence.schema)
public class UmUserHistory {

	@Id
	@SequenceGenerator(name="USER_HISTORY_PK_SEQUENCE",sequenceName="um_user_history_user_history_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="USER_HISTORY_PK_SEQUENCE")
	@Column(name = "user_history_id")
	private Integer userHistoryId;
    @Column(name = "login_time")
	private Timestamp loginTime;
    @Column(name = "logout_time")
	private Timestamp logoutTime;
	
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)*/
    @ManyToOne
	@JoinColumn(name = "user_id")
	private UmUser user;
	
	public UmUserHistory(){
		
	}
    
    //for create user history
    public UmUserHistory(Timestamp loginTime, UmUser user){
		this.loginTime = loginTime;
		this.user = user;
	}
    
	public UmUserHistory(Timestamp loginTime, Timestamp logoutTime, UmUser user){
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
		this.user = user;
	}
	
	public UmUserHistory(Integer userHistoryId, Timestamp loginTime, Timestamp logoutTime, UmUser user){
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
		this.user = user;
		this.userHistoryId = userHistoryId;
	}
	
  //for update
    public UmUserHistory(Integer userHistoryId, Timestamp loginTime, Timestamp logoutTime){
		this.userHistoryId = userHistoryId;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
	}

	//to create user history
    public UmUserHistory(Timestamp loginTime){
		this.loginTime = loginTime;
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

	public UmUser getUser() {
		return user;
	}

	public void setUser(UmUser user) {
		this.user = user;
	}
	
}
