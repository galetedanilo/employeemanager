package com.galete.employeemanager.requests;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.galete.employeemanager.requests.mins.DepartmentMinRequest;

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
	
	@NotBlank(message = "first name is required")
	private String firstName;
	
	@NotBlank(message = "last name is required")
	private String lastName;
	
	@Email(message = "email is not valid")
	@NotBlank(message = "email is required")
	private String email;
	
	@NotBlank(message = "cpf is required")
	@CPF(message = "cpf is not valid")
	private String cpf;
	
	@NotBlank(message = "birth date is required")
	private String birthDate;
	
	@NotBlank(message = "job title is required")
	private String jobTitle;
	
	private String imageUrl;
	
	@Valid
	@NotEmpty
	private List<PhoneRequest> phones;	
	
	@Valid
	private DepartmentMinRequest department;
}
