package com.srpl.crm.ejb.exceptions;

public class FranchiseNotFoundException extends Exception{
     
	public FranchiseNotFoundException(){
		
	}
	
    public FranchiseNotFoundException(String msg){
		super(msg);
	}
	
}
