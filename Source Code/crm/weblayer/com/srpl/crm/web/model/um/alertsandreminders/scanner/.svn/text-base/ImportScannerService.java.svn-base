package com.srpl.crm.web.model.um.alertsandreminders.scanner;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.primefaces.model.UploadedFile;

import com.srpl.crm.ejb.entity.CsAccount;
import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.entity.DBConfiguration;
import com.srpl.crm.ejb.entity.OrderORM;
import com.srpl.crm.ejb.entity.PaymentORM;
import com.srpl.crm.ejb.entity.ProductORM;
import com.srpl.crm.ejb.entity.SPackageORM;
import com.srpl.crm.ejb.entity.UmAlertsAndReminders;
import com.srpl.crm.ejb.exceptions.AccountNotFoundException;
import com.srpl.crm.ejb.request.AccountDAO;
import com.srpl.crm.ejb.request.AlertsAndRemindersDAO;
import com.srpl.crm.ejb.request.ContactDAO;
import com.srpl.crm.ejb.request.InvoiceDAO;
import com.srpl.crm.ejb.request.OrderDAO;
import com.srpl.crm.ejb.request.UtilsDAO;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.um.ejb.entity.City;
import com.srpl.um.ejb.entity.Country;
import com.srpl.um.ejb.entity.State;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.um.ejb.exceptions.UserNotFoundException;
import com.srpl.um.ejb.request.UserDAO;


