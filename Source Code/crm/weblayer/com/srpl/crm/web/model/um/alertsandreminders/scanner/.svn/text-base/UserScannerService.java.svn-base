package com.srpl.crm.web.model.um.alertsandreminders.scanner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.srpl.crm.ejb.entity.UmAlertsAndReminders;
import com.srpl.crm.ejb.request.AlertsAndRemindersDAO;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.um.ejb.request.UserDAO;


public class UserScannerService implements AlertScanner {
	@EJB AlertsAndRemindersDAO alertsAndRemindersDao;
	@EJB UserDAO userDao;
	private List<UmAlertsAndReminders> alerts;
	@Override
	public List<UmAlertsAndReminders> start() {
		alerts = new ArrayList<UmAlertsAndReminders>();
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			alertsAndRemindersDao = (AlertsAndRemindersDAO) ctx.lookup("java:global/srpl/crm_businesslayer/AlertsAndRemindersDAO");
			userDao = (UserDAO) ctx.lookup("java:global/srpl/um_businesslayer/UserDAO");
			List<UmUser> users = alertsAndRemindersDao.usersByCreatedDate();
	    	Date date = new Date();
			for(UmUser usr : users){
				remind(usr.getUserId(), usr.getUserCompany().intValue(), date.toString());
				if(userDao.getUserGroups(usr.getUserId(), usr.getUserCompany()).size() == 0)
					alert(usr.getUserId(), usr.getUserCompany().intValue(), date.toString());
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}	
		return alerts;
	}

	@Override
	public void remind(long objectId, int companyId, String endDate) {
		 UmAlertsAndReminders reminder = new UmAlertsAndReminders();
		 try {
			InitialContext ctx = new InitialContext();
			userDao = (UserDAO) ctx.lookup("java:global/srpl/um_businesslayer/UserDAO");
			reminder.setTitle("User Created");
			reminder.setIsAlert(false);
			Date remGenerationDate = new Date();
			reminder.setDate(new Timestamp(remGenerationDate.getTime()));
			reminder.setTransmitStatus(false);
			reminder.setObjectId(objectId);
			reminder.setObjectType("User");
			reminder.setAlertsRemindersStatus(true);
			reminder.setCompanyId(companyId);
			reminder.setEndDate(Timestamp.valueOf(endDate));
			reminder.setUserId(userDao.getAccountUser(companyId, "Account Manager Group").getUserId());
		 } catch (NamingException e) {
			e.printStackTrace();
		 }		 
		 alerts.add(reminder);
	}

	@Override
	public void alert(long objectId, int companyId, String endDate) {
		UmAlertsAndReminders alert = new UmAlertsAndReminders();
		try {
			InitialContext ctx = new InitialContext();
			userDao = (UserDAO) ctx.lookup("java:global/srpl/um_businesslayer/UserDAO");
			alert.setTitle("Newly Created Users Alerts");
			alert.setTransmitStatus(false);
			alert.setAlertsRemindersStatus(true);
			alert.setIsAlert(true);
			Date alertGenerationDate = new Date();
			alert.setDate(new Timestamp(alertGenerationDate.getTime()));
			alert.setObjectId(objectId);
			alert.setObjectType("User");
			alert.setCompanyId(companyId);
			alert.setEndDate(Timestamp.valueOf(endDate));
			alert.setUserId(userDao.getAccountUser(companyId, "Account Manager Group").getUserId());
		} catch (NamingException e) {
			e.printStackTrace();
		}		
		alerts.add(alert);
	}	
}