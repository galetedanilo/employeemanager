package com.galete.employeemanager.controllers.exceptions;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage extends ExceptionResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String message;
}
