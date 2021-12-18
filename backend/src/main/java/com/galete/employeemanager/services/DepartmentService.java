package com.galete.employeemanager.services;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

		Instant createdAndUpdated = Instant.now();

		departmentEntity.setCreated(createdAndUpdated);
		departmentEntity.setUpdated(createdAndUpdated);

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

			Department departmentUpdateEntity = departmentMapper.departmentRequestToDeparment(departmentRequest);

			departmentUpdateEntity.setId(id);
			departmentUpdateEntity.setCreated(departmentEntity.getCreated());
			departmentUpdateEntity.setUpdated(Instant.now());

			departmentUpdateEntity = departmentRepository.save(departmentUpdateEntity);

			return departmentMapper.departmentToDepartmentResponse(departmentUpdateEntity);
			
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Resource not found in database with id: " + id);
		}
		//DoTo when update and unique: Exception to catch, but I don't know to fix this now.
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
		if(departmentRepository.findByName(name).isPresent()){
			throw new UniqueDatabaseException("Department with name: " + name + " is already exist");
		}
	}
}
