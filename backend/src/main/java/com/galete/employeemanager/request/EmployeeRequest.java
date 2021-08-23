package com.galete.employeemanager.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String email;
	private String jobTitle;
	private String phone;
	private String imageUrl;
	private String employeeCode;
}
