package com.galete.employeemanager.services;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.galete.employeemanager.entities.Department;
import com.galete.employeemanager.mappers.DepartmentMapper;
import com.galete.employeemanager.repositories.DepartmentRepository;
import com.galete.employeemanager.request.DepartmentRequest;
import com.galete.employeemanager.response.DepartmentResponse;
import com.galete.employeemanager.services.exceptions.DatabaseException;
import com.galete.employeemanager.services.exceptions.ResourceNotFoundException;
import com.galete.employeemanager.services.exceptions.UniqueDatabaseException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentService implements Serializable {

	private static final long serialVersionUID = 1L;

	private final DepartmentRepository departmentRepository;
	
	private final DepartmentMapper departmentMapper = DepartmentMapper.INSTANCE;
	
	public DepartmentResponse addDepartment(DepartmentRequest departmentRequest) {
		
		Boolean departmentExists = departmentRepository.findByName(departmentRequest.getName()).isPresent();
		
		if(departmentExists) {
			throw new UniqueDatabaseException("Deparment with " + departmentRequest.getName() + " is already exist");
		}
		
		Department departmentEntity = departmentMapper.departmentRequestToDeparment(departmentRequest);
		
		departmentEntity.setCreated(Instant.now());
		departmentEntity.setUpdated(Instant.now());
		
		departmentEntity = departmentRepository.save(departmentEntity);
		
		return departmentMapper.departmentToDepartmentResponse(departmentEntity);
	}
	
	@Transactional(readOnly = true)
	public DepartmentResponse findDepartmentById(Long id) {
		Department departmentEntity = verifyIfDepartmentExists(id);
		
		return departmentMapper.departmentToDepartmentResponse(departmentEntity);
	}
	
	@Transactional(readOnly = true)
	public Page<DepartmentResponse> findAllDepartments(Pageable pageable) {
		Page<Department> departmentPage = departmentRepository.findAll(pageable);
		
		return departmentPage.map(obj -> departmentMapper.departmentToDepartmentResponse(obj));
	}
	
	@Transactional
	public DepartmentResponse updateDepartment(Long id, DepartmentRequest departmentRequest) {
		verifyIfDepartmentExists(id);
		
		Department departmentEntity = departmentMapper.departmentRequestToDeparment(departmentRequest);
		
		departmentEntity.setId(id);
		departmentEntity.setUpdated(Instant.now());
		
		departmentEntity = departmentRepository.save(departmentEntity);
		
		return departmentMapper.departmentToDepartmentResponse(departmentEntity);
	}
	
	public void deleteDepartment(Long id) {
		try {
			departmentRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Resource not found in database with id: " + id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}
	
	private Department verifyIfDepartmentExists(Long id) {
		return departmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(String.format("Department by id %s was not found", id)));
	}
}
