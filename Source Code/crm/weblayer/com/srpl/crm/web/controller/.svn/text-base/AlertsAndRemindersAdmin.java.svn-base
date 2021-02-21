package com.srpl.crm.web.controller;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.crm.ejb.entity.UmAlertsAndReminders;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.um.ejb.exceptions.UserNotFoundException;
import com.srpl.crm.ejb.request.AlertsAndRemindersDAO;
import com.srpl.crm.web.model.AlertsAndRemindersBackingBean;
import com.srpl.crm.web.model.common.ColumnModel;
import com.srpl.um.ejb.request.UserDAO;

/**
 * @author Muhammad Tahir Saleh
 * 
 */
@ManagedBean(name = "alertsAndRemindersAdmin")
@SessionScoped
public class AlertsAndRemindersAdmin extends JSFBeanSupport implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static List<ColumnModel> alertsAndRemindersColumns;
	public Boolean update = true;

	static {

		alertsAndRemindersColumns = new ArrayList<ColumnModel>();
		alertsAndRemindersColumns.add(new ColumnModel("id", "ID"));
		alertsAndRemindersColumns.add(new ColumnModel("title", "TITLE"));
		alertsAndRemindersColumns.add(new ColumnModel("date", "DATE"));
		alertsAndRemindersColumns.add(new ColumnModel("userName", "USER"));
		alertsAndRemindersColumns.add(new ColumnModel("transmitAffirmation", "TRANSMITED"));
	}

	@PostConstruct
	public void init() {
		String oldAction;
		try {
			oldAction = getParameter("old_action");
			switch (oldAction) {
			case WebConstants.ACTION_VIEW:
				setViewAction();
				break;
			case WebConstants.ACTION_DELETE:
				setDeleteAction();
				break;
			default:
				setViewAction();
			}
		} catch (Exception e) {
			System.out.println("exception on old_action.");
		}

		/*
		 * Address address = BeanFactory.getInstance().getAddressBean();
		 * 
		 * 
		 * try{ countryId = Integer.valueOf(getParameter("country_id"));
		 * address.stateAL(countryId); } catch (Exception e) { // TODO: handle
		 * exception }
		 * 
		 * try{ stateId = Integer.valueOf(getParameter("state_id"));
		 * address.cityAL(stateId); } catch (Exception e) { // TODO: handle
		 * exception }
		 */
	}

	/**
	 * Declare all the dao objects here
	 * 
	 */
	@EJB
	AlertsAndRemindersDAO alertsAndRemindersDao;
	@EJB UserDAO userDao;
	
	private final static String UNIT_NAME = "businesslayer";
    @PersistenceContext(unitName = UNIT_NAME)
    private EntityManager em;

	/**
	 * All the class variables i.e. selected list are declared here
	 */
	private static ArrayList<AlertsAndRemindersBackingBean> alertsAndReminders;

	/**
	 * Getters Setters
	 */

	public ArrayList<AlertsAndRemindersBackingBean> getAlertsAndReminders() {
		System.out.println("AlertsAndRemindersCB->getAlertsAndReminders()");
		// SessionDataBean session = BeanFactory.getInstance().getSessionBean();

		ArrayList<AlertsAndRemindersBackingBean> myList = new ArrayList<AlertsAndRemindersBackingBean>();
		AlertsAndRemindersBackingBean bean;
		int alertsAndRemindersSize = 0;

		// Get packages from DB
		List<UmAlertsAndReminders> alertsAndRemindersDb = alertsAndRemindersDao
				.listAlertsAndReminders();
		try {
			alertsAndRemindersSize = alertsAndReminders.size();
		} catch (Exception e) {
			// handle exception
			System.out.println("packages not set yet, cant get size");
		}

		if (alertsAndRemindersSize != alertsAndRemindersDb.size() || update) {
			// if(update){
			// update packages list
			for (UmAlertsAndReminders x : alertsAndRemindersDb) {
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

	public void setAlertsAndReminders(
			ArrayList<AlertsAndRemindersBackingBean> alertsAndReminders) {
		try {
			AlertsAndRemindersAdmin.alertsAndReminders.clear();
		} catch (Exception e) {
			// handle exception
			System.out.println("how can clear an empty list.");
		}
		AlertsAndRemindersAdmin.alertsAndReminders = alertsAndReminders;
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
		AlertsAndRemindersAdmin.alertsAndRemindersColumns = columns;
	}

	public String actionListener() {

		AlertsAndRemindersBackingBean bean = BeanFactory.getInstance()
				.getAlertsAndRemindersBackingBean();
		// NewUserBackingBean userBean =
		// BeanFactory.getInstance().getUserBackingBean();
		reset();

		UmAlertsAndReminders db;
		Long id;

		switch (getAction()) {

		case WebConstants.ACTION_TRANSMIT_NOW:
			// bean.reset();
			// setCreateAction();
			//System.out.println("This is aar id" + id);

			try{
				List<UmAlertsAndReminders> dbList = alertsAndRemindersDao.listAlertsAndReminders();
				for(UmAlertsAndReminders x:dbList){
					
					if(!x.getTransmitStatus()) {
							if(emailAlertsAndReminders(x.getUserId())){
								
							}
					}					
					x.setTransmitStatus(true);
					alertsAndRemindersDao.updateAlertsAndReminders(x);
					
				}
				setUpdate(true);
				addMessage("Transmission Complete.");
			}
			catch (Exception e) {
				System.out.println("update failed.");
				addError("Transmission Failed.");
			}
		
//			createAlert("Import Event", 52L, true);
			return WebConstants.ACTION_LIST;

			
			

			/*
			 * case WebConstants.ACTION_SAVE: try{ db = new
			 * UmAlertsAndReminders(); // user = new UmUser(); convert2Db(bean,
			 * db); // convert2DbUser(userBean, user); // companyId =
			 * companyDao.createCompany(db,user); id =
			 * alertsAndRemindersDao.createAlertsAndReminders(db);
			 * bean.setId(id); setUpdate(true); setListAction(true);
			 * addMessage("Company Successfully Created"); } catch (Exception e)
			 * { //handle exception System.out.println("Couldn't create");
			 * addError("Company Creation Failed."); } setListAction(true);
			 * return WebConstants.ACTION_LIST;
			 */
		case WebConstants.ACTION_VIEW:
			id = Long.valueOf(getParameter("row_id").toString());
			try {
				db = alertsAndRemindersDao.alertsAndRemindersDetails(id);
				// user = userDao.companyAccountManager(companyId);
				convert2Bean(db, bean);
				// convert2BeanUser(user, userBean);
				setViewAction();
				return WebConstants.ACTION_VIEW;
			} catch (Exception e) {
				// handle exception
				addError("Couldn't Load the Company.");
				System.out.println("Couldn't Load");
			}
			setListAction(true);
			return WebConstants.ACTION_LIST;

		case WebConstants.ACTION_CANCEL:
			setListAction(true);
			return WebConstants.ACTION_LIST;
			/*
			 * case WebConstants.ACTION_EDIT: id =
			 * Long.valueOf(getParameter("row_id").toString()); try{ db =
			 * alertsAndRemindersDao.alertsAndRemindersDetails(id); // user =
			 * userDao.companyAccountManager(companyId); convert2Bean(db, bean);
			 * // convert2BeanUser(user, userBean); setEditAction(); return
			 * WebConstants.ACTION_EDIT; }
			 * 
			 * catch (Exception e) { // handle exception
			 * addError("Couldn't Load the Company.");
			 * System.out.println("Couldn't Load"); } setListAction(true);
			 * 
			 * return WebConstants.ACTION_LIST;
			 * 
			 * case WebConstants.ACTION_UPDATE: id =
			 * Long.valueOf(getParameter("row_id").toString()); bean.setId(id);
			 * try{ db = new UmAlertsAndReminders(); convert2Db(bean, db); id =
			 * alertsAndRemindersDao.updateAlertsAndReminders(db);
			 * setUpdate(true); addMessage("Company Successfully Updated"); }
			 * catch (Exception e) { //handle exception
			 * System.out.println("Couldn't create");
			 * addError("Package Updation Failed."); } setListAction(true);
			 * return WebConstants.ACTION_LIST;
			 */
		case WebConstants.ACTION_DELETE:
			id = Long.valueOf(getParameter("row_id").toString());
			try {
				db = alertsAndRemindersDao.alertsAndRemindersDetails(id);
				// user = userDao.companyAccountManager();
				convert2Bean(db, bean);
				// convert2BeanUser(user, userBean);
				setDeleteAction();
				return WebConstants.ACTION_DELETE;
			} catch (Exception e) {
				// handle exception
				addError("Couldn't Load the Company.");
				System.out.println("Couldn't Load");
			}
			setListAction(true);
			return WebConstants.ACTION_LIST;

		case WebConstants.ACTION_DELETE_CONFIRMED:
			id = Long.valueOf(getParameter("row_id").toString());
			try {
				alertsAndRemindersDao.deleteAlertsAndReminders(id);
			} catch (Exception e) {
				// handle exception
				addError("Couldn't Delete the Company.");
				System.out.println("Deletion Failed.");
			}
			setListAction(true);
			return WebConstants.ACTION_LIST;

		case "settings":
			id = Long.valueOf(getParameter("row_id").toString());
			try {
				db = alertsAndRemindersDao.alertsAndRemindersDetails(id);
				// user = userDao.companyAccountManager();
				convert2Bean(db, bean);
				// convert2BeanUser(user, userBean);
				setDeleteAction();
				return "settings";
			} catch (Exception e) {
				// handle exception
				addError("Couldn't Load the Alert/Reminder.");
				System.out.println("Couldn't Load");
			}
			setListAction(true);
			return WebConstants.ACTION_LIST;
			
		case "settingsConfirmed":
			id = Long.valueOf(getParameter("row_id").toString());
			try{
				db  = alertsAndRemindersDao.updateAlerts(alertsAndRemindersDao.alertsAndRemindersDetails(id));
				
			}catch (Exception e) {
				// handle exception
				addError("Couldn't Update the Alert/Reminder.");
				System.out.println("Couldn't Load");
			}
			setListAction(true);
			/*id = Long.valueOf(getParameter("row_id").toString());
			System.out.println("from parameter confirmed: " + id);*/
			return WebConstants.ACTION_LIST;
		
		default:
			setListAction(true);
			return WebConstants.ACTION_LIST;
		}
	}

	public void createAlert(String alertTitle, Long userId) {//, boolean transmitStatus) {
		
//		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		UmAlertsAndReminders alertsAndReminders  = null;
	
//		if(transmitStatus) {
			alertsAndReminders = new UmAlertsAndReminders(alertTitle, new Timestamp(date.getTime()), userId, false, true, new Timestamp(date.getTime()),false); 
/*		}
		else {
			alertsAndReminders = new UmAlertsAndReminders(alertTitle, new Timestamp(date.getTime()), userId, false, false, null,false);
		} 
*/		
		alertsAndRemindersDao.createAlertsAndReminders(alertsAndReminders);

	}

	public void createReminder(String reminderTitle, Long userId) {//, Boolean transmitStatus) {

		Date date = new Date();

		UmAlertsAndReminders alertsAndReminders = null;

//		if (transmitStatus) {
			alertsAndReminders = new UmAlertsAndReminders(reminderTitle,
					new Timestamp(date.getTime()), userId, false, true,
					new Timestamp(date.getTime()), false);
/*
 		} else {
			alertsAndReminders = new UmAlertsAndReminders(reminderTitle,
					new Timestamp(date.getTime()), userId, false, false, null,
					false);
		}
*/		
		alertsAndRemindersDao.createAlertsAndReminders(alertsAndReminders);

	}

	public void updateARstatus() { // Long id
		Long id = Long.parseLong(getParameter("row_id").toString());
		// Long id=(long)2;

		System.out.println("updateAR() called" + id);

		alertsAndRemindersDao.updateAlerts(alertsAndRemindersDao.alertsAndRemindersDetails(id));

	}
	
	private void convert2Bean(UmAlertsAndReminders x,
			AlertsAndRemindersBackingBean bean) {
		bean.setId(x.getId());
		bean.setTitle(x.getTitle());
		bean.setDate(x.getDate());
		bean.setUserId(x.getUserId());
		bean.setIsAlert(x.getIsAlert());
		bean.setTransmitStatus(x.getTransmitStatus());
		bean.setTransmitDate(x.getTransmitDate());
		
		
		
		UmUser user;
		String userName;
		
		try {
			user = userDao.umUserDetails(x.getUserId());
			userName = user.getUserFname() + " " + user.getUserLname();
			bean.setUserName(userName);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bean.setUserName("-");
		}
		
		if(x.getTransmitStatus()){
			bean.setTransmitAffirmation("Yes");
		}
		else {
			bean.setTransmitAffirmation("No");
		}
	}
	
	public boolean emailAlertsAndReminders(Long userId) {
		
		emailSession(userId, "Alert or Reminder is Sent.");
		try {
			
			UmUser usr = userDao.umUserDetails(userId);
			emailSession(usr.getUserReportsto(), "Alert or Reminder is Sent to a person who reports you.");
			
		} catch(UserNotFoundException unfe) {
			
			System.out.println(unfe);
		
		} 
		
		return true;
		
	}
	

    public void emailSession(Long userId, String message){
    		
//	    	UmUser usr = em.createQuery("from UmUser where userId = :email",UmUser.class).setParameter("email", userId).getSingleResult();	    
    		try {
    			UmUser usr = userDao.umUserDetails(userId);

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
	            msg.addRecipient(RecipientType.TO, new InternetAddress(usr.getUserEmail()));
	            msg.setSubject(message);
	            msg.setSentDate(new Date());
	            msg.setText("Dear "+usr.getUserFname()+",\n\n" + message);
	            msg.saveChanges();
	            Transport transport = session.getTransport("smtp");
	            transport.connect("smtp.gmail.com", username, password);
	            transport.sendMessage(msg, msg.getAllRecipients());
	            transport.close();
    		
    		} catch(UserNotFoundException unfe) {
    			System.out.println(unfe);
    			  		
    		
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
    		
    	}
    	
    	
    }
