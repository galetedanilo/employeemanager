package com.galete.employeemanager.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.galete.employeemanager.entities.Employee;
import com.galete.employeemanager.entities.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long>{
	
	@Query("SELECT phone FROM Phone phone WHERE phone = :phone AND phone.employee = :employee")
	Optional<Phone> selectPhoneByEmployee(Phone phone, Employee employee);
}
