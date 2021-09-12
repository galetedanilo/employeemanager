package com.galete.employeemanager.services;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.galete.employeemanager.repositories.DepartmentRepository;
import com.galete.employeemanager.request.DepartmentRequest;
import com.galete.employeemanager.response.DepartmentResponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentService implements Serializable {

	private static final long serialVersionUID = 1L;

	private final DepartmentRepository departmentRepository;
	
	public DepartmentResponse addDepartment(DepartmentRequest departmentRequest) {
		
	}
}
