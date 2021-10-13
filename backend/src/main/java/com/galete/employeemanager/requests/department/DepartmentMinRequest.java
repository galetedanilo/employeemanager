package com.galete.employeemanager.requests.department;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentMinRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "id department is required")
	private Long id;
	
	@NotBlank(message = "department name is required")
	private String name;

}
