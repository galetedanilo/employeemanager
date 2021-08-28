package com.galete.employeemanager.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.galete.employeemanager.entities.Employee;
import com.galete.employeemanager.request.EmployeeRequest;
import com.galete.employeemanager.response.EmployeeResponse;

@Mapper
public interface EmployeeMapper {

	EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
	
	Employee employeeRequestToEmployee(EmployeeRequest request);
	
	EmployeeResponse employeeToEmployeeResponse(Employee entity);
}
