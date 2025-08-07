package com.example.Students.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Students.Entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{
	
	Address findByStudentId(int studentId);


}
