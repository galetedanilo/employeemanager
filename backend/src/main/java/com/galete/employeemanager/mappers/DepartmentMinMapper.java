package com.galete.employeemanager.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.galete.employeemanager.entities.Department;
import com.galete.employeemanager.requests.mins.DepartmentMinRequest;
import com.galete.employeemanager.responses.mins.DepartmentMinResponse;

@Mapper
public interface DepartmentMinMapper {
	
	DepartmentMinMapper INSTANCE = Mappers.getMapper(DepartmentMinMapper.class);
	
	@Mapping(target = "name", ignore = true)
	@Mapping(target = "description", ignore = true)
	@Mapping(target = "created", ignore = true)
	@Mapping(target = "updated", ignore = true)
	@Mapping(target = "imageUrl", ignore = true)
	Department departmentMinRequestToDepartment(DepartmentMinRequest request);
	
	DepartmentMinResponse departmentToDepartmentMinResponse(Department entity);

}
