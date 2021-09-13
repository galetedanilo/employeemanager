package com.galete.employeemanager.services.exceptions;

public class UniqueDatabaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UniqueDatabaseException(String msg) {
		super(msg);
	}

}
