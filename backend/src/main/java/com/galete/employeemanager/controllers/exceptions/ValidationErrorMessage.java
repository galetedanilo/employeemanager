package com.galete.employeemanager.controllers.exceptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class ValidationErrorMessage extends ExceptionResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errorFields = new ArrayList<>();
	
	public void addErrorFieldMessage(String field, String message) {
		errorFields.add(new FieldMessage(field, message));
	}
}
