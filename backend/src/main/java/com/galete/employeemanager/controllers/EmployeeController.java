package com.galete.employeemanager.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galete.employeemanager.request.EmployeeRequest;
import com.galete.employeemanager.response.EmployeeResponse;
import com.galete.employeemanager.services.EmployeeService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

	private final EmployeeService service;
	
	@GetMapping("/all")
	public ResponseEntity<Page<EmployeeResponse>> findAllEmployees(Pageable pageable) {
		
		Page<EmployeeResponse> page = service.findAllEmployees(pageable);
		
		page.map(x -> x.add(WebMvcLinkBuilder.linkTo(
								WebMvcLinkBuilder.methodOn(EmployeeController.class)
									.findEmployeeById(x.getEmployeeId())).withSelfRel()));
		
		return new ResponseEntity<>(page, HttpStatus.OK);
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<EmployeeResponse> findEmployeeById(@PathVariable("id") Long id) {
		
		EmployeeResponse response = service.findEmployeeById(id);
				
		response.add(WebMvcLinkBuilder.linkTo(
						WebMvcLinkBuilder.methodOn(EmployeeController.class)
							.findAllEmployees(PageRequest.of(0, 20))).withRel("Find All Employees"));
		
		response.add(WebMvcLinkBuilder.linkTo(
						WebMvcLinkBuilder.methodOn(EmployeeController.class)
							.deleteEmployee(id)).withRel("Delete Employee"));
		
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<EmployeeResponse> addEmployee(@Validated @RequestBody EmployeeRequest request) {
		
		EmployeeResponse response = service.addEmployee(request);
		
		response.add(WebMvcLinkBuilder.linkTo(
						WebMvcLinkBuilder.methodOn(EmployeeController.class)
							.findAllEmployees(PageRequest.of(0, 20))).withRel("Find All Employees"));
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable("id") Long id, @Validated @RequestBody EmployeeRequest request) {
		
		EmployeeResponse response = service.updateEmployee(id, request);
		
		response.add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(EmployeeController.class)
					.findAllEmployees(PageRequest.of(0, 20))).withRel("Find All Employees"));
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long id) {
		
		service.deleteEmployee(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
