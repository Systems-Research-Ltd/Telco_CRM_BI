package com.srpl.crm.ejb.request;

/**
 * @author Muhammad Tahir Saleh
 *
 */
//import java.sql.Timestamp;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

//import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.entity.DBConfiguration;
import com.srpl.crm.ejb.entity.LoyaltyORM;
import com.srpl.crm.ejb.entity.LoyaltyRuleORM;
import com.srpl.crm.ejb.entity.MCampaign;
import com.srpl.crm.ejb.entity.OrderORM;
import com.srpl.crm.ejb.entity.UmAlertsAndReminders;
import com.srpl.um.ejb.entity.UmUser;

/**
 * Session Bean implementation class CompanyDAO
 */
@Stateless
@LocalBean

public class AlertsAndRemindersDAO extends GenericDAO<UmAlertsAndReminders>{
	
//	@EJB UserDAO userDao;
//	@EJB GroupDAO grpDao;
    /**
     * Default constructor. 
     */
    public AlertsAndRemindersDAO() {
        // TODO Auto-generated constructor stub
    	super(UmAlertsAndReminders.class);
    }
    
    //================= List company =======================//
    public List<UmAlertsAndReminders> listAlertsAndReminders(){
    	List<UmAlertsAndReminders> alertsandreminders = findAll();
        return alertsandreminders;    	

    	
    }
    
    public List<UmAlertsAndReminders> listAlertsAndReminders(Long userId){
/*    	List<UmAlertsAndReminders> alertsandreminderstransmitStatus = findAll();
    	//Iterator alertsAndRemindersIterator<UmAlertsAndReminders> = new Iterator(alertsandreminderstransmitStatus);
    	
    	for(UmAlertsAndReminders alertsAndReminders:alertsandreminderstransmitStatus){
    			if(!alertsAndReminders.getTransmitStatus()) {    		
*/
   				List<UmAlertsAndReminders> alertsandreminders = em.createQuery("from UmAlertsAndReminders where userId = :uId and transmitStatus = true",UmAlertsAndReminders.class).setParameter("uId", userId).getResultList();
                return alertsandreminders;    	
    	
    }
    
    public List<UmAlertsAndReminders> filterAlertsAndReminders(){
    	   return em.createQuery("from UmAlertsAndReminders where transmitStatus = false and isAlert = true",UmAlertsAndReminders.class).getResultList();    	     	
    }
    
    @SuppressWarnings("unchecked")
	public List<UmAlertsAndReminders> listExpiredAlerts(){
    	   Date date = new Date();
    	   return (List<UmAlertsAndReminders>)em.createNativeQuery("Select * From crm.alerts_and_reminders Where Date(date) < ?1",UmAlertsAndReminders.class).setParameter(1, date).getResultList();    	  
    }
    
  //================= Create company =======================//
    public Long createAlertsAndReminders(UmAlertsAndReminders details){
       	save(details);
    	return details.getId();
 
    }

   //================= Update Company =======================//
    public Long updateAlertsAndReminders(UmAlertsAndReminders alertsAndReminders){
    	update(alertsAndReminders);
    	return alertsAndReminders.getId();
    }

    //================= Delete Company =======================//
    public void deleteAlertsAndReminders(Long id){
    	delete(id);
    }
    
    public void deleteAllAlertsAndReminders() {
    	System.out.println("deleteAllAlertsAndReminders()");
    	Timestamp systemDate = new Timestamp((new Date()).getTime());
//    	Date systemDate = new Date();
    	System.out.println(systemDate);
    	List<UmAlertsAndReminders> deleteList = em.createQuery("select u from UmAlertsAndReminders u where u.date < :systemDate",UmAlertsAndReminders.class).setParameter("systemDate", systemDate).getResultList();

    	for(UmAlertsAndReminders deleteable: deleteList) {
    		System.out.println(deleteable.getId());
    		delete(deleteable.getId());
    	}
    }

    //================= Details of Company =======================//
    public UmAlertsAndReminders alertsAndRemindersDetails(Long id){
    	UmAlertsAndReminders alertsAndReminders = find(id);
    	return alertsAndReminders;
    }
    
    //====================update alerts===============//
    public UmAlertsAndReminders updateAlerts(UmAlertsAndReminders alertsAndReminders){
    	alertsAndReminders.setTransmitStatus(true);
		Date alertTransmitDate = new Date();
		alertsAndReminders.setTransmitDate(new Timestamp(alertTransmitDate.getTime()));
    	update(alertsAndReminders);
    	return alertsAndReminders;	
    	
    }
  
