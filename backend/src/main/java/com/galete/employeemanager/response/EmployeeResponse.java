package com.galete.employeemanager.response;

import java.io.Serializable;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

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
	private String employeeCode;
	private List<PhoneResponse> phones;
	private DepartmentMinResponse department;

}
