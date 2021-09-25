package com.galete.employeemanager.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.galete.employeemanager.entities.Employee;
import com.galete.employeemanager.requests.EmployeeRequest;
import com.galete.employeemanager.responses.EmployeeResponse;

@Mapper(uses = {PhoneMapper.class, DepartmentMinMapper.class})
public interface EmployeeMapper {

	EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

	@Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
	@Mapping(target = "employeeCode", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "created", ignore = true)
	@Mapping(target = "updated", ignore = true)
	Employee employeeRequestToEmployee(EmployeeRequest request);
	
	@Mapping(source = "id", target = "employeeId")
	EmployeeResponse employeeToEmployeeResponse(Employee entity);
}
