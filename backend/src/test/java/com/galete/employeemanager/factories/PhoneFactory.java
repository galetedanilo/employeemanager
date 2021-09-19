package com.galete.employeemanager.factories;

import com.galete.employeemanager.entities.Phone;
import com.galete.employeemanager.entities.enums.PhoneType;
import com.galete.employeemanager.requests.PhoneRequest;

public class PhoneFactory {

	private static final Long ID = 1L;
	private static final PhoneType PHONE_TYPE = PhoneType.HOME;
	private static final String NUMBER = "12345678912345";
	
	public static Phone createPhone() {
		
		return Phone.builder()
				.id(ID)
				.type(PHONE_TYPE)
				.number(NUMBER)
				.build();
	}
	
	public static PhoneRequest createPhoneRequest() {
		
		return PhoneRequest.builder()
				.type(PHONE_TYPE)
				.number(NUMBER)
				.build();
	}
}
