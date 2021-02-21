package com.srpl.um.ejb.request;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.srpl.um.ejb.entity.UmUser;
import com.srpl.um.ejb.entity.UmUserHistory;
import com.srpl.um.ejb.exceptions.UserNotFoundException;

/**
 * Session Bean implementation class UserHistoryDAO
 */
@Stateless
@LocalBean
public class UserHistoryDAO extends GenericDAO<UmUserHistory> {

	/**
	 * Default constructor.
	 */
	public UserHistoryDAO() {
		super(UmUserHistory.class);
	}

	@EJB
	UserDAO userDao;

	public List<UmUserHistory> listuserHistory() {
		List<UmUserHistory> historyList = findAll();
		List<UmUserHistory> detailsList = new ArrayList<UmUserHistory>();
		Iterator<UmUserHistory> i = historyList.iterator();
		while (i.hasNext()) {
			UmUserHistory history = (UmUserHistory) i.next();
			// System.out.println("1 user name ="+history.getUser().getUserName());
			UmUserHistory details = new UmUserHistory(
					history.getUserHistoryId(), history.getLoginTime(),
					history.getLogout_time(), history.getUser());
			// System.out.println("2 user name ="+details.getUser().getUserName());
			detailsList.add(details);
		}
		return detailsList;

	}

	// Updated Version Hammad
	public List<UmUserHistory> listUserHistory(Long userId) {
		List<UmUserHistory> historyList = null;
		UmUser user = null;
		try {
			user = userDao.umUserDetails(userId);
			historyList = em
					.createQuery(
							"SELECT h FROM UmUserHistory h WHERE h.user = :myUser",
							UmUserHistory.class).setParameter("myUser", user)
					.getResultList();
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return historyList;
	}

	/*
	 * public List<UmUserHistory> listUserHistory(){ List<UmUserHistory>
	 * historyList = findAll(); return historyList; }
	 */
	public Integer createUserHistory(UmUserHistory details) {
		try {
			System.out.println("createhistory in dao called");
			System.out.println("user id = " + details.getUser().getUserId());
			UmUserHistory history = new UmUserHistory(details.getLoginTime(),
					details.getLogout_time(), details.getUser());
			UmUser umUser = em.find(UmUser.class, details.getUser().getUserId());
			history.setUser(umUser);
			save(history);
			return history.getUserHistoryId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 1;
		}
	}

	/*
	 * public Integer createUserHistory(UmUserHistory userHistory, Long userId){
	 * try{ System.out.println("createhistory in dao called");
	 * 
	 * //UmUserHistory history = new UmUserHistory(details.getLoginTime(),
	 * details.getLogout_time(),details.getUser()); UmUser umUser =
	 * em.find(UmUser.class, userId); userHistory.setUser(umUser);
	 * save(userHistory); return userHistory.getUserHistoryId(); }
	 * catch(Exception e){ System.out.println(e.getMessage()); return 1; } }
	 */

	public void updateUserHistory(UmUserHistory details) {
		try {
			System.out.println("updatehistory in dao called");
			System.out.println("user id = " + details.getUser().getUserId());
			UmUserHistory history = new UmUserHistory(details.getLoginTime(),
					details.getLogout_time(), details.getUser());
			history.setUserHistoryId(details.getUserHistoryId());

			UmUser umUser = em.find(UmUser.class, details.getUser().getUserId());
			history.setUser(umUser);

			update(history);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/*
	 * public void updateUserHistory(UmUserHistory userHistory, Long userId){
	 * try{ System.out.println("updatehistory in dao called");
	 * //System.out.println("user id = "+details.getUserId()); // UmUserHistory
	 * history = new
	 * UmUserHistory(details.getLoginTime(),details.getLogout_time(
	 * ),details.getUser());
	 * //history.setUserHistoryId(details.getUserHistoryId());
	 * 
	 * //UmUser umUser = em.find(UmUser.class, details.getUserId()); UmUser
	 * umUser = em.find(UmUser.class, userId); userHistory.setUser(umUser);
	 * 
	 * update(userHistory); }catch(Exception e){
	 * System.out.println(e.getMessage()); } }
	 */

	public Timestamp getLoginTime(Integer historyId) {
		Timestamp retVal;
		// Hammad Here, i have made this change to get through some errors. need
		// to fix this in future
		try {
			UmUserHistory userHistory = em
					.createQuery(
							"from UmUserHistory where userHistoryId = :userHistoryId",
							UmUserHistory.class)
					.setParameter("userHistoryId", historyId).getSingleResult();
			retVal = userHistory.getLoginTime();
		} catch (Exception e) {
			System.out.println("error fetching userHistory");
			retVal = new Timestamp(new Date().getTime());
		}
		return retVal;
	}

}
