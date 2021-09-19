package com.galete.employeemanager.responses;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.galete.employeemanager.entities.enums.PhoneType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneResponse {

	private Long id;
	
	@Enumerated(EnumType.STRING)
	private PhoneType type;
	
	private String number;
}
