package com.srpl.crm.web.model.um.alertsandreminders.scanner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.srpl.crm.ejb.entity.OrderORM;
import com.srpl.crm.ejb.entity.UmAlertsAndReminders;
import com.srpl.crm.ejb.request.AlertsAndRemindersDAO;
import com.srpl.crm.ejb.request.InvoiceDAO;
import com.srpl.crm.ejb.request.OrderDAO;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.um.ejb.request.UserDAO;


public class OrderScannerService implements AlertScanner {
	@EJB AlertsAndRemindersDAO alertsAndRemindersDao;
	@EJB OrderDAO orderDao;
	@EJB InvoiceDAO invoiceDao;
	private List<UmAlertsAndReminders> alerts;
	@Override
	public List<UmAlertsAndReminders> start() {
		alerts = new ArrayList<UmAlertsAndReminders>();
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			alertsAndRemindersDao = (AlertsAndRemindersDAO) ctx.lookup("java:global/srpl/crm_businesslayer/AlertsAndRemindersDAO");
			invoiceDao = (InvoiceDAO) ctx.lookup("java:global/srpl/crm_businesslayer/InvoiceDAO");
			List<OrderORM> orders = alertsAndRemindersDao.customerOrders();
			for(OrderORM ord : orders){
				if(!invoiceDao.orderInvoiceGenerated(ord)){
					invoiceDao.create(ord.getOrderId());
					remind(ord.getOrderId(), 0, new Date().toString());
					alert(ord.getOrderId(), 0, new Date().toString());
				}	
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}	
		return alerts;
	}

	@Override
	public void remind(long objectId, int companyId, String endDate) {
		 UmAlertsAndReminders reminder = new UmAlertsAndReminders();
		 reminder.setTitle("Order Invoice Created!");
		 reminder.setIsAlert(false);
		 Date remGenerationDate = new Date();
		 reminder.setDate(new Timestamp(remGenerationDate.getTime()));
		 reminder.setTransmitStatus(false);
		 reminder.setObjectId(objectId);
		 reminder.setObjectType("Order");
		 reminder.setAlertsRemindersStatus(true);
		 reminder.setCompanyId(companyId);
		 reminder.setEndDate(Timestamp.valueOf(endDate));
		 alerts.add(reminder);
	}

	@Override
	public void alert(long objectId, int companyId, String endDate) {
		UmAlertsAndReminders alert = new UmAlertsAndReminders();
		alert.setTitle("Order Invoice Alerts!");
		alert.setTransmitStatus(false);
		alert.setAlertsRemindersStatus(true);
		alert.setIsAlert(true);
		Date alertGenerationDate = new Date();
		alert.setDate(new Timestamp(alertGenerationDate.getTime()));
		alert.setObjectId(objectId);
		alert.setObjectType("Order");
		alert.setCompanyId(companyId);
		alert.setEndDate(Timestamp.valueOf(endDate));
		alerts.add(alert);
	}	
}