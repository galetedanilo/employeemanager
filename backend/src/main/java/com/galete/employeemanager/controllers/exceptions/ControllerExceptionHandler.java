package com.galete.employeemanager.controllers.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.galete.employeemanager.services.exceptions.UserNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> userNotFoundException(UserNotFoundException ex, HttpServletRequest request) {
		
		return new ResponseEntity<>("DEu CERTO", HttpStatus.NOT_FOUND);
	}
}
