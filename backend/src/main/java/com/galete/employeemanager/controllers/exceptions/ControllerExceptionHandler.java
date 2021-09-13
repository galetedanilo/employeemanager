package com.galete.employeemanager.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.galete.employeemanager.services.exceptions.DatabaseException;
import com.galete.employeemanager.services.exceptions.ResourceNotFoundException;
import com.galete.employeemanager.services.exceptions.UniqueDatabaseException;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(UniqueDatabaseException.class)
	public ResponseEntity<ErrorMessage> usernameExistsException(UniqueDatabaseException ex, HttpServletRequest request) {
		ErrorMessage error = new ErrorMessage();
		
		error.setTimestamp(Instant.now());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setError("Email already in use");
		error.setPath(request.getRequestURI());
		error.setMessage(ex.getMessage());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
		ErrorMessage error = new ErrorMessage();
				
		error.setTimestamp(Instant.now());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setError("Resource not found");
		error.setPath(request.getRequestURI());
		error.setMessage(ex.getMessage());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<ErrorMessage> databaseException(DatabaseException ex, HttpServletRequest request) {
		ErrorMessage error = new ErrorMessage();
		
		error.setTimestamp(Instant.now());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setError("Database exception");
		error.setPath(request.getRequestURI());
		error.setMessage(ex.getMessage());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationErrorMessage> validationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
		ValidationErrorMessage error = new ValidationErrorMessage();
		
		error.setTimestamp(Instant.now());
		error.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
		error.setError("Validation Exception");
		error.setPath(request.getRequestURI());
		
		for(FieldError field : ex.getBindingResult().getFieldErrors()) {
			error.addErrorFieldMessage(field.getField(), field.getDefaultMessage());
		}
		
		return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
	}
}
