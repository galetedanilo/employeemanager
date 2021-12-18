package com.galete.employeemanager.factories;

import com.galete.employeemanager.entities.Phone;
import com.galete.employeemanager.entities.enums.PhoneType;
import com.galete.employeemanager.requests.phone.PhoneRequest;

public class PhoneFactory {

	private static final Long ID = 1L;
	private static final PhoneType PHONE_TYPE = PhoneType.HOME;
	private static final String NUMBER = "+55 - (19) 97857-7845";
	
	public static Phone createPhone() {
		
		return Phone.builder()
				.id(ID)
				.phoneType(PHONE_TYPE)
				.phoneNumber(NUMBER)
				.build();
	}
	
	public static PhoneRequest createPhoneRequest() {
		
		return PhoneRequest.builder()
				.phoneType(PHONE_TYPE)
				.phoneNumber(NUMBER)
				.build();
	}
}
