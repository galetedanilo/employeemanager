package com.galete.employeemanager.request;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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
	
	@NotBlank(message = "name is required")
	private String name;
	@Email(message = "email not valid")
	@NotBlank(message = "email is required")
	private String email;
	@NotBlank(message = "job title is required")
	private String jobTitle;
	@NotBlank(message = "phone is required")
	private String phone;
	private String imageUrl;
	private String employeeCode;
}
