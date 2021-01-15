package com.restproject.bowlinggame.exception;

public class NoSuchFrameException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;

	public NoSuchFrameException(final String msg) {
        super(msg);
    }

}