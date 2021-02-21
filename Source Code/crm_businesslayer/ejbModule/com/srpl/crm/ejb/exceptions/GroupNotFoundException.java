package com.srpl.crm.ejb.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException
public class GroupNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GroupNotFoundException() {
    }

    public GroupNotFoundException(String msg) {
        super(msg);
    }
}
