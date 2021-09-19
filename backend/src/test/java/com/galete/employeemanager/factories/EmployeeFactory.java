package com.galete.employeemanager.factories;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Collections;
import java.util.UUID;

import com.galete.employeemanager.entities.Employee;
import com.galete.employeemanager.requests.EmployeeRequest;

public class EmployeeFactory {
	
	private static final Long ID = 1L; 
	private static final String FIRST_NAME = "João";
    private static final String LAST_NAME = "Paulo";
    private static final String EMAIL = "joaopaulo@gmail.com";
    private static final String CPF = "369.333.878-79";
    private static final String JOB_TITLE = "FullStack";
    private static final LocalDate BIRTH_DATE = LocalDate.of(2010, 10, 1);
    private static final Instant CREATED_AND_UPDATED = Instant.now();
    private static final String IMAGE_URL = "https://bootdey.com/img/Content/avatar/avatar8.png";
    private static final String EMPLOYEE_CODE = UUID.randomUUID().toString();

	public static Employee createEmployee() {
		
		return Employee.builder()
				.id(ID)
				.firstName(FIRST_NAME)
				.lastName(LAST_NAME)
				.email(EMAIL)
				.cpf(CPF)
				.birthDate(BIRTH_DATE)
				.jobTitle(JOB_TITLE)
				.created(CREATED_AND_UPDATED)
				.updated(CREATED_AND_UPDATED)
				.imageUrl(IMAGE_URL)
				.phones(Collections.singletonList(PhoneFactory.createPhone()))
				.employeeCode(EMPLOYEE_CODE)
				.build();
	}
	
	public static EmployeeRequest createEmployeeRequest() {
		
		return EmployeeRequest.builder()
				.firstName(FIRST_NAME)
				.lastName(LAST_NAME)
				.email(EMAIL)
				.cpf(CPF)
				.birthDate("10-12-2010")
				.jobTitle(JOB_TITLE)
				.imageUrl(IMAGE_URL)
				.phones(Collections.singletonList(PhoneFactory.createPhoneRequest()))
				.build();
	}
}
