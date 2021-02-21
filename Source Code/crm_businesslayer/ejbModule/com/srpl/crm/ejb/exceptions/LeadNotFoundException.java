package com.srpl.crm.ejb.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException
public class LeadNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public LeadNotFoundException() {
    }

    public LeadNotFoundException(String msg) {
        super(msg);
    }
}
