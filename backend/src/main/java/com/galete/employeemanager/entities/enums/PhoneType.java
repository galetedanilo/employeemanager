package com.galete.employeemanager.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PhoneType {
	
	HOME("home"),
	MOBILE("mobile"),
	COMMERCIAL("commercial");
	
	private final String description;
}
