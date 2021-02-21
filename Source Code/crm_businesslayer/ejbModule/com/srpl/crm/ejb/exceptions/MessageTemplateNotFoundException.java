package com.srpl.crm.ejb.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException
public class MessageTemplateNotFoundException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MessageTemplateNotFoundException(){
		
	}
	public MessageTemplateNotFoundException(String msg){
	super(msg);
		
	}
}