    public List<LoyaltyORM> customerLoyalties(){    	
    	return em.createQuery("FROM LoyaltyORM",LoyaltyORM.class).getResultList();
    }
  
    public List<OrderORM> customerOrders(){    	
    	return em.createQuery("FROM OrderORM",OrderORM.class).getResultList();
    }
  
    public List<DBConfiguration> importData(){    	
    	return em.createQuery("FROM DBConfiguration",DBConfiguration.class).getResultList();
    }
    
    public Connection dbConnection(DBConfiguration config){
		Connection conn = null;
		Properties connectionProps = new Properties();
	    connectionProps.put("user", config.getUserName());
	    connectionProps.put("password", config.getPassword());	    
	    try {
	    	Class.forName(config.getDriver());
			conn = DriverManager.getConnection(config.getConfigDB(), connectionProps);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
    
    @SuppressWarnings("unchecked")
	public List<MCampaign> campaignsByLaunchDate(){
    	Date date = new Date();
    	return (List<MCampaign>)em.createNativeQuery("Select * From crm.m_campaign Where Date(launch_date) = ?1",MCampaign.class).setParameter(1, date).getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public List<UmUser> usersByCreatedDate(){
    	Date date = new Date();
    	return (List<UmUser>)em.createNativeQuery("Select * From um_crm.um_users Where Date(user_addedon) = ?1",UmUser.class).setParameter(1, date).getResultList();
    }
  
    @SuppressWarnings("unchecked")
	public List<CsContactORM> loyaltyAlertsandReminders(LoyaltyORM loyalty) {    	
		List<CsContactORM> loyaltyList = new ArrayList<CsContactORM>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	List<LoyaltyRuleORM> loyaltyrule = em.createQuery("FROM LoyaltyRuleORM WHERE loyalty = :loyal",LoyaltyRuleORM.class).setParameter("loyal", loyalty).getResultList();
    	for(LoyaltyRuleORM lr : loyaltyrule){
    		String str = "";
    		String[] splitDate = null;
    		switch(lr.getLoyaltyRule()){
	    		case "contactCreatedon": 
	    			str = "Select * From crm.cs_contacts Where Date(contact_createdon) "+lr.getLoyaltyRuleCondition()+" ?1";
	    			splitDate = lr.getLoyaltyRuleValue().split(" ");
	    			break;
	    		case "contactDob": 
	    			str = "Select * From crm.cs_contacts Where Date(contact_dob) "+lr.getLoyaltyRuleCondition()+" ?1";
	    			splitDate = lr.getLoyaltyRuleValue().split(" ");
	    			break;	
    		}
			try {
				loyaltyList = (List<CsContactORM>)em.createNativeQuery(str,CsContactORM.class).setParameter(1, formatter.parse(splitDate[0])).getResultList();
			} catch (ParseException e) {
				e.printStackTrace();
			}
    	}    	
    	return loyaltyList;
    }
    
    /*public void loyaltyEmailScanner(String emails, String subject, String message){
    	try {

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
                msg.addRecipient(RecipientType.TO, new InternetAddress(emails));
                msg.setSubject(subject);
                msg.setSentDate(new Date());
                msg.setText(message);
                msg.saveChanges();
                Transport transport = session.getTransport("smtp");
                transport.connect("smtp.gmail.com", username, password);
                transport.sendMessage(msg, msg.getAllRecipients());
                transport.close();
    		
            } catch (Exception e) {
            	e.printStackTrace();
            }
    }*/
  

   
   /* public Long updateAlertdandReminders(Long userId){
    	System.out.println(userId);
    	UmAlertsAndReminders alertsAndReminders = em.createQuery("select u from UmAlertsAndReminders u where u.alertsRemindersStatus = :st and userId= :uId",UmAlertsAndReminders.class).setParameter("st", false).setParameter("uId", userId).getSingleResult();
    	if(alertsAndReminders.getAlertsRemindersStatus()){
    		alertsAndReminders.setAlertsRemindersStatus(false);
    	}
    	else
    	{
    		alertsAndReminders.setAlertsRemindersStatus(true);
    	}
    	update(alertsAndReminders);
    	return alertsAndReminders.getId();
    	
    	
    }*/
    
    
   /* public Long updateAlertsAndRemindersStatus(Long id,UmAlertsAndReminders alertsAndReminders){
    	update(alertsAndReminders);
    	return alertsAndReminders.getId();
    }*/

}
