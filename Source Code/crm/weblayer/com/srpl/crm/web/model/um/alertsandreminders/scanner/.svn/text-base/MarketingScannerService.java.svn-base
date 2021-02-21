package com.srpl.crm.web.model.um.alertsandreminders.scanner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.crm.ejb.entity.MCampaign;
import com.srpl.crm.ejb.entity.UmAlertsAndReminders;
import com.srpl.crm.ejb.request.AlertsAndRemindersDAO;
import com.srpl.crm.web.common.SessionDataBean;


public class MarketingScannerService implements AlertScanner {
	@EJB AlertsAndRemindersDAO alertsAndRemindersDao;
	private List<UmAlertsAndReminders> alerts;
	
	@Override
	public List<UmAlertsAndReminders> start() {
		alerts = new ArrayList<UmAlertsAndReminders>();
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			alertsAndRemindersDao = (AlertsAndRemindersDAO) ctx.lookup("java:global/srpl/crm_businesslayer/AlertsAndRemindersDAO");
			List<MCampaign> campaigns = alertsAndRemindersDao.campaignsByLaunchDate();
			for(MCampaign camp : campaigns){
				remind(camp.getId(), camp.getCompany_id().intValue(), camp.getEndDate().toString());
				alert(camp.getId(), camp.getCompany_id().intValue(), camp.getEndDate().toString());
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}	
		return alerts;
	}

	@Override
	public void remind(long objectId, int companyId, String endDate) {
		 UmAlertsAndReminders reminder = new UmAlertsAndReminders();
		 reminder.setTitle("Campaign Launched");
		 reminder.setIsAlert(false);
		 Date remGenerationDate = new Date();
		 reminder.setDate(new Timestamp(remGenerationDate.getTime()));
		 reminder.setTransmitStatus(false);
		 reminder.setObjectId(objectId);
		 reminder.setObjectType("Campaign");
		 reminder.setAlertsRemindersStatus(true);
		 reminder.setCompanyId(companyId);
		 reminder.setEndDate(Timestamp.valueOf(endDate));
		 alerts.add(reminder);
	}

	@Override
	public void alert(long objectId, int companyId, String endDate) {
		UmAlertsAndReminders alert = new UmAlertsAndReminders();
		alert.setTitle("Campaign Alerts");
		alert.setTransmitStatus(false);
		alert.setAlertsRemindersStatus(true);
		Date alertGenerationDate = new Date();
		alert.setIsAlert(true);
		alert.setDate(new Timestamp(alertGenerationDate.getTime()));
		alert.setObjectId(objectId);
		alert.setObjectType("Campaign");
		alert.setCompanyId(companyId);
		alert.setEndDate(Timestamp.valueOf(endDate));
		alerts.add(alert);
	}	
}