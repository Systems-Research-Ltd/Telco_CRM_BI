package com.srpl.crm.web.common;

import javax.ejb.EJB;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.srpl.um.ejb.request.UmUtilsDAO;


public class SessionListener implements HttpSessionListener {
	  @EJB UmUtilsDAO uDao;
	  @Override
	  public void sessionCreated(HttpSessionEvent event) {
		System.out.println("sessionCreated - add one session into counter");
	  }
	 
	  @Override
	  public void sessionDestroyed(HttpSessionEvent event) {
		Long uId = (long)event.getSession().getAttribute("uId");  
		System.out.println(uId);
		uDao.updateStatus(uId);
		System.out.println("sessionDestroyed - deduct one session from counter");
	  }
}
