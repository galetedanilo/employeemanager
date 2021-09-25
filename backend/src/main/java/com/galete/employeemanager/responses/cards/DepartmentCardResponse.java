package com.galete.employeemanager.responses.cards;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentCardResponse {

	private Long departmentId;
	private String name;
	private String imageUrl;
}
