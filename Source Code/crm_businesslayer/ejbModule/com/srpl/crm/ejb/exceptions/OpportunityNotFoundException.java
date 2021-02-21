package com.srpl.crm.ejb.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException
public class OpportunityNotFoundException extends Exception{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

   	public OpportunityNotFoundException(){
		
	}
	
	public OpportunityNotFoundException(String msg){
		super(msg);
	}
	
}
