package com.galete.employeemanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galete.employeemanager.entities.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{

}
