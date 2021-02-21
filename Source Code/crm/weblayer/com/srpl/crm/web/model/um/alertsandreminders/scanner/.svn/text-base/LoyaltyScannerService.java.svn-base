package com.srpl.crm.web.model.um.alertsandreminders.scanner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.entity.LoyaltyORM;
import com.srpl.crm.ejb.entity.UmAlertsAndReminders;
import com.srpl.crm.ejb.request.AlertsAndRemindersDAO;

public class LoyaltyScannerService implements AlertScanner {
	@EJB AlertsAndRemindersDAO alertsAndRemindersDao;
	private List<UmAlertsAndReminders> alerts;
	@Override
	public List<UmAlertsAndReminders> start() {
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			alertsAndRemindersDao = (AlertsAndRemindersDAO) ctx.lookup("java:global/srpl/crm_businesslayer/AlertsAndRemindersDAO");
			alerts = new ArrayList<UmAlertsAndReminders>();
			List<LoyaltyORM> loyalties = alertsAndRemindersDao.customerLoyalties();
			for(LoyaltyORM loyal : loyalties){
				List<CsContactORM> loyalCustomers = alertsAndRemindersDao.loyaltyAlertsandReminders(loyal);
				remind(loyal.getLoyaltyId(), 0, loyal.getLaunchDateTime().toString());
				if(loyalCustomers.size() > 0)
					alert(loyal.getLoyaltyId(), 0, loyal.getLaunchDateTime().toString());
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}			
		return alerts;
	}

	@Override
	public void remind(long objectId, int companyId, String endDate) {
		UmAlertsAndReminders reminder = new UmAlertsAndReminders();
		reminder.setTitle("Loyalty Reminder");
		reminder.setTransmitStatus(false);
		reminder.setAlertsRemindersStatus(true);
		Date alertGenerationDate = new Date();
		reminder.setIsAlert(false);
		reminder.setDate(new Timestamp(alertGenerationDate.getTime()));
		reminder.setObjectId(objectId);
		reminder.setObjectType("Loyalty");
		reminder.setCompanyId(companyId);
		reminder.setEndDate(Timestamp.valueOf(endDate));
		alerts.add(reminder);
	}

	@Override
	public void alert(long objectId, int companyId, String endDate) {
		UmAlertsAndReminders alert = new UmAlertsAndReminders();
		alert.setTitle("Loyalty Alerts");
		alert.setTransmitStatus(false);
		alert.setAlertsRemindersStatus(true);
		Date alertGenerationDate = new Date();
		alert.setIsAlert(true);
		alert.setDate(new Timestamp(alertGenerationDate.getTime()));
		alert.setObjectId(objectId);
		alert.setObjectType("Loyalty");
		alert.setCompanyId(companyId);
		alert.setEndDate(Timestamp.valueOf(endDate));
		alerts.add(alert);
	}	
}
