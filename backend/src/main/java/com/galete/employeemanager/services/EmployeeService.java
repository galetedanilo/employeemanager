package com.galete.employeemanager.services;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

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
	
	private static final String USER_NOT_FOUND_MESSAGE = "User by id %s was not found";
	
	private final EmployeeRepository repository;
	
	private final EmployeeMapper mapper = EmployeeMapper.INSTANCE;
		
	public EmployeeResponse addEmployee(EmployeeRequest request) {
		
		Boolean userExists = repository.findByName(request.getName()).isPresent();
		
		if(userExists) {
			throw new UsernameExistsException("Email already taken");
		}
		
		Employee entity = mapper.employeeRequestToEmployee(request);
			
		entity.setEmployeeCode(UUID.randomUUID().toString());
		
		entity = repository.save(entity);
		
		return mapper.employeeToEmployeeResponse(entity);
	}
	
	public EmployeeResponse findEmployeeById(Long id) {
		Optional<Employee> optional = repository.findById(id);
		
		Employee entity = optional.orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, id)));
		
		return mapper.employeeToEmployeeResponse(entity);
	}
	
	public Page<EmployeeResponse> findAllEmployees(Pageable pageable) {
		return repository.findAll(pageable).map(x -> mapper.employeeToEmployeeResponse(x));
	}
	
	public EmployeeResponse updateEmployee(Long id, EmployeeRequest request) {
		
		try {
			Employee entity = repository.getById(id);
			
			entity = mapper.employeeRequestToEmployee(request);
			
			entity = repository.save(entity);
			
			return mapper.employeeToEmployeeResponse(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}		
	}
	
	public void deleteEmployee(Long id) {
		
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}

}
