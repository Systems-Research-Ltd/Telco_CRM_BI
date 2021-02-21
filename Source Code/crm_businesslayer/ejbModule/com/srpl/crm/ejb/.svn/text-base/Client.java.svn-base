package com.srpl.crm.ejb;

import javax.ejb.EJB;

import com.srpl.um.ejb.request.ParameterDAO;

public class Client {
	@EJB
	ParameterDAO dao;
	
	  public static void main(String[] arg){
		  Client c = new Client();
		  c.dao = new ParameterDAO();
		  System.out.println("-------------"+c.dao.list(new Long(38)).size());
	  }

}
