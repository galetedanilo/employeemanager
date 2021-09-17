package com.galete.employeemanager.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.galete.employeemanager.entities.Department;
import com.galete.employeemanager.response.DepartmentMinResponse;

@Mapper
public interface DepartmentMinMapper {

	DepartmentMinMapper INSTANCE = Mappers.getMapper(DepartmentMinMapper.class);
	
	DepartmentMinResponse departmentToDepartmentMinResponse(Department entity);
}
