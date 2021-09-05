package com.galete.employeemanager.controllers.exceptions;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ExceptionResponse {

	private Instant timestamp;
	private Integer status;
	private String error;
	private String path;
}
