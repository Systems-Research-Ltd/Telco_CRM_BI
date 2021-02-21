package com.srpl.crm.ejb.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException
public class BillReimbursementNotFoundException  extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BillReimbursementNotFoundException(){
		
	}
	 public BillReimbursementNotFoundException(String msg){
		 super(msg);
		 
	 }
	
}
