package com.srpl.crm.web.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.ejb.entity.UmAlertsAndReminders;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.um.ejb.exceptions.UserNotFoundException;
import com.srpl.crm.ejb.request.AlertsAndRemindersDAO;
import com.srpl.crm.web.model.common.ColumnModel;
import com.srpl.um.ejb.request.UserDAO;
@ManagedBean(name = "alertsAndRemindersBean")
@RequestScoped
public class AlertsAndRemindersBean extends JSFBeanSupport implements JSFBeanInterface  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long alert_reminders_id;
	private String title;
	private Long userId;
	private String userName;
	private Boolean isAlert;
	private Boolean transmitStatus;
	private Date date;
	private Timestamp transmitDate;
	private String transmitAffirmation;
	private Boolean alertsAndRemindersStatus =false;
	public  List<ColumnModel> alertsAndRemindersColumns;
	
	

	@EJB AlertsAndRemindersDAO alertsAndRemindersDao;
	@EJB UserDAO userDao;
	
	
	
	public AlertsAndRemindersBean(){
		alertsAndRemindersColumns =  new ArrayList<ColumnModel>();
		alertsAndRemindersColumns.add(new ColumnModel("title", "TITLE"));
		alertsAndRemindersColumns.add(new ColumnModel("date", "DATE"));
		alertsAndRemindersColumns.add(new ColumnModel("isAlert", "ALERT"));
	//	alertsAndRemindersColumns.add(new ColumnModel("transmitAffirmation", "TRANSMITED"));
	}
	
	public String actionListener() {
		AlertsAndRemindersBean bean= this;
		Long id;
		UmAlertsAndReminders db;
		setCurrentAction(getParameter("action"),this.getClass());
		System.out.println(getCurrentAction() + "current action");
		switch (getCurrentAction()) {
		case WebConstants.ACTION_CREATE:
			return WebConstants.ACTION_CRUD;
		case WebConstants.ACTION_SAVE: 
			try{
				java.sql.Timestamp launchDateTime;
			this.setDate(new Date());
			date = new Timestamp(this.getDate().getTime());
			//Date date = new Date();
				db = new UmAlertsAndReminders(title, new Timestamp(date.getTime()), userId, false, true, new Timestamp(date.getTime()),false);
				alertsAndRemindersDao.createAlertsAndReminders(db);
			 return WebConstants.ACTION_LIST;
		}catch(Exception e){
				  return WebConstants.ACTION_LIST;
		}
				 
		case WebConstants.ACTION_VIEW:
			id = Long.valueOf(getParameter("row_id").toString());
			try {
				db = alertsAndRemindersDao.alertsAndRemindersDetails(id);
				// user = userDao.companyAccountManager(companyId);
				this.setTitle(db.getTitle());
				this.setAlertsAndRemindersStatus(db.getAlertsRemindersStatus());
				this.setDate(db.getDate());
				this.setTransmitDate(db.getTransmitDate());
				this.setTransmitStatus(db.getTransmitStatus());
				this.setUserId(db.getUserId());
				//convert2Bean(db, bean);
				// convert2BeanUser(user, userBean);
				setViewAction();
				return WebConstants.ACTION_CRUD;
			} catch (Exception e) {
				// handle exception
				addError("Couldn't Load the Company.");
				System.out.println("Couldn't Load");
			}
			setListAction(true);
			return WebConstants.ACTION_CRUD;
		case WebConstants.ACTION_CANCEL:
		
	 return WebConstants.ACTION_LIST;
		case WebConstants.ACTION_DELETE:
			id = Long.valueOf(getParameter("row_id").toString());
			try {
				db = alertsAndRemindersDao.alertsAndRemindersDetails(id);
				// user = userDao.companyAccountManager();
				convert2Bean(db, bean);
				// convert2BeanUser(user, userBean);
				setDeleteAction();
				return WebConstants.ACTION_CRUD;
			} catch (Exception e) {
				// handle exception
				addError("Couldn't Load the Alerts and Reminders.");
				System.out.println("Couldn't Load");
			}
			setListAction(true);
			return WebConstants.ACTION_LIST;
		case WebConstants.ACTION_DELETE_CONFIRMED:
			id = Long.valueOf(getParameter("row_id").toString());
			try {
				alertsAndRemindersDao.deleteAlertsAndReminders(id);
				addMessage(" Alerts and Reminders Successfully Deleted.");
			} catch (Exception e) {
				// handle exception
				addError("Couldn't Delete the  Alerts and Reminders.");
				System.out.println("Deletion Failed.");
			}
			setListAction(true);
			return WebConstants.ACTION_LIST;
			
		case "settings":
			id = Long.valueOf(getParameter("row_id").toString());
			try {
				db = alertsAndRemindersDao.alertsAndRemindersDetails(id);
				// user = userDao.companyAccountManager();
				convert2Bean(db, bean);
				return "alertsettings";
			} catch (Exception e) {
				// handle exception
				addError("Couldn't Load the Alert/Reminder.");
				System.out.println("Couldn't Load");
			}
			setListAction(true);
			return WebConstants.ACTION_LIST;
		
		case "settingsConfirmed":
			id = Long.valueOf(getParameter("row_id").toString());
			try{
				db  = alertsAndRemindersDao.updateAlertdandReminders(id);
				return WebConstants.ACTION_LIST;
			}catch (Exception e) {
				// handle exception
				addError("Couldn't Update the Alert/Reminder.");
				System.out.println("Couldn't Load");
			}
			setListAction(true);
			return WebConstants.ACTION_LIST;
			
		}
		return (null);
	}
	
	
	public List<UmAlertsAndReminders> getAlertsAndRemindersList() 
	{
		
		List<UmAlertsAndReminders> alertsAndRemindersDb=null;
		alertsAndRemindersDb = alertsAndRemindersDao.listAlertsAndReminders();
		return alertsAndRemindersDb;
	}
	public Long getAlert_reminders_id() {
		return alert_reminders_id;
	}
	public void setAlert_reminders_id(Long alert_reminders_id) {
		this.alert_reminders_id = alert_reminders_id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Boolean getIsAlert() {
		return isAlert;
	}
	public void setIsAlert(Boolean isAlert) {
		this.isAlert = isAlert;
	}
	public Boolean getTransmitStatus() {
		return transmitStatus;
	}
	public void setTransmitStatus(Boolean transmitStatus) {
		this.transmitStatus = transmitStatus;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Timestamp getTransmitDate() {
		return transmitDate;
	}
	public void setTransmitDate(Timestamp transmitDate) {
		this.transmitDate = transmitDate;
	}
	public String getTransmitAffirmation() {
		return transmitAffirmation;
	}
	public void setTransmitAffirmation(String transmitAffirmation) {
		this.transmitAffirmation = transmitAffirmation;
	}
	public Boolean getAlertsAndRemindersStatus() {
		return alertsAndRemindersStatus;
	}
	public void setAlertsAndRemindersStatus(Boolean alertsAndRemindersStatus) {
		this.alertsAndRemindersStatus = alertsAndRemindersStatus;
	}
	
	public List<ColumnModel> getAlertsAndRemindersColumns() {
		return alertsAndRemindersColumns;
	}
	public void setAlertsAndRemindersColumns(
			List<ColumnModel> alertsAndRemindersColumns) {
		this.alertsAndRemindersColumns = alertsAndRemindersColumns;
	}

	
	private void convert2Bean(UmAlertsAndReminders x,
			AlertsAndRemindersBean bean) {
		bean.setAlert_reminders_id(x.getId());
		bean.setTitle(x.getTitle());
		bean.setDate(x.getDate());
		bean.setUserId(x.getUserId());
		bean.setIsAlert(x.getIsAlert());
		bean.setTransmitStatus(x.getTransmitStatus());
		bean.setTransmitDate(x.getTransmitDate());
		
		
		
		UmUser user = null;
		String userName;
		
		try {
			user = userDao.umUserDetails(x.getUserId());
			userName = user.getUserFname() + " " + user.getUserLname();
			bean.setUserName(userName);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bean.setUserName("-");
		}
		
		if(x.getTransmitStatus()){
			bean.setTransmitAffirmation("Yes");
		}
		else {
			bean.setTransmitAffirmation("No");
		}
	}
public void createAlert(String alertTitle, Long userId) {//, boolean transmitStatus) {
		
//		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		UmAlertsAndReminders alertsAndReminders  = null;
	
//		if(transmitStatus) {
			alertsAndReminders = new UmAlertsAndReminders(alertTitle, new Timestamp(date.getTime()), userId, false, true, new Timestamp(date.getTime()),false); 
/*		}
		else {
			alertsAndReminders = new UmAlertsAndReminders(alertTitle, new Timestamp(date.getTime()), userId, false, false, null,false);
		} 
*/		
		alertsAndRemindersDao.createAlertsAndReminders(alertsAndReminders);

	}

@Override
public List<?> getList() {
	// TODO Auto-generated method stub
	return null;
}
	
}
