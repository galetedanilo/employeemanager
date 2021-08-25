package com.galete.employeemanager.services.exceptions;

public class UsernameExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UsernameExistsException(String msg) {
		super(msg);
	}

}
