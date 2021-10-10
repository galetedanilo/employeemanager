package com.galete.employeemanager.responses;

import java.io.Serializable;

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
public class PhoneResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@Enumerated(EnumType.STRING)
	private PhoneType phoneType;
	
	private String phoneNumber;

}
