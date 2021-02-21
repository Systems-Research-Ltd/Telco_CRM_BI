package com.srpl.um.ejb.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException
public class DocumentNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DocumentNotFoundException() {
    }

    public DocumentNotFoundException(String msg) {
        super(msg);
    }
}
