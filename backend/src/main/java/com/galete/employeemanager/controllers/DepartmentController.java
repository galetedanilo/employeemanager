package com.galete.employeemanager.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galete.employeemanager.requests.department.DepartmentRequest;
import com.galete.employeemanager.responses.DepartmentResponse;
import com.galete.employeemanager.responses.mins.DepartmentMinResponse;
import com.galete.employeemanager.services.DepartmentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/departments")
@AllArgsConstructor
public class DepartmentController {

	private final DepartmentService departmentService;
	
	@GetMapping
	public ResponseEntity<Page<DepartmentResponse>> findAllDepartments(Pageable pageable) {
		Page<DepartmentResponse> response = departmentService.findAllDepartments(pageable);
		
		response.forEach(x -> {

			x.add(WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(DepartmentController.class).findDepartmentById(x.getDepartmentId()))
					.withSelfRel());
			
			x.add(WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(DepartmentController.class).deleteDepartment(x.getDepartmentId()))
					.withRel("Delete Department"));
		});
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DepartmentResponse> findDepartmentById(@PathVariable Long id) {
		DepartmentResponse response = departmentService.findDepartmentById(id);
		
		response.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(DepartmentController.class).findAllDepartments(PageRequest.of(0, 20)))
				.withRel("Find All Departments"));
		
		response.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(DepartmentController.class).deleteDepartment(id))
				.withRel("Delete Department"));
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<DepartmentMinResponse>> listAllDepartments() {
		List<DepartmentMinResponse> response = departmentService.listAllDepartments();
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<DepartmentResponse> addDepartment(@Valid @RequestBody DepartmentRequest request) {
		DepartmentResponse response = departmentService.addDepartment(request);
		
		response.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(DepartmentController.class).findAllDepartments(PageRequest.of(0, 20)))
				.withRel("Find All Departments"));
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<DepartmentResponse> updateDepartment(@PathVariable Long id, @Valid @RequestBody DepartmentRequest request) {
		DepartmentResponse response = departmentService.updateDepartment(id, request);
		
		response.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(DepartmentController.class).findAllDepartments(PageRequest.of(0, 20)))
				.withRel("Find All Departments"));
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
		departmentService.deleteDepartment(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
