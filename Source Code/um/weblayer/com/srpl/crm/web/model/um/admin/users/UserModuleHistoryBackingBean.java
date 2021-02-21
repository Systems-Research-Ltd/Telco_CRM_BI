package com.srpl.crm.web.model.um.admin.users;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.um.ejb.entity.UmUserHistory;
import com.srpl.um.ejb.request.UserHistoryDAO;
import com.srpl.um.web.common.SessionDataBean;
import com.srpl.um.web.controller.BeanFactory;

@ManagedBean(name="userHistory")
public class UserModuleHistoryBackingBean extends JSFBeanSupport implements JSFBeanInterface, Serializable {

	/**
	 * 
	 */
	@EJB
	UserHistoryDAO userHistoryDao;
	
	private static final long serialVersionUID = 1L;
	private SessionDataBean session;
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
	
	public UserModuleHistoryBackingBean(){
		session = BeanFactory.getInstance().getSessionBean();
	}

	@Override
	public String actionListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserModuleHistoryBackingBean> getList() {
		System.out.println("userHistory() called");
		List<UserModuleHistoryBackingBean> myList = new ArrayList<UserModuleHistoryBackingBean>();

		List<UmUserHistory> uhDB = userHistoryDao.listUserHistory(session.getUserModule_selectedUser());
		System.out.println(uhDB.size());
		for (UmUserHistory ushd : uhDB) {

			UserModuleHistoryBackingBean uh = new UserModuleHistoryBackingBean();
			uh.setUserId(ushd.getUser().getUserId());
			uh.setLoginTime(ushd.getLoginTime());
			uh.setLogoutTime(ushd.getLogout_time());
			uh.setUser(ushd.getUser());
			uh.setUserHistoryId(ushd.getUserHistoryId());

			myList.add(uh);

		}
		return myList;

	}

}
