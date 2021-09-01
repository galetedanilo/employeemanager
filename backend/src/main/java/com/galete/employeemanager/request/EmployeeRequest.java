package com.galete.employeemanager.request;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
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
	@Size(min = 10, max = 15, message = "phone size should be between 10 and 15 ")
	private String phone;
	
	private String imageUrl;
}
