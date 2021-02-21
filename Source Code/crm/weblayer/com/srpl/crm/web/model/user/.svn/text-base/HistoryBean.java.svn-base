package com.srpl.crm.web.model.user;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.um.ejb.entity.UmUserHistory;
import com.srpl.um.ejb.request.UserDAO;
import com.srpl.um.ejb.exceptions.UserNotFoundException;
import com.srpl.um.ejb.request.UserHistoryDAO;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "history")
@SessionScoped
public class HistoryBean implements Serializable {
	private static final long serialVersionUID = 1L;
	public List<HistoryBackingBean> userHistoryData;
	@EJB
	UserHistoryDAO userHistoryDao;
	@EJB UserDAO userDao;

	public List<HistoryBackingBean> getUserHistoryData() {
		System.out.println("userHistory() called");
		List<HistoryBackingBean> myList = new ArrayList<HistoryBackingBean>();

		List<UmUserHistory> uhDB = userHistoryDao.listUserHistory(BeanFactory.getInstance().getSessionBean().getUserId());
		System.out.println(uhDB.size());
		for (UmUserHistory ushd : uhDB) {

			HistoryBackingBean uh = new HistoryBackingBean();
			uh.setUserId(ushd.getUser().getUserId());
			uh.setLoginTime(ushd.getLoginTime());
			uh.setLogoutTime(ushd.getLogout_time());
			uh.setUser(ushd.getUser());
			uh.setUserHistoryId(ushd.getUserHistoryId());

			myList.add(uh);

		}
		return myList;

	}

	public void setUserHistoryData(List<HistoryBackingBean> userHistoryData) {
		this.userHistoryData = userHistoryData;
	}

	public void createUserHistory() {
		System.out.println("createUserHistory called");
		java.util.Date date = new java.util.Date();
		Timestamp newTime = new Timestamp(date.getTime());
		UmUserHistory userHistoryDetails = null;
		try {
			userHistoryDetails = new UmUserHistory(newTime, userDao.umUserDetails(Long.parseLong("1")));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Integer userHistoryId = userHistoryDao
				.createUserHistory(userHistoryDetails);
		userHistoryDetails.setUserHistoryId(userHistoryId);

	}

	public void updateUserHistory() {
		System.out.println("updateUserHistory called");
		java.util.Date date = new java.util.Date();
		UmUserHistory historyDetails = null;
		try {
			historyDetails = new UmUserHistory(2,
					new Timestamp(date.getTime()), new Timestamp(date.getTime()),
					userDao.umUserDetails(Long.parseLong("3")));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userHistoryDao.updateUserHistory(historyDetails);
	}
}
