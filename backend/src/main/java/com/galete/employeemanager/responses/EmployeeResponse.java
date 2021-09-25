package com.galete.employeemanager.responses;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.galete.employeemanager.responses.mins.DepartmentMinResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse extends RepresentationModel<EmployeeResponse> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long employeeId;
	private String firstName;
	private String lastName;
	private String cpf;
	private String birthDate;
	private String email;
	private String jobTitle;
	private String imageUrl;
	private Instant created;
	private Instant updated;
	private String employeeCode;
	private List<PhoneResponse> phones;
	private DepartmentMinResponse department;
}
