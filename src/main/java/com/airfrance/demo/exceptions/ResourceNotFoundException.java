package com.airfrance.demo.exceptions;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public ResourceNotFoundException(String message) {
		super(message);
		this.message = message;
	}

	public ResourceNotFoundException() {
	}
}
