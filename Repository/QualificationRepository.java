package com.example.Students.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Students.Entity.Qualification;

public interface QualificationRepository extends JpaRepository<Qualification, Integer>{
	
	Qualification findByStudentId(int studentId);

}
