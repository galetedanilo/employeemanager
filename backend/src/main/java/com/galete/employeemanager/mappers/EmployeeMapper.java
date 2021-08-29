package com.galete.employeemanager.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.galete.employeemanager.entities.Employee;
import com.galete.employeemanager.request.EmployeeRequest;
import com.galete.employeemanager.response.EmployeeResponse;

@Mapper
public interface EmployeeMapper {

	EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

	@Mapping(target = "employeeCode", ignore = true)
	@Mapping(target = "id", ignore = true)
	Employee employeeRequestToEmployee(EmployeeRequest request);
	
	@Mapping(source = "id", target = "employeeId")
	EmployeeResponse employeeToEmployeeResponse(Employee entity);
}
