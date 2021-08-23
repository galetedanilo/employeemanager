package com.galete.employeemanager.response;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import com.galete.employeemanager.entities.Employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse extends RepresentationModel<EmployeeResponse> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long employeeId;
	private String name;
	private String email;
	private String jobTitle;
	private String phone;
	private String imageUrl;
	private String employeeCode;
	
	public EmployeeResponse(Employee entity) {
		employeeId = entity.getId();
		name = entity.getName();
		email = entity.getEmail();
		jobTitle = entity.getJobTitle();
		phone = entity.getPhone();
		imageUrl = entity.getImageUrl();
		employeeCode = entity.getEmployeeCode();
	}
}
