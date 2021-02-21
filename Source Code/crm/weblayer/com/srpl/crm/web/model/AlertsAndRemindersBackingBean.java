package com.srpl.crm.web.model;

/**
 * @author Muhammad Tahir Saleh
 *
 */
import java.io.Serializable;
import java.sql.Timestamp;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.srpl.crm.ejb.request.AlertsAndRemindersDAO;
import com.srpl.um.ejb.request.UserDAO;

//import com.lmkr.crm.web.controller.BeanFactory;
//import com.lmkr.crm.web.model.common.Address;

@ManagedBean(name="alertsAndReminders")
public class AlertsAndRemindersBackingBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String title;
	private Long userId;
	private String userName;
	private Boolean isAlert;
	private Boolean transmitStatus;
	private Timestamp date;
	private Timestamp transmitDate;
	private String transmitAffirmation;
	private Boolean alertsAndRemindersStatus =false;
	
	@EJB AlertsAndRemindersDAO alertsAndRemindersDao;
	@EJB UserDAO userDao;

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Timestamp getTransmitDate() {
		return transmitDate;
	}

	public void setTransmitDate(Timestamp transmitDate) {
		this.transmitDate = transmitDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getTransmitAffirmation() {
		return transmitAffirmation;
	}

	public void setTransmitAffirmation(String transmitAffirmation) {
		this.transmitAffirmation = transmitAffirmation;
	}

	public void reset(){
		id =  null;
		title = "";
		userId = null;
		date = null;		
		isAlert = true;
		transmitStatus = false;
		transmitDate = null;
		alertsAndRemindersStatus = false;
	}

	public Boolean getAlertsAndRemindersStatus() {
		return alertsAndRemindersStatus;
	}

	public void setAlertsAndRemindersStatus(Boolean alertsAndRemindersStatus) {
		this.alertsAndRemindersStatus = alertsAndRemindersStatus;
	}

	public AlertsAndRemindersDAO getAlertsAndRemindersDao() {
		return alertsAndRemindersDao;
	}
	
	public void testFunction() {
		System.out.println("Print me");
	}
	
/*

	public void setAlert(String alertTitle, boolean alertImport) {
		
//		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		id =  null;
		title = alertTitle;
		userId = null;
		this.date = new Timestamp(date.getTime());		
		isAlert = alertImport;
		transmitStatus = false;
		transmitDate = null;
	}
	
	public void setReminder(String reminderTitle, boolean alertImport) {
		
//		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		id =  null;
		title = reminderTitle;
		userId = null;
		this.date = new Timestamp(date.getTime());		
		isAlert = alertImport;
		transmitStatus = false;
		transmitDate = null;
	}
	
*/	

}
