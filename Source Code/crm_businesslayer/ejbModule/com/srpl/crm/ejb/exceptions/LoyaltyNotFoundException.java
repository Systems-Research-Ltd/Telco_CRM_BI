package com.srpl.crm.ejb.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException
public class LoyaltyNotFoundException  extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LoyaltyNotFoundException(){
		
	}
  public LoyaltyNotFoundException(String msg){
	  super(msg);
		
	}

}
