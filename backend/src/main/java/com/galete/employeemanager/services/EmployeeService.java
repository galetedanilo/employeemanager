package com.galete.employeemanager.services;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.galete.employeemanager.entities.Employee;
import com.galete.employeemanager.mappers.EmployeeMapper;
import com.galete.employeemanager.repositories.EmployeeRepository;
import com.galete.employeemanager.request.EmployeeRequest;
import com.galete.employeemanager.response.EmployeeResponse;
import com.galete.employeemanager.services.exceptions.DatabaseException;
import com.galete.employeemanager.services.exceptions.ResourceNotFoundException;
import com.galete.employeemanager.services.exceptions.UserNotFoundException;
import com.galete.employeemanager.services.exceptions.UsernameExistsException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EmployeeService implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String USER_NOT_FOUND_MESSAGE = "Employee by id %s was not found";

	private final EmployeeRepository employeeRepository;

	private final EmployeeMapper employeeMapper = EmployeeMapper.INSTANCE;

	public EmployeeResponse addEmployee(EmployeeRequest employeeRequest) {

		Boolean userExists = employeeRepository.findByName(employeeRequest.getName()).isPresent();

		if (userExists) {
			throw new UsernameExistsException("Email already taken");
		}

		Employee employeeEntity = employeeMapper.employeeRequestToEmployee(employeeRequest);

		employeeEntity.setEmployeeCode(UUID.randomUUID().toString());

		employeeEntity = employeeRepository.save(employeeEntity);

		return employeeMapper.employeeToEmployeeResponse(employeeEntity);
	}

	public EmployeeResponse findEmployeeById(Long id) {
		Employee employeeEntity = verifyIfEmployeeExists(id);

		return employeeMapper.employeeToEmployeeResponse(employeeEntity);
	}

	public Page<EmployeeResponse> findAllEmployees(Pageable pageable) {
		Page<Employee> employeePage = employeeRepository.findAll(pageable);
				
		return employeePage.map(obj -> employeeMapper.employeeToEmployeeResponse(obj));
	}

	public EmployeeResponse updateEmployee(Long id, EmployeeRequest employeeRequest) {

		verifyIfEmployeeExists(id);

		Employee employeeEntity = employeeMapper.employeeRequestToEmployee(employeeRequest);

		employeeEntity.setId(id);

		employeeEntity = employeeRepository.save(employeeEntity);

		return employeeMapper.employeeToEmployeeResponse(employeeEntity);

	}

	public void deleteEmployee(Long id) {
		try {
			employeeRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}

	private Employee verifyIfEmployeeExists(Long id) {
		return employeeRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, id)));
	}

}
