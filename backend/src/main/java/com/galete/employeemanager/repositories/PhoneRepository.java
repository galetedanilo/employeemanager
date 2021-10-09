package com.galete.employeemanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.galete.employeemanager.entities.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long>{

	@Transactional
	@Modifying
	@Query("DELETE FROM Phone phone WHERE phone.employee.id = :id")
	void deleteAllPhonesByEmployee(Long id);
}
