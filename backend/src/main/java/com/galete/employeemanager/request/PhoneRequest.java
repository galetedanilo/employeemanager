package com.galete.employeemanager.request;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.galete.employeemanager.entities.enums.PhoneType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneRequest {

	private Long id;
	
	@Enumerated(EnumType.STRING)
	private PhoneType type;
	
	@NotBlank(message = "number is required")
	@Size(min = 10, max = 15, message = "number size should be between 10 and 15 ")
	private String number;
}
