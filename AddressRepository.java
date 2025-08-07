package com.example.Students;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer>{
	
	Address findByStudentId(int studentId);


}