public class ImportScannerService implements AlertScanner {
	@EJB AlertsAndRemindersDAO alertsAndRemindersDao;
	@EJB UtilsDAO utilsDao;
	@EJB UserDAO userDao;
	@EJB AccountDAO accountDao;
	@EJB ContactDAO contactDao;
	private List<UmAlertsAndReminders> alerts;
	@Override
	public List<UmAlertsAndReminders> start() {
		alerts = new ArrayList<UmAlertsAndReminders>();
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			alertsAndRemindersDao = (AlertsAndRemindersDAO) ctx.lookup("java:global/srpl/crm_businesslayer/AlertsAndRemindersDAO");
			utilsDao = (UtilsDAO) ctx.lookup("java:global/srpl/crm_businesslayer/UtilsDAO");
			List<DBConfiguration> configs = alertsAndRemindersDao.importData();
			for(DBConfiguration config : configs){
				Connection dbconn = alertsAndRemindersDao.dbConnection(config);
				if(dbconn != null){
					ResultSet res = null;
					Statement st = dbconn.createStatement();
					res = st.executeQuery(config.getConfigQuery());
					switch(config.getTableMapping().getMappingTable()){
						case "USER"		: importUserData(res, config); break;
						case "ACCOUNT" 	: importAccountData(res, config); break;
						case "CONTACT" 	: importContactData(res, config); break;
						/*case "PAYMENT" 	: importPaymentData(res, config); break;
						case "PRODUCT" 	: importProductData(res, config); break;
						case "PACKAGE" 	: importPackageData(res, config); break;
						case "ORDER" 	: importOrderData(res, config); break;
						case "COUNTRY" 	: importCountryData(res, config); break;
						case "STATE" 	: importStateData(res, config); break;
						case "CITY" 	: importCityData(res, config); break;*/
					}
				}	
				/*if(!invoiceDao.orderInvoiceGenerated(ord)){
					invoiceDao.create(ord.getOrderId());
					remind(ord.getOrderId(), 0, new Date().toString());
					alert(ord.getOrderId(), 0, new Date().toString());
				}*/	
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return alerts;
	}
	
	public void importUserData(ResultSet res, DBConfiguration config){
		try{
			InitialContext ctx = new InitialContext();
			userDao = (UserDAO) ctx.lookup("java:global/srpl/crm_businesslayer/UserDAO");
			utilsDao = (UtilsDAO) ctx.lookup("java:global/srpl/crm_businesslayer/UtilsDAO");
			String[] splitLine = new String[res.getMetaData().getColumnCount()];
			Integer[] selectedCSVHeader = new Integer[10];
			long companyId = config.getCompanyId();
			String[] mapStr = utilsDao.savedMap(config.getTableMapping().getMappingId()).split(",");
			int k = 0;
			for(String mp : mapStr){
				selectedCSVHeader[k] = Integer.parseInt(mp);
				k++;
			}
			while(res.next()){
				for(int i=1; i<=res.getMetaData().getColumnCount(); i++){
					splitLine[i-1] = res.getString(i);
				}
				if(!userDao.mappedIDExists(Long.parseLong(splitLine[selectedCSVHeader[9]-1]))){
					UmUser usr = new UmUser();
					usr.setUserFname(splitLine[selectedCSVHeader[0]-1]);
					usr.setUserLname(splitLine[selectedCSVHeader[1]-1]);
					usr.setUserName(splitLine[selectedCSVHeader[2]-1]);
					usr.setUserPassword(splitLine[selectedCSVHeader[3]-1]);
					usr.setUserAddress(splitLine[selectedCSVHeader[4]-1]);
					usr.setUserPhone(splitLine[selectedCSVHeader[5]-1]);
					usr.setUserEmail(splitLine[selectedCSVHeader[6]-1]);
					usr.setUserJobtitle(splitLine[selectedCSVHeader[7]-1]);
					usr.setUserStatus(splitLine[selectedCSVHeader[8]-1].equals("Active") || splitLine[selectedCSVHeader[8]-1].equals("1") || splitLine[selectedCSVHeader[8]-1].equals("true"));
					usr.setUserCompany((long)companyId);
					usr.setUserAddedon(new Timestamp(new Date().getTime()));
					usr.setIsFranchiseUser(false);
					usr.setIsOnline(false);
					usr.setUserCountry(169);
					usr.setUserState(3021);
					usr.setUserCity(14548);
					usr.setIsUserCustomer(false);
					usr.setMappedId(Long.parseLong(splitLine[selectedCSVHeader[9]-1]));
					userDao.createUser(usr);
				}else{				
					UmUser usr = userDao.umMappedUserDetails(Long.parseLong(splitLine[selectedCSVHeader[9]-1]));
					if(!usr.getUserFname().equals(splitLine[selectedCSVHeader[0]-1])) usr.setUserFname(splitLine[selectedCSVHeader[0]-1]);
					if(!usr.getUserLname().equals(splitLine[selectedCSVHeader[1]-1])) usr.setUserLname(splitLine[selectedCSVHeader[1]-1]);
					if(!usr.getUserName().equals(splitLine[selectedCSVHeader[2]-1])) usr.setUserName(splitLine[selectedCSVHeader[2]-1]);
					if(!usr.getUserPassword().equals(splitLine[selectedCSVHeader[3]-1])) usr.setUserPassword(splitLine[selectedCSVHeader[3]-1]);
					if(!usr.getUserAddress().equals(splitLine[selectedCSVHeader[4]-1])) usr.setUserAddress(splitLine[selectedCSVHeader[4]-1]);
					if(!usr.getUserPhone().equals(splitLine[selectedCSVHeader[5]-1])) usr.setUserPhone(splitLine[selectedCSVHeader[5]-1]);
					if(!usr.getUserEmail().equals(splitLine[selectedCSVHeader[6]-1])) usr.setUserEmail(splitLine[selectedCSVHeader[6]-1]);
					if(!usr.getUserJobtitle().equals(splitLine[selectedCSVHeader[7]-1])) usr.setUserJobtitle(splitLine[selectedCSVHeader[7]-1]);
					if(!usr.getUserStatus().equals(splitLine[selectedCSVHeader[8]-1])) usr.setUserStatus(splitLine[selectedCSVHeader[8]-1].equals("Active") || splitLine[selectedCSVHeader[8]-1].equals("1") || splitLine[selectedCSVHeader[8]-1].equals("true"));
					userDao.updateUser(usr);
				}
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void importAccountData(ResultSet res, DBConfiguration config){
		try{
			InitialContext ctx = new InitialContext();
			accountDao = (AccountDAO) ctx.lookup("java:global/srpl/crm_businesslayer/AccountDAO");
			utilsDao = (UtilsDAO) ctx.lookup("java:global/srpl/crm_businesslayer/UtilsDAO");
			String[] splitLine = new String[res.getMetaData().getColumnCount()];
			Integer[] selectedCSVHeader = new Integer[7];
			long companyId = config.getCompanyId();
			String[] mapStr = utilsDao.savedMap(config.getTableMapping().getMappingId()).split(",");
			int k = 0;
			for(String mp : mapStr){
				selectedCSVHeader[k] = Integer.parseInt(mp);
				k++;
			}
			while(res.next()){
				for(int i=1; i<=res.getMetaData().getColumnCount(); i++){
					splitLine[i-1] = res.getString(i);
				}
				if(!accountDao.mappedIDExists(Long.parseLong(splitLine[selectedCSVHeader[6]-1]))){
					CsAccount accnt = new CsAccount();
					accnt.setAccountTitle(splitLine[selectedCSVHeader[0]-1]);
		    		accnt.setAccountAddress(splitLine[selectedCSVHeader[1]-1]);
		    		accnt.setAccountEmail((selectedCSVHeader[2] != 0) ? splitLine[selectedCSVHeader[2]-1] : "");
		    		accnt.setAccountPhone(splitLine[selectedCSVHeader[3]-1]);
		    		accnt.setAccountIscompany(splitLine[selectedCSVHeader[4]-1].equals("true") || splitLine[selectedCSVHeader[4]-1].equals("Yes") || splitLine[selectedCSVHeader[4]-1].equals("1"));
		    		accnt.setAccountStatus(splitLine[selectedCSVHeader[5]-1].equals("Active") || splitLine[selectedCSVHeader[5]-1].equals("true") || splitLine[selectedCSVHeader[5]-1].equals("1"));
		    		accnt.setAccountCreatedon(new Timestamp(new Date().getTime()));
		    		accnt.setAccountCountry(169);
		    		accnt.setAccountState(3021);
		    		accnt.setAccountCity(14548);
		    		accnt.setCompanyId(companyId);
					accnt.setMappedId(Long.parseLong(splitLine[selectedCSVHeader[6]-1]));
					accountDao.createAccount(accnt);
				}else{				
					CsAccount accnt = accountDao.csMappedAccountDetails(Long.parseLong(splitLine[selectedCSVHeader[6]-1]));
					if(!accnt.getAccountTitle().equals(splitLine[selectedCSVHeader[0]-1])) accnt.setAccountTitle(splitLine[selectedCSVHeader[0]-1]);
					if(!accnt.getAccountAddress().equals(splitLine[selectedCSVHeader[1]-1])) accnt.setAccountAddress(splitLine[selectedCSVHeader[1]-1]);
					if(!accnt.getAccountEmail().equals(splitLine[selectedCSVHeader[2]-1])) accnt.setAccountEmail((selectedCSVHeader[2] != 0) ? splitLine[selectedCSVHeader[2]-1] : "");
					if(!accnt.getAccountPhone().equals(splitLine[selectedCSVHeader[3]-1])) accnt.setAccountPhone(splitLine[selectedCSVHeader[3]-1]);
					if(!accnt.getAccountIscompany().equals(splitLine[selectedCSVHeader[4]-1])) accnt.setAccountIscompany(splitLine[selectedCSVHeader[4]-1].equals("true") || splitLine[selectedCSVHeader[4]-1].equals("Yes") || splitLine[selectedCSVHeader[4]-1].equals("1"));
					if(!accnt.getAccountStatus().equals(splitLine[selectedCSVHeader[5]-1])) accnt.setAccountStatus(splitLine[selectedCSVHeader[5]-1].equals("Active") || splitLine[selectedCSVHeader[5]-1].equals("true") || splitLine[selectedCSVHeader[5]-1].equals("1"));
					accountDao.updateAccount(accnt);
				}
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (AccountNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void importContactData(ResultSet res, DBConfiguration config){
		try{
			InitialContext ctx = new InitialContext();
			accountDao = (AccountDAO) ctx.lookup("java:global/srpl/crm_businesslayer/AccountDAO");
			contactDao = (ContactDAO) ctx.lookup("java:global/srpl/crm_businesslayer/AccountDAO");
			utilsDao = (UtilsDAO) ctx.lookup("java:global/srpl/crm_businesslayer/UtilsDAO");
			String[] splitLine = new String[res.getMetaData().getColumnCount()];
			Integer[] selectedCSVHeader = new Integer[13];
			long companyId = config.getCompanyId();
			String[] mapStr = utilsDao.savedMap(config.getTableMapping().getMappingId()).split(",");
			int k = 0;
			for(String mp : mapStr){
				selectedCSVHeader[k] = Integer.parseInt(mp);
				k++;
			}
			while(res.next()){
				for(int i=1; i<=res.getMetaData().getColumnCount(); i++){
					splitLine[i-1] = res.getString(i);
				}
				if(!contactDao.mappedIDExists(Long.parseLong(splitLine[selectedCSVHeader[12]-1]))){
					CsContactORM contact = new CsContactORM();
					contact.setContactFname(splitLine[selectedCSVHeader[0]-1]);
		    		contact.setContactLname(splitLine[selectedCSVHeader[1]-1]);
		    		contact.setContactFatherName(splitLine[selectedCSVHeader[2]-1]);
		    		contact.setContactAddress(splitLine[selectedCSVHeader[3]-1]);
		    		contact.setContactCountry(169);
		    		contact.setContactState(3021);
		    		contact.setContactCity(14548);
		    		contact.setContactEmail((selectedCSVHeader[4] != 0) ? splitLine[selectedCSVHeader[4]-1] : "");
		    		contact.setContactPhone(splitLine[selectedCSVHeader[5]-1]);
		    		contact.setContactDob(null);
		    		contact.setContactCnic(splitLine[selectedCSVHeader[7]-1]);
			    	CsAccount accnt = null;
			    	if(selectedCSVHeader[8] != 0){
			    		try {		    			
							if(splitLine[selectedCSVHeader[8]-1].isEmpty() || !accountDao.accntIDExists(Long.parseLong(splitLine[selectedCSVHeader[8]-1]))){
								accnt = new CsAccount();
								accnt.setAccountTitle(splitLine[selectedCSVHeader[0]-1]+" "+splitLine[selectedCSVHeader[1]-1]);
					    		accnt.setAccountAddress(splitLine[selectedCSVHeader[3]-1]);
					    		accnt.setAccountEmail((selectedCSVHeader[4] != 0) ? splitLine[selectedCSVHeader[4]-1] : "");
					    		accnt.setAccountPhone(splitLine[selectedCSVHeader[5]-1]);
					    		accnt.setAccountIscompany(false);
					    		accnt.setAccountCreatedon(new Timestamp(new Date().getTime()));
					    		accnt.setAccountStatus(splitLine[selectedCSVHeader[11]-1].equals("Active") || splitLine[selectedCSVHeader[11]-1].equals("true") || splitLine[selectedCSVHeader[11]-1].equals(1));
					    		accnt.setAccountCountry(169);
					    		accnt.setAccountState(3021);
					    		accnt.setAccountCity(14548);
			    			}	
				    		contact.setCsAccount(accnt);
						} catch (NumberFormatException e) {
							e.printStackTrace();
						}
			    	}else{
			    		accnt = new CsAccount();
						accnt.setAccountTitle(splitLine[selectedCSVHeader[0]-1]+" "+splitLine[selectedCSVHeader[1]-1]);
			    		accnt.setAccountAddress(splitLine[selectedCSVHeader[3]-1]);
			    		accnt.setAccountEmail((selectedCSVHeader[4] != 0) ? splitLine[selectedCSVHeader[4]-1] : "");
			    		accnt.setAccountPhone(splitLine[selectedCSVHeader[5]-1]);
			    		accnt.setAccountIscompany(false);
			    		accnt.setAccountCreatedon(new Timestamp(new Date().getTime()));
			    		accnt.setAccountStatus(splitLine[selectedCSVHeader[11]-1].equals("Active") || splitLine[selectedCSVHeader[11]-1].equals("true") || splitLine[selectedCSVHeader[11]-1].equals(1));
			    		accnt.setAccountCountry(169);
			    		accnt.setAccountState(3021);
			    		accnt.setAccountCity(14548);
			    		contact.setCsAccount(accnt);
			    	}
		    		UmUser usr = new UmUser();
		    		usr.setUserFname(splitLine[selectedCSVHeader[0]-1]);
					usr.setUserLname(splitLine[selectedCSVHeader[1]-1]);
					usr.setUserName(splitLine[selectedCSVHeader[9]-1]);
					usr.setUserPassword(splitLine[selectedCSVHeader[10]-1]);
					usr.setUserAddress(splitLine[selectedCSVHeader[3]-1]);
					usr.setUserPhone(splitLine[selectedCSVHeader[5]-1]);
					usr.setUserEmail((selectedCSVHeader[4] != 0) ? splitLine[selectedCSVHeader[4]-1] : "");
					usr.setUserJobtitle("Customer");
					usr.setUserStatus(splitLine[selectedCSVHeader[11]-1].equals("Active") || splitLine[selectedCSVHeader[11]-1].equals("true") || splitLine[selectedCSVHeader[11]-1].equals(1));
					usr.setUserCompany(companyId);
					usr.setUserAddedon(new Timestamp(new Date().getTime()));
					usr.setIsFranchiseUser(false);
					usr.setIsOnline(false);
					usr.setUserCountry(169);
					usr.setUserState(3021);
					usr.setUserCity(14548);
					contact.setContactUser(usr);
		    		contact.setContactCreatedon(new Timestamp(new Date().getTime()));
		    		contact.setContactStatus(splitLine[selectedCSVHeader[11]-1].equals("Active") || splitLine[selectedCSVHeader[11]-1].equals("true") || splitLine[selectedCSVHeader[11]-1].equals(1));
					contact.setMappedId(Long.parseLong(splitLine[selectedCSVHeader[12]-1]));
					contactDao.createMappedContact(contact);
				}else{				
					CsContactORM contact = contactDao.mappedContactDetails(Long.parseLong(splitLine[selectedCSVHeader[12]-1]));
					if(!contact.getContactFname().equals(splitLine[selectedCSVHeader[0]-1])) contact.setContactFname(splitLine[selectedCSVHeader[0]-1]);
					if(!contact.getContactLname().equals(splitLine[selectedCSVHeader[1]-1])) contact.setContactLname(splitLine[selectedCSVHeader[1]-1]);
					if(!contact.getContactFatherName().equals(splitLine[selectedCSVHeader[2]-1])) contact.setContactFatherName(splitLine[selectedCSVHeader[2]-1]);
					if(!contact.getContactAddress().equals(splitLine[selectedCSVHeader[3]-1])) contact.setContactAddress(splitLine[selectedCSVHeader[3]-1]);
					if(!contact.getContactEmail().equals(splitLine[selectedCSVHeader[4]-1])) contact.setContactEmail((selectedCSVHeader[4] != 0) ? splitLine[selectedCSVHeader[4]-1] : "");
					if(!contact.getContactPhone().equals(splitLine[selectedCSVHeader[5]-1])) contact.setContactPhone(splitLine[selectedCSVHeader[5]-1]);
					if(!contact.getContactCnic().equals(splitLine[selectedCSVHeader[7]-1])) contact.setContactCnic(splitLine[selectedCSVHeader[7]-1]);
		    		contactDao.updateMappedContact(contact);
				}
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
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