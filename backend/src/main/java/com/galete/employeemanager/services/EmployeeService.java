package com.galete.employeemanager.services;

import java.io.Serializable;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.galete.employeemanager.entities.Department;
import com.galete.employeemanager.entities.Employee;
import com.galete.employeemanager.entities.Phone;
import com.galete.employeemanager.mappers.EmployeeMapper;
import com.galete.employeemanager.repositories.EmployeeRepository;
import com.galete.employeemanager.repositories.PhoneRepository;
import com.galete.employeemanager.requests.employee.EmployeeRequest;
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
		
		verifyEmployeeByEmailExists(employeeRequest.getEmail());
		
		try {
			Employee employeeEntity = employeeMapper.employeeRequestToEmployee(employeeRequest);
			Department departmentEntity = new Department();
			
			departmentEntity.setId(employeeRequest.getDepartment().getId());
			departmentEntity.setName(employeeRequest.getDepartment().getName());
			
			employeeEntity.setEmployeeCode(UUID.randomUUID().toString());
			employeeEntity.setCreated(Instant.now());
			employeeEntity.setUpdated(Instant.now());
			employeeEntity.setDepartment(departmentEntity);
						
			for(Phone phone : employeeEntity.getPhones()){
				phone.setId(null);
				phone.setEmployee(employeeEntity);
			}
			
			employeeEntity = employeeRepository.save(employeeEntity);
			
			return employeeMapper.employeeToEmployeeResponse(employeeEntity);
		} catch (DataIntegrityViolationException e) {
			throw new UniqueDatabaseException("User with " + employeeRequest.getEmail() + " is already exist");
		}		
	}

	@Transactional(readOnly = true)
	public EmployeeResponse findEmployeeById(Long id) {
		Optional<Employee> optional = employeeRepository.findEmployeeById(id);
		
		Employee employeeEntity = optional.orElseThrow(() -> new ResourceNotFoundException(String.format("Employee by id %s was not found", id)));

		return employeeMapper.employeeToEmployeeResponse(employeeEntity);
	}

	@Transactional(readOnly = true)
	public Page<EmployeeResponse> findAllEmployees(Pageable pageable) {
		Page<Employee> employeePage = employeeRepository.findAllEmployees(pageable);
				
		return employeePage.map(obj -> employeeMapper.employeeToEmployeeResponse(obj));
	}

	@Transactional
	public EmployeeResponse updateEmployee(Long id, EmployeeRequest employeeRequest) {

		try {
			Employee employeeEntity = employeeRepository.getById(id);
			
			Department departmentEntity = new Department();
			
			employeeEntity = employeeMapper.employeeRequestToEmployee(employeeRequest);

			employeeEntity.setId(id);
			employeeEntity.setUpdated(Instant.now());
			employeeEntity.setDepartment(departmentEntity);
			
			for(Phone phone : employeeEntity.getPhones()) {
				
				if(phone.getId() != null) {
					phoneRepository.selectPhoneByEmployee(phone, employeeEntity)
							.orElseThrow(() -> new ResourceNotFoundException("This phone number don't pertence this employee"));
				}
				
				phone.setEmployee(employeeEntity);
				phoneRepository.save(phone);
			}

			employeeEntity = employeeRepository.save(employeeEntity);

			return employeeMapper.employeeToEmployeeResponse(employeeEntity);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Resource not found in database with id: " + id);
		}
	}

	public void deleteEmployee(Long id) {
		try {	
			employeeRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Resource found in database with id: " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}
	
	private void verifyEmployeeByEmailExists(String email) {
		boolean check = employeeRepository.findByEmail(email).isPresent();
		
		if(check) {
			throw new UniqueDatabaseException("User with email: " + email + " is already exist");
		}
	}

}
