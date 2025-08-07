package com.example.Students;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QualificationRepository extends JpaRepository<Qualification, Integer>{
	
	Qualification findByStudentId(int studentId);

}
