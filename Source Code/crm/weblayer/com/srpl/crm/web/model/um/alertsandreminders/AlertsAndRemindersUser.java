package com.srpl.crm.web.model.um.alertsandreminders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.web.controller.BeanFactory;
//import com.lmkr.crm.web.model.common.Address;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.srpl.crm.ejb.entity.UmAlertsAndReminders;
import com.srpl.crm.ejb.request.AlertsAndRemindersDAO;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.crm.web.model.AlertsAndRemindersBackingBean;
import com.srpl.crm.web.model.common.ColumnModel;


/**
 * @author Muhammad Tahir Saleh
 *
 */
@ManagedBean(name="alertsAndRemindersUser")
public class AlertsAndRemindersUser extends JSFBeanSupport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static List<ColumnModel> alertsAndRemindersColumns;
	public Boolean update = true;
	
	static{

		alertsAndRemindersColumns = new ArrayList<ColumnModel>();
		alertsAndRemindersColumns.add(new ColumnModel("id", "ID"));
		alertsAndRemindersColumns.add(new ColumnModel("title", "TITLE"));
		alertsAndRemindersColumns.add(new ColumnModel("date", "DATE"));
	/*	alertsAndRemindersColumns.add(new ColumnModel("alertsRemindersStatus", "ALERTS AND REMINDERS STATUS"));*/
	}

	@PostConstruct
	public void init(){
		String oldAction;
		try{
			oldAction = getParameter("old_action");
			switch(oldAction){
			case WebConstants.ACTION_VIEW:
				setViewAction();
				break;
			case WebConstants.ACTION_EDIT:
				setEditAction();
				break;
			case WebConstants.ACTION_DELETE:
				setDeleteAction();
				break;
			default:
				setViewAction();
			}
		}
		catch(Exception e){
			System.out.println("exception on old_action.");
		}
		
/*
  	Address address = BeanFactory.getInstance().getAddressBean();
 
		
		try{
			countryId = Integer.valueOf(getParameter("country_id"));
			address.stateAL(countryId);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		try{
			stateId = Integer.valueOf(getParameter("state_id"));
			address.cityAL(stateId);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
*/		
	}
	/**
	 * Declare all the dao objects here
	 * 
	 */
	@EJB AlertsAndRemindersDAO alertsAndRemindersDao;


	/**
	 * All the class variables i.e. selected list are declared here
	 */
	private static ArrayList<AlertsAndRemindersBackingBean> alertsAndReminders;


	
	/**
	 * Getters Setters
	 */
	
	
	public ArrayList<AlertsAndRemindersBackingBean> getAlertsAndReminders() {
		System.out.println("AlertsAndRemindersCB->getAlertsAndReminders()");
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		
		ArrayList<AlertsAndRemindersBackingBean> myList = new ArrayList<AlertsAndRemindersBackingBean>();
		AlertsAndRemindersBackingBean bean;
		int alertsAndRemindersSize = 0;
	

		//Get packages from DB
		List<UmAlertsAndReminders> alertsAndRemindersDb = alertsAndRemindersDao.listAlertsAndReminders(session.getUserId());
		try{
			alertsAndRemindersSize = alertsAndReminders.size();
		}
		catch (Exception e) {
			//handle exception
			System.out.println("packages not set yet, cant get size");
		}

		if(alertsAndRemindersSize != alertsAndRemindersDb.size() || update){
//		if(update){
			//update packages list
			for(UmAlertsAndReminders x:alertsAndRemindersDb){
				bean = new AlertsAndRemindersBackingBean();
				convert2Bean(x, bean);
				myList.add(bean);
			}
			setUpdate(false);
			setAlertsAndReminders(myList);
		}
		System.out.println("Whats wrong!");
		return alertsAndReminders;
	}

	public void setAlertsAndReminders(ArrayList<AlertsAndRemindersBackingBean> alertsAndReminders) {
		try{
			AlertsAndRemindersUser.alertsAndReminders.clear();
		}
		catch (Exception e) {
			//handle exception
			System.out.println("how can clear an empty list.");
		}
		AlertsAndRemindersUser.alertsAndReminders = alertsAndReminders;
	}
	
	public Boolean getUpdate() {
		return update;
	}

	public void setUpdate(Boolean update) {
		this.update = update;
	}

	public List<ColumnModel> getAlertsAndRemindersColumns() {
		return alertsAndRemindersColumns;
	}

	public void setCompanyColumns(List<ColumnModel> columns) {
		AlertsAndRemindersUser.alertsAndRemindersColumns = columns;
	}

	public String actionListener(){
		
		AlertsAndRemindersBackingBean bean = BeanFactory.getInstance().getAlertsAndRemindersBackingBean();
//		NewUserBackingBean userBean = BeanFactory.getInstance().getUserBackingBean();
		reset();
		
		UmAlertsAndReminders db;
//		UmUser user;
		Long id;
		Long userId;
		Boolean alertsAndRemindersStatus = false;
		
		switch (getAction()) {
		case WebConstants.ACTION_EDIT:
			id = Long.valueOf(getParameter("row_id").toString());
			try{
				db = alertsAndRemindersDao.alertsAndRemindersDetails(id);
				convert2Bean(db, bean);
				setEditAction();
				return WebConstants.ACTION_EDIT;
			}
			catch (Exception e) {
				// handle exception
				addError("Couldn't Load the Alerts and Reminders");
				System.out.println("Couldn't Load");
			}
			setListAction(true);
			return WebConstants.ACTION_LIST;
			
		case WebConstants.ACTION_UPDATE:
			id = Long.valueOf(getParameter("row_id").toString());
			bean.setId(id);
			
			try{
				db = new UmAlertsAndReminders();
				convert2Db(bean, db);
				//id= alertsAndRemindersDao.updateAlertdandReminders(id);
				
				setUpdate(true);
				addMessage("Alerts and Reminders status Successfully Updated");
			}
			catch (Exception e) {
				//handle exception
				System.out.println("Couldn't create");
				addError("Alerts and Reminders status Updation Failed.");
			}
			setListAction(true);
			return WebConstants.ACTION_LIST;
/*		
		case WebConstants.ACTION_CREATE:
			bean.reset();
			setCreateAction();
			return WebConstants.ACTION_CREATE;

		case WebConstants.ACTION_SAVE:
			try{
				db = new UmAlertsAndReminders();
//				user = new UmUser();
				convert2Db(bean, db);
//				convert2DbUser(userBean, user);
//				companyId = companyDao.createCompany(db,user);
				id = alertsAndRemindersDao.createAlertsAndReminders(db);
				bean.setId(id);
				setUpdate(true);
				setListAction(true);
				addMessage("Company Successfully Created");
			}
			catch (Exception e) {
				//handle exception
				System.out.println("Couldn't create");
				addError("Company Creation Failed.");
			}
			setListAction(true);
			return WebConstants.ACTION_LIST;
*/
		case WebConstants.ACTION_VIEW:
			id = Long.valueOf(getParameter("row_id").toString());
			
			try{
				db = alertsAndRemindersDao.alertsAndRemindersDetails(id);
//				user = userDao.companyAccountManager(companyId);
				convert2Bean(db, bean);
//				convert2BeanUser(user, userBean);
				setViewAction();
				return WebConstants.ACTION_VIEW;
			}
			catch (Exception e) {
				// handle exception
				addError("Couldn't Load the AlertAndReminders.");
				System.out.println("Couldn't Load");
			}
			setListAction(true);
			return WebConstants.ACTION_LIST;
			case WebConstants.ACTION_PARAMETER:
			userId = Long.valueOf(getParameter("row_id"));
			SessionDataBean session = BeanFactory.getInstance().getSessionBean();
			session.setCompanyForParameter(userId);
			return WebConstants.ACTION_PARAMETER;
			
			
			

/*
		case WebConstants.ACTION_CANCEL:
			setListAction(true);
			return WebConstants.ACTION_LIST;

		case WebConstants.ACTION_EDIT:
			id = Long.valueOf(getParameter("row_id").toString());
			try{
				db = alertsAndRemindersDao.alertsAndRemindersDetails(id);
//				user = userDao.companyAccountManager(companyId);
				convert2Bean(db, bean);
//				convert2BeanUser(user, userBean);
				setEditAction();
				return WebConstants.ACTION_EDIT;
			}

			catch (Exception e) {
				// handle exception
				addError("Couldn't Load the Company.");
				System.out.println("Couldn't Load");
			}
			setListAction(true);

			return WebConstants.ACTION_LIST;

		case WebConstants.ACTION_UPDATE:
			id = Long.valueOf(getParameter("row_id").toString());
			bean.setId(id);
			try{
				db = new UmAlertsAndReminders();
				convert2Db(bean, db);
				id = alertsAndRemindersDao.updateAlertsAndReminders(db);
				setUpdate(true);
				addMessage("Company Successfully Updated");
			}
			catch (Exception e) {
				//handle exception
				System.out.println("Couldn't create");
				addError("Package Updation Failed.");
			}
			setListAction(true);
			return WebConstants.ACTION_LIST;

		case WebConstants.ACTION_DELETE:
			id = Long.valueOf(getParameter("row_id").toString());
			try{
				db = alertsAndRemindersDao.alertsAndRemindersDetails(id);
//				user = userDao.companyAccountManager(companyId);
				convert2Bean(db, bean);
//				convert2BeanUser(user, userBean);
				setDeleteAction();
				return WebConstants.ACTION_DELETE;
			}
			catch (Exception e) {
				// handle exception
				addError("Couldn't Load the Company.");
				System.out.println("Couldn't Load");
			}
			setListAction(true);
			return WebConstants.ACTION_LIST;

		case WebConstants.ACTION_DELETE_CONFIRMED:
			id = Long.valueOf(getParameter("row_id").toString());
			try{
				alertsAndRemindersDao.deleteAlertsAndReminders(id);
			}
			catch (Exception e) {
				// handle exception
				addError("Couldn't Delete the Company.");
				System.out.println("Deletion Failed.");
			}
			setListAction(true);
			return WebConstants.ACTION_LIST;
			
		case WebConstants.ACTION_PARAMETER:
			id = Long.valueOf(getParameter("row_id"));
			SessionDataBean session = BeanFactory.getInstance().getSessionBean();
			session.setCompanyForParameter(id);
			return WebConstants.ACTION_PARAMETER;
*/
		default:
			setListAction(true);
			return WebConstants.ACTION_LIST;
		}
	}

	
	public void updateARstatus(){
		Long id=(long)2;
		
		
	   	System.out.println("updateAR() called");
    	
    	alertsAndRemindersDao.updateAlertdandReminders(id);
    	
    }
	private void convert2Bean(UmAlertsAndReminders x, AlertsAndRemindersBackingBean bean) {
		bean.setId(x.getId());
		bean.setTitle(x.getTitle());
		bean.setDate(x.getDate());
		bean.setUserId(x.getUserId());
		bean.setIsAlert(x.getIsAlert());
		bean.setTransmitStatus(x.getTransmitStatus());
		bean.setTransmitDate(x.getTransmitDate());
		bean.setAlertsAndRemindersStatus(x.getAlertsRemindersStatus());
		
	}
	public void alertsRemindersStatus(){
		
	}
	
	private void convert2Db(AlertsAndRemindersBackingBean bean, UmAlertsAndReminders db){
		db.setId(bean.getId());
		db.setTitle(bean.getTitle());
		db.setTransmitDate(bean.getTransmitDate());
		db.setDate(bean.getDate());
		db.setUserId(bean.getUserId());
		db.setIsAlert(bean.getIsAlert());
		db.setTransmitStatus(bean.getTransmitStatus());
		db.setAlertsRemindersStatus(bean.getAlertsAndRemindersStatus());

		
	}
	
