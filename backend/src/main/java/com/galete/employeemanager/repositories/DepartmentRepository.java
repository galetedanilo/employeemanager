package com.galete.employeemanager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.galete.employeemanager.entities.Department;
import com.galete.employeemanager.entities.projections.DepartmentProjection;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{
	
	@Query("SELECT d.id AS id, d.name AS name FROM Department d")
	List<DepartmentProjection> listAllDepartments();
}
