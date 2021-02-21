package com.srpl.crm.ejb.request;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.srpl.crm.ejb.entity.CsContactORM;

/**
 * Session Bean implementation class EmailTemplateDAO
 */
@Stateless
@LocalBean
public class EmailTemplateDAO {
	private final static String UNIT_NAME = "businesslayer";	 
    @PersistenceContext(unitName = UNIT_NAME)
    private EntityManager em;
    /**
     * Default constructor. 
     */
    public EmailTemplateDAO (){
    	
    }
	
    public boolean contactExists(String contactname){  
    	List<CsContactORM> con = em.createQuery("from CsContactORM where contactFname =:cname",CsContactORM.class).setParameter("cname", contactname).getResultList();
    	return !((con.size() == 0) ? true : false);
   }

    public boolean emailExists(String email){  
    	List<CsContactORM> con = em.createQuery("from CsContactORM where contactEmail = :email",CsContactORM.class).setParameter("email", email).getResultList();
    	return !((con.size() == 0) ? true : false);
    }
    public boolean sendEmail(String email,String title,String message){
    	System.out.println("Action send title in DAO"+title +"message" + message);
    	System.out.println("emailTemplate Dao method called");
    	System.out.println("email address is: " + email);
    	if(emailExists(email)){ 
    		System.out.println("email is"+email);
    	CsContactORM con = em.createQuery("from CsContactORM where contactEmail = :email",CsContactORM.class).setParameter("email", email).getSingleResult();
    		System.out.println("getemail is"+email);
    	final String contactname = "rizwan.softwareengineer05@gmail.com";
    	final String password = "systemsresearchltd";
    	Properties props = new Properties();
    	 props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "465");
	        props.put("mail.smtp.user", contactname);
	        props.put("mail.smtp.auth", true);
	        props.put("mail.smtp.starttls.enable", true);
	        props.put("mail.smtp.debug", true);   
	        props.put("mail.smtp.socketFactory.port", "465");
	        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        props.put("mail.smtp.socketFactory.fallback", "false"); 
    	 Session session = Session.getInstance( props, null);
	     session.setDebug(true);
	     try{
	    	 
    	 MimeMessage msg = new MimeMessage(session);
         msg.setFrom(new InternetAddress("rizwan.softwareengineer05@gmail.com"));            
         msg.addRecipient(RecipientType.TO, new InternetAddress(email));
         msg.setSubject("Loyality Message!" + title);
         msg.setSentDate(new Date());
       //  msg.setText("Dear "+ con.getContactFname()+ message);
         msg.setContent("Dear" +" " + con.getContactFname()+",\n\n" +message ,"text/html");
         msg.saveChanges();
         /*Date timeStamp = new Date();
         msg.setSentDate(timeStamp);
         MimeMultipart multiPart = new MimeMultipart();
         MimeBodyPart htmlPart = new MimeBodyPart();
         htmlPart.setContent(multiPart, "text/html");
         multiPart.addBodyPart(htmlPart);
         MimeBodyPart imgPart = new MimeBodyPart();
         String fileName = "WebContent/themes/default/images/CustomerRelationshipManagement.png";
         InputStream stream = null;
         ClassLoader classLoader = Thread.currentThread()
                 .getContextClassLoader();
         if (classLoader == null) {
             classLoader = this.getClass().getClassLoader();
         }
         stream = classLoader.getResourceAsStream(fileName);
         DataSource ds = (DataSource) new ByteArrayDataSource(stream, "image/*");             

         imgPart.setDataHandler(new DataHandler((javax.activation.DataSource) ds));
         imgPart.setHeader("Content-ID", "the-img-1");
         multiPart.addBodyPart(imgPart);
         // Set the message content!
        // msg.setContent(multiPart);
*/         Transport transport = session.getTransport("smtp");
        transport.connect("smtp.gmail.com", contactname,password);
         transport.sendMessage(msg, msg.getAllRecipients());
       //  transport.send(msg);
         transport.close();
        
    	}catch (Exception e) {
        	e.printStackTrace();
        }
    	 return true;
    } else
    		return false;
    }
}
