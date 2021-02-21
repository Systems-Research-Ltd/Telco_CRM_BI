package com.srpl.crm.web.model.um.alertsandreminders.scanner;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.entity.LoyaltyORM;
import com.srpl.crm.ejb.entity.MCampaign;
import com.srpl.crm.ejb.entity.MarketingCampaignSettings;
import com.srpl.crm.ejb.entity.MessageTemplateORM;
import com.srpl.crm.ejb.entity.OrderORM;
import com.srpl.crm.ejb.entity.UmAlertsAndReminders;
import com.srpl.crm.ejb.exceptions.LoyaltyNotFoundException;
import com.srpl.crm.ejb.request.AlertsAndRemindersDAO;
import com.srpl.crm.ejb.request.CampaignDAO;
import com.srpl.crm.ejb.request.CampaignSettingsDAO;
import com.srpl.crm.ejb.request.LoyaltyDAO;
import com.srpl.crm.ejb.request.MessageTemplateDAO;
import com.srpl.crm.ejb.request.OrderDAO;
import com.srpl.um.ejb.entity.MailTemplateORM;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.um.ejb.exceptions.UserNotFoundException;
import com.srpl.um.ejb.request.MailTemplateDAO;
import com.srpl.um.ejb.request.UserDAO;


public class EmailScannerService implements AlertScanner {

	@EJB AlertsAndRemindersDAO alertsAndRemindersDao;
	@EJB LoyaltyDAO loyaltyDao;
	@EJB CampaignDAO campDao;
	@EJB CampaignSettingsDAO campaignSettingsDao;
	@EJB UserDAO userDao;
	@EJB MessageTemplateDAO templateDao;	
	@EJB OrderDAO orderDao;
	@EJB MailTemplateDAO mailTemplateDao;
	private List<UmAlertsAndReminders> alerts;
	
	@Override
	public List<UmAlertsAndReminders> start() {
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			alertsAndRemindersDao = (AlertsAndRemindersDAO) ctx.lookup("java:global/srpl/crm_businesslayer/AlertsAndRemindersDAO");
			alerts = alertsAndRemindersDao.filterAlertsAndReminders();
			for(UmAlertsAndReminders alert: alerts){
				if(transmit(alert)){
					alertsAndRemindersDao.updateAlerts(alert);					
				}
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return alerts;
	}

	@Override
	public void remind(long objectId, int companyId, String endDate) {
		
	}

	@Override
	public void alert(long objectId, int companyId, String endDate) {

	}

	private boolean transmit(UmAlertsAndReminders alert){		
		StringBuilder emails = new StringBuilder();
		LoyaltyORM lorm = null;
		MCampaign corm = null;
		UmUser uorm = null;
		OrderORM oorm = null;
		MessageTemplateORM torm = null;
		try {
			InitialContext ctx = new InitialContext();
			alertsAndRemindersDao = (AlertsAndRemindersDAO) ctx.lookup("java:global/srpl/crm_businesslayer/AlertsAndRemindersDAO");
			loyaltyDao = (LoyaltyDAO) ctx.lookup("java:global/srpl/crm_businesslayer/LoyaltyDAO");
			campDao = (CampaignDAO) ctx.lookup("java:global/srpl/crm_businesslayer/CampaignDAO");
			campaignSettingsDao = (CampaignSettingsDAO) ctx.lookup("java:global/srpl/crm_businesslayer/CampaignSettingsDAO");
			userDao = (UserDAO) ctx.lookup("java:global/srpl/um_businesslayer/UserDAO");
			mailTemplateDao = (MailTemplateDAO) ctx.lookup("java:global/srpl/um_businesslayer/MailTemplateDAO");
			switch(alert.getObjectType()){
				case "Loyalty" :
					lorm = loyaltyDao.loyaltyDetail(alert.getObjectId());		
					List<CsContactORM> customers = alertsAndRemindersDao.loyaltyAlertsandReminders(lorm);
					if(customers.size() > 0){
						for(int i = 0; i < customers.size(); i++){
							if(customers.get(i).getContactEmail() != "") emails.append(customers.get(i).getContactEmail());
							if(i < (customers.size()-1)) emails.append(",");
						}
					}
					torm = new MessageTemplateORM(lorm.getLoyaltyTitle(), lorm.getLoyaltyDetails(), emails.toString()); break;
					/*torm = templateDao.messageTemplateDetails(lorm.getTemplateId().longValue());
					torm.setSendTo(emails.toString());
					break;*/
				case "Campaign" :
					corm = campDao.campaignDetails(alert.getObjectId());
					List<String> targets = campDao.campaignTargets(corm);
					if(targets.size() > 0){
						for(int i = 1; i < targets.size(); i++){
							emails.append(targets.get(i));
							if(i < (targets.size()-1)) emails.append(",");
						}
					}
					String cMessage = campDao.campaignMessage(corm);
					try{
						MarketingCampaignSettings campaignSettings = campaignSettingsDao.campaignSettingsDetail(corm);
						MailTemplateORM template = mailTemplateDao.details(campaignSettings.getMailTemplate());
						cMessage = mailTemplateDao.getMessageWithMailTemplate(template, corm);
					}catch (Exception e) {
						// TODO: handle exception
					}
					
					torm = new MessageTemplateORM(corm.getTitle(), cMessage, emails.toString()); break;
				case "User" :
					try {
						uorm = userDao.umUserDetails(alert.getObjectId());
						emails.append(uorm.getUserEmail());
						String message = "Dear "+uorm.getUserLname()+"\n Your login credentials are as follows: \n Login Name : "+uorm.getUserName()+" \n Password : "+uorm.getUserPassword();
						torm = new MessageTemplateORM("Login Credentials!", message, emails.toString()); break;
					} catch (UserNotFoundException e) {
						e.printStackTrace();
					}
				case "Order" :
					oorm = orderDao.retrieveOrder(alert.getObjectId());
					emails.append(oorm.getAssignedTo().getUserEmail());
					String message = "Dear "+oorm.getAssignedTo().getUserLname()+"\n Invoice have been generated against Order No.  "+oorm.getOrderId();
					torm = new MessageTemplateORM("Order Invoice Generated!", message, emails.toString()); break;						
			}
			if(emails.length() > 0) {
				final String username = "rizwan.softwareengineer05@gmail.com";
				final String password = "systemsresearchltd";
		    	Properties props = new Properties();
		        props.put("mail.smtp.host", "smtp.gmail.com");
		        props.put("mail.smtp.port", "465");
		        props.put("mail.smtp.user", username);
		        props.put("mail.smtp.auth", true);
		        props.put("mail.smtp.starttls.enable", true);
		        props.put("mail.smtp.debug", true);   
		        props.put("mail.smtp.socketFactory.port", "465");
		        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		        props.put("mail.smtp.socketFactory.fallback", "false");        
		        Session session = Session.getInstance(props, null);
		        session.setDebug(true);
	        
	            MimeMessage msg = new MimeMessage(session);
	            msg.setFrom(new InternetAddress("rizwan.softwareengineer05@gmail.com")); 
				msg.addRecipients(RecipientType.TO, InternetAddress.parse(torm.getSendTo())); 
	            msg.setSubject(torm.getTitle());
	            msg.setSentDate(new Date());
	            msg.setText(torm.getMessage());
	            msg.saveChanges();
	            Transport transport = session.getTransport("smtp");
	            transport.connect("smtp.gmail.com", username, password);
	            transport.sendMessage(msg, msg.getAllRecipients());
	            transport.close();
			}    
		} catch (LoyaltyNotFoundException e1) {
			e1.printStackTrace();
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
}
