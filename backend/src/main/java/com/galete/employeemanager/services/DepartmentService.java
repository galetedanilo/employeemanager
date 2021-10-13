package com.galete.employeemanager.services;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.galete.employeemanager.entities.Department;
import com.galete.employeemanager.entities.projections.DepartmentProjection;
import com.galete.employeemanager.mappers.DepartmentMapper;
import com.galete.employeemanager.repositories.DepartmentRepository;
import com.galete.employeemanager.requests.department.DepartmentRequest;
import com.galete.employeemanager.responses.DepartmentResponse;
import com.galete.employeemanager.responses.mins.DepartmentMinResponse;
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
		
		verifyDepartmentByNameExists(departmentRequest.getName());

		Department departmentEntity = departmentMapper.departmentRequestToDeparment(departmentRequest);

		departmentEntity.setCreated(Instant.now());
		departmentEntity.setUpdated(Instant.now());

		departmentEntity = departmentRepository.save(departmentEntity);

		return departmentMapper.departmentToDepartmentResponse(departmentEntity);

	}

	@Transactional(readOnly = true)
	public DepartmentResponse findDepartmentById(Long id) {
		Optional<Department> departmentOptional = departmentRepository.findById(id);

		Department departmentEntity = departmentOptional
				.orElseThrow(() -> new ResourceNotFoundException("Department by id " + id + " was not found"));

		return departmentMapper.departmentToDepartmentResponse(departmentEntity);
	}

	@Transactional(readOnly = true)
	public Page<DepartmentResponse> findAllDepartments(Pageable pageable) {
		Page<Department> departmentPage = departmentRepository.findAll(pageable);

		return departmentPage.map(obj -> departmentMapper.departmentToDepartmentResponse(obj));
	}

	@Transactional(readOnly = true)
	public List<DepartmentMinResponse> listAllDepartments() {
		List<DepartmentProjection> departmentProjection = departmentRepository.listAllDepartments();

		return departmentProjection.stream()
				.map(obj -> departmentMapper.departmentProjectionToDepartmentMinResponse(obj))
				.collect(Collectors.toList());
	}

	@Transactional
	public DepartmentResponse updateDepartment(Long id, DepartmentRequest departmentRequest) {

		try {
			Department departmentEntity = departmentRepository.getById(id);

			departmentEntity = departmentMapper.departmentRequestToDeparment(departmentRequest);

			departmentEntity.setId(id);
			departmentEntity.setUpdated(Instant.now());

			departmentEntity = departmentRepository.save(departmentEntity);

			return departmentMapper.departmentToDepartmentResponse(departmentEntity);
		} catch (ConstraintViolationException e) {
			throw new ResourceNotFoundException("Resource not found in database with id: " + id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Resource not found in database with id: " + id);
		}
	}

	public void deleteDepartment(Long id) {
		try {
			departmentRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Resource not found in database with id: " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}
	
	private void verifyDepartmentByNameExists(String name) {
		boolean check = departmentRepository.findByName(name).isPresent();
		
		if(check){
			throw new UniqueDatabaseException("Deparment with name: " + name + " is already exist");
		}
	}
}
