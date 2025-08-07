package com.example.Students;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	@Query("SELECT s.studentId as studentId, s.age as age, s.studentName as studentName, "
		     + "s.mail as mail, s.phone as phone, s.dob as dob, s.gender as gender, s.sNo as sNo, "
		     + "a.streetName as streetName, a.doorNo as doorNo, a.city as city, a.state as state, a.country as country, a.pincode as pincode, "
		     + "q.courseId as courseId, q.courseName as courseName, q.universityName as universityName, q.cgpa as cgpa, q.yop as yop "
		     + "FROM Student s JOIN Address a ON a.studentId = s.studentId "
		     + "JOIN Qualification q ON q.studentId = s.studentId "
		     + "WHERE s.studentId = :id")
	List<StudentDTO> getStudentByQuery(@Param("id") int id);

	}