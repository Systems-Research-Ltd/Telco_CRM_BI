package com.srpl.crm.web.model.um.alertsandreminders.scanner;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;

import com.srpl.crm.ejb.entity.CustomerPaymentORM;
import com.srpl.crm.ejb.entity.UmAlertsAndReminders;
import com.srpl.crm.ejb.request.AlertsAndRemindersDAO;
import com.srpl.crm.ejb.request.UtilsDAO;


public class MonthlyPayment{
	
	public void start() {
		
		
		try{
			InitialContext ctx = new InitialContext();
			System.out.println("Cannot Initialize");
			UtilsDAO utilsDao = (UtilsDAO) ctx.lookup("java:global/srpl/businesslayer/UtilsDAO");
			System.out.println("Cannot get DAO");
			
			List<CustomerPaymentORM> list = utilsDao.CustomerPaymentList();
			for(CustomerPaymentORM sPORM : list) {
				if(sPORM.getPaymentDate().before(new Date())) {
					if(sPORM.getPaidAmount() < sPORM.getTotalAmount()) {
						alert(sPORM.getCustomerId(), sPORM.getTotalAmount() - sPORM.getPaidAmount());
					}
				}
			}
		}catch (Exception e) {
			System.out.println("Cannot get List.");
			e.printStackTrace();
		}
		
		
		
	}
	
	public void remind(long objectId,int companyId,String endDate) {
		
	}
	
	public void alert(Long userId, Long deficit) {
		String alertTitle = "Alert for nonpayment of monthly with the deficit of " + deficit + " Rs.";
		Date date = new Date();
		
		UmAlertsAndReminders alertsAndReminders  = null;
	
//		if(transmitStatus) {
			alertsAndReminders = new UmAlertsAndReminders(alertTitle, new Timestamp(date.getTime()), userId, false, true, new Timestamp(date.getTime()),false); 
/*		}
		else {
			alertsAndReminders = new UmAlertsAndReminders(alertTitle, new Timestamp(date.getTime()), userId, false, false, null,false);
		} 
*/		
			try{
				InitialContext ctx = new InitialContext();
				System.out.println("Cannot Initialize");
				AlertsAndRemindersDAO alertsAndRemindersDao = (AlertsAndRemindersDAO) ctx.lookup("java:global/srpl/businesslayer/AlertsAndRemindersDAO");
				System.out.println("Cannot get DAO");
				alertsAndRemindersDao.createAlertsAndReminders(alertsAndReminders);
			}catch (Exception e) {
				System.out.println("Cannot create Alerts.");
				e.printStackTrace();
			}
		
	}
}
