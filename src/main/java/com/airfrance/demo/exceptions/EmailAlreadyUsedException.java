package com.airfrance.demo.exceptions;

public class EmailAlreadyUsedException extends RuntimeException {

	private String message;

	public EmailAlreadyUsedException(String message) {
		super(message);
		this.message = message;
	}

	public EmailAlreadyUsedException() {
	}
}
