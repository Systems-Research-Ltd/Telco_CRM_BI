package com.srpl.crm.web.model.um.alertsandreminders.scanner;

import java.util.List;
import java.util.TimerTask;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.srpl.crm.ejb.entity.UmAlertsAndReminders;
import com.srpl.crm.ejb.request.AlertsAndRemindersDAO;

public class AlertScannerService extends TimerTask {	
	
	public static short LOYALTY_ALERT;
	public static short CAMPAIGN_ALERT;
	public static short USER_ALERT;
	public static short ORDER_ALERT;
	public static short IMPORT_ALERT;
	
	public static final short OBJECT_TYPE_LOYALTY = 1;	
	public static final short OBJECT_TYPE_CAMPAIGN = 2;	
	public static final short OBJECT_TYPE_USER = 3;
	public static final short OBJECT_TYPE_ORDER = 4;		
	public static final short OBJECT_TYPE_IMPORT = 5;
	
	public static short ALERT_TYPE_REMINDER = 0;
	public static short ALERT_TYPE_ALERT = 1;	
	
	public static boolean ALERT_STATUS_ACTIVE = true;
	public static boolean ALERT_STATUS_INACTIVE = false;	
	
	public static boolean IS_SCANNING=false;
	
	private List<UmAlertsAndReminders> alerts;	
	@EJB AlertsAndRemindersDAO alertsAndRemindersDao;
	
	public void run() {
		    System.out.println("--------------- Alert Scanner Started --------------");			
			IS_SCANNING=true;
			deleteExpiredAlerts();
			processLoyaltyAlerts();
			processCampaignAlerts();
			processUserAlerts();
			processOrderAlerts();
			processImportData();
			IS_SCANNING=false;
			System.out.println("--------------- Alert Scanner Ended --------------");
	}
	
	public void deleteExpiredAlerts() {
		   try {
			   InitialContext ctx = new InitialContext();
			   alertsAndRemindersDao = (AlertsAndRemindersDAO) ctx.lookup("java:global/srpl/crm_businesslayer/AlertsAndRemindersDAO");
			   for(UmAlertsAndReminders ex : alertsAndRemindersDao.listExpiredAlerts())
					alertsAndRemindersDao.deleteAlertsAndReminders(ex.getId());
			} catch (NamingException e) {
				e.printStackTrace();
			}		   
	}
	
	public void processLoyaltyAlerts(){
		LoyaltyScannerService loyaltyScannerService = new LoyaltyScannerService();
		alerts = loyaltyScannerService.start();
		if(alerts.size() > 0) save();
	}
	
	public void processCampaignAlerts(){
		MarketingScannerService marketingScannerService = new MarketingScannerService();
		alerts = marketingScannerService.start();
		if(alerts.size() > 0) save();
	}
	
	public void processUserAlerts(){
		UserScannerService userScannerService = new UserScannerService();
		alerts = userScannerService.start();
		if(alerts.size() > 0) save();
	}
	
	public void processOrderAlerts(){
		OrderScannerService orderScannerService = new OrderScannerService();
		alerts = orderScannerService.start();
		if(alerts.size() > 0) save();
	}
	
	public void processImportData(){
		ImportScannerService importScannerService = new ImportScannerService();
		alerts = importScannerService.start();
		if(alerts.size() > 0) save();
	}

	public void save() {
		try {
			   InitialContext ctx = new InitialContext();
			   alertsAndRemindersDao = (AlertsAndRemindersDAO) ctx.lookup("java:global/srpl/crm_businesslayer/AlertsAndRemindersDAO");
			   for(UmAlertsAndReminders alert:alerts){			
					alertsAndRemindersDao.createAlertsAndReminders(alert);
				}
			} catch (NamingException e) {
				e.printStackTrace();
			}		
		sendEmails();
	}	
	
	public void sendEmails(){
		EmailScannerService emailScannerService = new EmailScannerService();
		emailScannerService.start();
	}
}