package com.galete.employeemanager.services;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.galete.employeemanager.entities.Employee;
import com.galete.employeemanager.factories.EmployeeFactory;
import com.galete.employeemanager.repositories.EmployeeRepository;
import com.galete.employeemanager.requests.EmployeeRequest;
import com.galete.employeemanager.responses.EmployeeResponse;
import com.galete.employeemanager.services.exceptions.DatabaseException;
import com.galete.employeemanager.services.exceptions.ResourceNotFoundException;

@ExtendWith(SpringExtension.class)
public class EmployeeServiceTest {

	@Mock
	private EmployeeRepository employeeRepository;
	
	@InjectMocks
	private EmployeeService employeeService;
	
	private Employee employee;
	
	private EmployeeRequest employeeRequest;
	
	private PageImpl<Employee> employeePage;
		
	private Long existingId;
	private Long nonExistingId;
	private Long dependentId;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 2L;
		dependentId = 3L;
		
		employee = EmployeeFactory.createEmployee();
		employeeRequest = EmployeeFactory.createEmployeeRequest();
		
		employeePage = new PageImpl<Employee>(List.of(employee));
		
		Mockito.when(employeeRepository.findAll((Pageable)ArgumentMatchers.any())).thenReturn(employeePage);
		
		Mockito.when(employeeRepository.findEmployeeById(existingId)).thenReturn(Optional.of(employee));
		Mockito.when(employeeRepository.findEmployeeById(nonExistingId)).thenReturn(Optional.empty());
		
		Mockito.doNothing().when(employeeRepository).deleteById(existingId);
		Mockito.doThrow(EmptyResultDataAccessException.class).when(employeeRepository).deleteById(nonExistingId);
		Mockito.doThrow(DataIntegrityViolationException.class).when(employeeRepository).deleteById(dependentId);
		
		Mockito.when(employeeRepository.save(ArgumentMatchers.any())).thenReturn(employee);
		
		Mockito.when(employeeRepository.findByEmail(ArgumentMatchers.anyString())).thenReturn(Optional.empty());
	}
	
	@Test
	public void findAllEmployeesShouldReturnPageOfEmployeeResponse() {
		
		Pageable pageable = PageRequest.of(0, 20);
		
		Page<EmployeeResponse> employeePage = employeeService.findAllEmployees(pageable);
		
		Assertions.assertNotNull(employeePage);
	}
	
	@Test
	public void findEmployeeByIdShouldReturnEmployeeResponseWhenIdExists() {
		
		EmployeeResponse employeeResponse = employeeService.findEmployeeById(existingId);
		
		Assertions.assertNotNull(employeeResponse);
		
		Mockito.verify(employeeRepository, Mockito.times(1)).findEmployeeById(existingId);
	}
	
	@Test
	public void findEmployeeByIdShouldReturnEmployeeNotFoundExceptionWhenIdDoesNotExisting() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			employeeService.findEmployeeById(nonExistingId);
		});
		
		Mockito.verify(employeeRepository, Mockito.times(1)).findEmployeeById(nonExistingId);
	}
	
	@Test
	public void deleteEmployeeShouldReturnNothingWhenIdExits() {
		
		Assertions.assertDoesNotThrow(() -> {
			employeeService.deleteEmployee(existingId);
		});
		
		Mockito.verify(employeeRepository, Mockito.times(1)).deleteById(existingId);
	}
	
	@Test
	public void deleteEmployeeShouldReturnThrowResourceNotFoundExceptionWhenIdDoesNotExisting() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			employeeService.deleteEmployee(nonExistingId);
		});
		
		Mockito.verify(employeeRepository, Mockito.times(1)).deleteById(nonExistingId);
	}
	
	@Test
	public void deleteEmployeeShouldReturnThrowDatabaseExceptionWhenDepenentId() {
		
		Assertions.assertThrows(DatabaseException.class, () -> {
			employeeService.deleteEmployee(dependentId);
		});
		
		Mockito.verify(employeeRepository, Mockito.times(1)).deleteById(dependentId);
	}
	
	@Test
	public void addEmployeeShouldReturnEmployeeResponse() {
		
		EmployeeResponse employeeResponse = employeeService.addEmployee(employeeRequest);
		
		Assertions.assertNotNull(employeeResponse);
		
	}
}
