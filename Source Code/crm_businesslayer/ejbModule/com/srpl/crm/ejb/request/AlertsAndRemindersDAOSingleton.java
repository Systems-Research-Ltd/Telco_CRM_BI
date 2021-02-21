package com.srpl.crm.ejb.request;

/**
 * @author Muhammad Tahir Saleh
 *
 */
//import java.sql.Timestamp;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

//import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Lock;
import static javax.ejb.LockType.READ;
import static javax.ejb.LockType.WRITE;
import javax.ejb.Stateless;

import com.srpl.crm.ejb.entity.UmAlertsAndReminders;

/**
 * Session Bean implementation class CompanyDAO
 */
@Stateless
@LocalBean
//@Singleton
//@Lock(READ)
public class AlertsAndRemindersDAOSingleton extends GenericDAO<UmAlertsAndReminders>{
	
//	@EJB UserDAO userDao;
//	@EJB GroupDAO grpDao;
    /**
     * Default constructor. 
     */
    public AlertsAndRemindersDAOSingleton() {
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
    
  //================= Create company =======================//
    public Long createAlertsAndReminders(UmAlertsAndReminders details){
  /*  	UmAlertsAndReminders alertsAndReminders = null;
    	alertsAndReminders = new UmAlertsAndReminders(details.getTitle(),details.getDate(),details.getUserId(),details.getIsAlert(),details.getTransmitStatus(),details.getTransmitDate());
    	save(alertsAndReminders);
    	return alertsAndReminders.getId();
   */
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
 
 //   @Lock(WRITE)
    public void deleteAllAlertsAndReminders() {
    	Timestamp systemDate = new Timestamp((new Date()).getTime());
    	System.out.println(systemDate);
    	/*List<UmAlertsAndReminders> deleteList = em.createQuery("select u.id from UmAlertsAndReminders u where u.date < :systemDate",UmAlertsAndReminders.class).setParameter("systemDate", systemDate).getResultList();

    	for(UmAlertsAndReminders deleteable: deleteList) {
    		System.out.println(deleteable.getId());
    		delete(deleteable.getId());
    	}*/
    }

    //================= Details of Company =======================//
    public UmAlertsAndReminders alertsAndRemindersDetails(Long id){
    	UmAlertsAndReminders alertsAndReminders = find(id);
    	return alertsAndReminders;
    }
    
    //====================update alerts===============//
  public UmAlertsAndReminders updateAlertdandReminders(Long id){
    	System.out.println("DAO"+id);
    	UmAlertsAndReminders alertsAndReminders = em.createQuery("select u from UmAlertsAndReminders u where id= :aId",UmAlertsAndReminders.class).setParameter("aId", id).getSingleResult();
    	if(alertsAndReminders.getAlertsRemindersStatus()){
    		alertsAndReminders.setAlertsRemindersStatus(false);
    	}
    	else
    	{
    		alertsAndReminders.setAlertsRemindersStatus(true);
    	}
    	update(alertsAndReminders);
    	return alertsAndReminders;
    	
    	
    }
  

   
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
