package com.galete.employeemanager.requests.phone;

import java.io.Serializable;

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
public class PhoneRequest implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@Enumerated(EnumType.STRING)
	private PhoneType phoneType;
	
	@NotBlank(message = "phone number is required")
	@Size(min = 10, max = 20, message = "number size should be between 10 and 20 ")
	private String phoneNumber;
	
}
