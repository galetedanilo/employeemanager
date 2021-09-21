package com.galete.employeemanager.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.galete.employeemanager.entities.Department;
import com.galete.employeemanager.requests.cards.DepartmentCardRequest;
import com.galete.employeemanager.responses.cards.DepartmentCardResponse;

@Mapper
public interface DepartmentCardMapper {

	DepartmentCardMapper INSTANCE = Mappers.getMapper(DepartmentCardMapper.class);

	@Mapping(source = "id", target = "departmentId")
	DepartmentCardResponse departmentToDepartmentCardMapper(Department entity);
	
	@Mapping(target = "name", ignore = true)
	@Mapping(target = "description", ignore = true)
	@Mapping(target = "created", ignore = true)
	@Mapping(target = "updated", ignore = true)
	Department departmentCardRequestToDepartment(DepartmentCardRequest request);
}
