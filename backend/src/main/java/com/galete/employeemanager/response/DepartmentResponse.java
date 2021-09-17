package com.galete.employeemanager.response;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentResponse extends RepresentationModel<DepartmentResponse> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long departmentId;
	
	private String name;
	
	private String description;
	
	private Instant created;

	private Instant updated;
	
}
