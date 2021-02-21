/*
 * Resides commons static utilities i.e. mail
 */
package com.srpl.um.common.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class Utils {

	public static void sendMail(String sendTo, String subject, String body, boolean isHtml){
    	final String username = "rizwan.softwareengineer05@gmail.com";
		final String password = "systemsresearchltd";
		Utils.sendMail(sendTo, subject, body, username, password, isHtml);
	}
	
	public static void sendMail(String sendTo, String subject, String body, String sendFrom, String sendFromPass, boolean isHtml){


    	final String username = sendFrom;
		final String password = sendFromPass;
		String encodingType = isHtml ? "text/html" : "text/plain";
    	Properties props = new Properties();
    	
    	//Currently this code supports only gmail
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
            msg.setFrom(new InternetAddress(username));            
            msg.addRecipient(RecipientType.TO, new InternetAddress(sendTo));
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setContent(body, encodingType);
            msg.saveChanges();
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", username, password);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
}
