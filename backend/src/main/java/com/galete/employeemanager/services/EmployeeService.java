package com.galete.employeemanager.services;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.galete.employeemanager.entities.Employee;
import com.galete.employeemanager.mappers.EmployeeMapper;
import com.galete.employeemanager.repositories.EmployeeRepository;
import com.galete.employeemanager.repositories.PhoneRepository;
import com.galete.employeemanager.requests.EmployeeRequest;
import com.galete.employeemanager.responses.EmployeeResponse;
import com.galete.employeemanager.services.exceptions.DatabaseException;
import com.galete.employeemanager.services.exceptions.ResourceNotFoundException;
import com.galete.employeemanager.services.exceptions.UniqueDatabaseException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EmployeeService implements Serializable {

	private static final long serialVersionUID = 1L;

	private final EmployeeRepository employeeRepository;
	private final PhoneRepository phoneRepository;
	
	private final EmployeeMapper employeeMapper = EmployeeMapper.INSTANCE;

	@Transactional
	public EmployeeResponse addEmployee(EmployeeRequest employeeRequest) {

		Boolean userExists = employeeRepository.findByEmail(employeeRequest.getEmail()).isPresent();
		
		if (userExists) {
			throw new UniqueDatabaseException("User with " + employeeRequest.getEmail() + " is already exist");
		}

		Employee employeeEntity = employeeMapper.employeeRequestToEmployee(employeeRequest);

		employeeEntity.setEmployeeCode(UUID.randomUUID().toString());
		employeeEntity.setCreated(Instant.now());
		employeeEntity.setUpdated(Instant.now());
		
		employeeEntity = employeeRepository.save(employeeEntity);

		return employeeMapper.employeeToEmployeeResponse(employeeEntity);
	}

	@Transactional(readOnly = true)
	public EmployeeResponse findEmployeeById(Long id) {
		Employee employeeEntity = verifyIfEmployeeExists(id);

		return employeeMapper.employeeToEmployeeResponse(employeeEntity);
	}

	@Transactional(readOnly = true)
	public Page<EmployeeResponse> findAllEmployees(Pageable pageable) {
		Page<Employee> employeePage = employeeRepository.findAllEmployees(pageable);
				
		return employeePage.map(obj -> employeeMapper.employeeToEmployeeResponse(obj));
	}

	@Transactional
	public EmployeeResponse updateEmployee(Long id, EmployeeRequest employeeRequest) {

		verifyIfEmployeeExists(id);

		Employee employeeEntity = employeeMapper.employeeRequestToEmployee(employeeRequest);

		employeeEntity.setId(id);
		employeeEntity.setUpdated(Instant.now());

		employeeEntity = employeeRepository.save(employeeEntity);

		return employeeMapper.employeeToEmployeeResponse(employeeEntity);

	}

	public void deleteEmployee(Long id) {
		try {	
			phoneRepository.deleteAllPhonesByEmployee(id);
			
			employeeRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Resource found in database with id: " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}

	private Employee verifyIfEmployeeExists(Long id) {
		return employeeRepository.findEmployeeById(id)
				.orElseThrow(() -> new ResourceNotFoundException(String.format("Employee by id %s was not found", id)));
	}

}
