package com.galete.employeemanager.requests.cards;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentCardRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "deparment is required")
	private Long id;
}
