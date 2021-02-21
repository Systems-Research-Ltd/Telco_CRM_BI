package com.srpl.crm.ejb.request;

import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.srpl.um.ejb.entity.GroupPermission;
import com.srpl.crm.ejb.entity.OrderORM;
import com.srpl.um.ejb.entity.UmUser;

/**
 * Session Bean implementation class AuthenticationDAO
 */
@Stateless
@LocalBean
public class AuthenticationDAO {

	private final static String UNIT_NAME = "businesslayer";	 
    @PersistenceContext(unitName = UNIT_NAME)
    private EntityManager em;
    /**
     * Default constructor. 
     */
    public AuthenticationDAO() {
        // TODO Auto-generated constructor stub
    }
    
    public boolean userExists(String username){    	
    	List<UmUser> usr = em.createQuery("from UmUser where userName = :uname",UmUser.class).setParameter("uname", username).getResultList();
    	return !((usr.size() == 0) ? true : false);    	
    }
    
    public boolean emailExists(String email){    	
    	List<UmUser> usr = em.createQuery("from UmUser where userEmail = :email",UmUser.class).setParameter("email", email).getResultList();
    	return !((usr.size() == 0) ? true : false);    	
    }
    
    private Boolean passExists(Long userid, String password){    	
    	UmUser usr = (UmUser)em.find(UmUser.class, userid);    	    	
    	return ((usr.getUserPassword().equals(password)) ? true : false);    	
    }
    
    public boolean resetPassword(Long userid, String oldpass, String newpass) {    	
    	if(passExists(userid,oldpass)){    		    		
	    	UmUser usr = (UmUser)em.find(UmUser.class, userid); 
	    	if(usr.getUserName().isEmpty() == false){
	    		usr.setUserPassword(newpass);
	    		em.merge(usr);
	    		return true;
	    	}else
	    		return false;	    		
    	}else
    		return false;
    }
    
    public boolean forgotPassword(String email){
    	if(emailExists(email)){    		
	    	UmUser usr = em.createQuery("from UmUser where userEmail = :email",UmUser.class).setParameter("email", email).getSingleResult();
	    	String str = new String("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
	     	StringBuffer sb = new StringBuffer();
	    	Random rand = new Random();
	    	int te = 0;
	     	for(int i=1;i<=6;i++){
	     		te=rand.nextInt(36);
	     		sb.append(str.charAt(te));
	     	}
	     	String pswd = sb.toString();	     	
	    	usr.setUserPassword(pswd);
	    	em.merge(usr);
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
	        try {
	            MimeMessage msg = new MimeMessage(session);
	            msg.setFrom(new InternetAddress("rizwan.softwareengineer05@gmail.com"));            
	            msg.addRecipient(RecipientType.TO, new InternetAddress(email));
	            msg.setSubject("Your Password has been reset!");
	            msg.setSentDate(new Date());
	            msg.setText("Dear "+usr.getUserFname()+",\n\nYour Password has been reset. Your new Password is as follows:\n\n"+pswd);
	            msg.saveChanges();
	            Transport transport = session.getTransport("smtp");
	            transport.connect("smtp.gmail.com", username, password);
	            transport.sendMessage(msg, msg.getAllRecipients());
	            transport.close();
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	        return true;
    	}else
    		return false;
    } 
    
    public void setPermissions(Long pId, Long code){
    	System.out.println("PID"+pId);
    	System.out.println("CODE"+code);
    	GroupPermission per = em.find(GroupPermission.class, pId);
    	per.setPermissionCode(code);
    	em.merge(per);
    }
    
    public void getResults(){
    	List<OrderORM> orders = em.createQuery("select * from OrderORM where EXTRACT(YEAR FROM orderDate) = :year;",OrderORM.class).setParameter("year",2012).getResultList();
    	System.out.println("ORDER SIZE : "+orders.size());
    }
}