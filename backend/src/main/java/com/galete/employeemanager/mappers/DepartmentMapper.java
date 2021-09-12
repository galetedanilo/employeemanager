package com.galete.employeemanager.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.galete.employeemanager.entities.Department;
import com.galete.employeemanager.request.DepartmentRequest;
import com.galete.employeemanager.response.DepartmentResponse;

@Mapper
public interface DepartmentMapper {

	DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "created", ignore = true)
	@Mapping(target = "updated", ignore = true)
	Department departmentRequestToDeparment(DepartmentRequest request);
	
	@Mapping(source = "id", target = "departmentId")
	DepartmentResponse departmentToDepartmentResponse(Department entity);
}
