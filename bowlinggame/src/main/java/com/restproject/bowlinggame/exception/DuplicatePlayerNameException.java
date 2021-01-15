package com.restproject.bowlinggame.exception;

public class DuplicatePlayerNameException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DuplicatePlayerNameException(final String msg) {
	        super(msg);
	    }

}
