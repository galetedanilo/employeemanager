package com.galete.employeemanager.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "name is required")
	private String name;
	
	private String description;
}