/*	
	private void convert2DbUser(NewUserBackingBean bean, UmUser db){
		
		db.setIsFranchiseUser(bean.getIsFranchiseUser());
		//db.setUmUserGroups(bean.get)
		db.setUserAddress(db.getUserAddress());
		db.setUserCity(bean.getUserCity());
		db.setUserCountry(bean.getUserCountry());
		db.setUserEmail(bean.getUserEmail());
		db.setUserFname(bean.getUserFname());
		db.setUserId(bean.getUserId());
		db.setUserJobtitle(bean.getUserJobtitle());
		db.setUserLname(bean.getUserLname());
		db.setUserName(bean.getUserName());
		db.setUserPhone(bean.getUserPhone());
		db.setUserState(bean.getUserState());
		db.setUserStatus(bean.getUserStatus());
		db.setUserZipcode(bean.getUserZipcode());
		db.setUserPassword(bean.getUserPassword());
	}
*/
/*	
	private void convert2BeanUser(UmUser db, NewUserBackingBean bean){

		bean.setIsFranchiseUser(db.getIsFranchiseUser());
		//bean.setUmUserGroups(db.get)
		bean.setUserAddress(db.getUserAddress());
		bean.setUserCity(db.getUserCity());
		bean.setUserCountry(db.getUserCountry());
		bean.setUserEmail(db.getUserEmail());
		bean.setUserFname(db.getUserFname());
		bean.setUserId(db.getUserId());
		bean.setUserJobtitle(db.getUserJobtitle());
		bean.setUserLname(db.getUserLname());
		bean.setUserName(db.getUserName());
		bean.setUserPhone(db.getUserPhone());
		bean.setUserState(db.getUserState());
		bean.setUserStatus(db.getUserStatus());
		bean.setUserZipcode(db.getUserZipcode());
		bean.setUserPassword(db.getUserPassword());
	}
*/
	
	
}
