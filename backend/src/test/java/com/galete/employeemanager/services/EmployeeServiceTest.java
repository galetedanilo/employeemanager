package com.galete.employeemanager.services;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.galete.employeemanager.entities.Employee;
import com.galete.employeemanager.factories.EmployeeFactory;
import com.galete.employeemanager.repositories.EmployeeRepository;
import com.galete.employeemanager.response.EmployeeResponse;
import com.galete.employeemanager.services.exceptions.EmployeeNotFoundException;

@ExtendWith(SpringExtension.class)
public class EmployeeServiceTest {

	@Mock
	private EmployeeRepository employeeRepository;
	
	@InjectMocks
	private EmployeeService employeeService;
	
	private Optional<Employee> employee;
	
	private Long existingId;
	private Long nonExistingId;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 2L;
		
		employee = Optional.of(EmployeeFactory.createEmployee());
		
		Mockito.when(employeeRepository.findById(existingId)).thenReturn(employee);
		Mockito.when(employeeRepository.findById(nonExistingId)).thenReturn(Optional.empty());
	}
	
	@Test
	public void findEmployeeByIdShouldReturnEmployeeResponseWhenIdExists() {
		
		EmployeeResponse employeeResponse = employeeService.findEmployeeById(existingId);
		
		Assertions.assertNotNull(employeeResponse);
		
		Mockito.verify(employeeRepository, Mockito.times(1)).findById(existingId);
	}
	
	@Test
	public void findEmployeeByIdShouldReturnEmployeeNotFoundExceptionWhenIdDoesNotExisting() {
		
		Assertions.assertThrows(EmployeeNotFoundException.class, () -> {
			employeeService.findEmployeeById(nonExistingId);
		});
		
		Mockito.verify(employeeRepository, Mockito.times(1)).findById(nonExistingId);
	}
}
