package com.galete.employeemanager.requests.mins;

import java.io.Serializable;

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
	
	@NotNull(message = "deparment is required")
	private Long id;
}
