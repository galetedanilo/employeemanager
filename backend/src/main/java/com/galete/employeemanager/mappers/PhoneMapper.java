package com.galete.employeemanager.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.galete.employeemanager.entities.Phone;
import com.galete.employeemanager.requests.phone.PhoneRequest;
import com.galete.employeemanager.responses.PhoneResponse;

@Mapper
public interface PhoneMapper {

	PhoneMapper INSTANCE = Mappers.getMapper(PhoneMapper.class);
	
	@Mapping(target = "employee", ignore = true)
	Phone phoneRequestToPhone(PhoneRequest request);
	
	PhoneResponse phoneToPhoneResponse(Phone entity);
	
	List<Phone> phoneRequestListToPhoneList(List<PhoneRequest> request);
	
	List<PhoneResponse> phoneListToPhoneResponseList(List<Phone> entity);
}
