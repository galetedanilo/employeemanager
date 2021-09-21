package com.galete.employeemanager.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.galete.employeemanager.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Optional<Employee> findByEmail(String email);
	
	@Query(value = "SELECT DISTINCT employee FROM Employee employee JOIN FETCH employee.phones JOIN FETCH employee.department",
			countQuery = "SELECT COUNT(employee) FROM Employee employee")
	Page<Employee> findAllEmployees(Pageable pageable);
	
	@Query("SELECT DISTINCT employee FROM Employee employee JOIN FETCH employee.phones JOIN FETCH employee.department WHERE employee.id = :id")
	Optional<Employee> findEmployeeById(Long id);
}
