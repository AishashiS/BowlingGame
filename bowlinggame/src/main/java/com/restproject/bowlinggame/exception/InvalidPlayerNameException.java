package com.restproject.bowlinggame.exception;

public class InvalidPlayerNameException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String MESSAGE_TEMPLATE = "Invalid player with name %s";

    private String invalidPlayerName;

    public InvalidPlayerNameException(final String name) {
        super(String.format(MESSAGE_TEMPLATE, name));
        this.invalidPlayerName = name;
    }

    public String getInvalidPlayerName() {
        return invalidPlayerName;
    }

}
