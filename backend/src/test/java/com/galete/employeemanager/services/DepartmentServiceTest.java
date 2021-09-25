package com.galete.employeemanager.services;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.galete.employeemanager.repositories.DepartmentRepository;

@ExtendWith(SpringExtension.class)
public class DepartmentServiceTest {

	@Mock
	private DepartmentRepository departmentRepository;
	
	@InjectMocks
	private DepartmentService departmentService;
	
	private Long existingId;
	private Long noExistingId;
	private Long dependentId;
}
