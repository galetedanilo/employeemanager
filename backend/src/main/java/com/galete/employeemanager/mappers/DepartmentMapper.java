package com.galete.employeemanager.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.galete.employeemanager.entities.Department;
import com.galete.employeemanager.entities.projections.DepartmentProjection;
import com.galete.employeemanager.requests.DepartmentRequest;
import com.galete.employeemanager.responses.DepartmentResponse;
import com.galete.employeemanager.responses.mins.DepartmentMinResponse;

@Mapper
public interface DepartmentMapper {

	DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "created", ignore = true)
	@Mapping(target = "updated", ignore = true)
	Department departmentRequestToDeparment(DepartmentRequest request);
	
	@Mapping(source = "id", target = "departmentId")
	DepartmentResponse departmentToDepartmentResponse(Department entity);
	
	DepartmentMinResponse departmentToDepartmentMinResponse(Department entity);
	
	DepartmentMinResponse departmentProjectionToDepartmentMinResponse(DepartmentProjection projection);
}
