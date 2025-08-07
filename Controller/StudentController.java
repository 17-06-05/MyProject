package com.example.Students.Controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.example.Students.Entity.Student;
import com.example.Students.ResponseGenerator.ResponseGenerator;
import com.example.Students.Service.StudentService;
import com.example.Students.dto.StudentDTO;
import com.example.Students.dto.StudentFullDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService service;

    @CrossOrigin(origins = "*")
    @GetMapping("/details/{id}")
    public ResponseEntity<?> getDetails(@PathVariable int id) {
        try {
            StudentFullDTO dto = service.getStudentWithAllDetails(id); // Address + Qualification only

            if (dto != null) {
                return ResponseGenerator.successResponse("Student full details fetched successfully", dto);
            } else {
                return ResponseGenerator.errorResponse("Student not found with ID: " + id);
            }

        } catch (Exception e) {
            return ResponseGenerator.errorResponse("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllStudents() {
        try {
            List<StudentFullDTO> allStudents = service.getAllStudentWithDetails(); // custom DTO list

            if (!allStudents.isEmpty()) {
                return ResponseGenerator.successResponse("Students fetched successfully", allStudents);
            } else {
                return ResponseGenerator.errorResponse("No students found");
            }

        } catch (Exception e) {
            return ResponseGenerator.errorResponse("An error occurred while fetching students: " + e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addStudent(@Valid @RequestBody StudentDTO studentDTO) {
        try {
            Student createdStudent = service.addStudent(studentDTO);

            if (createdStudent != null) {
                return ResponseGenerator.successResponse("Student added successfully", createdStudent);
            } else {
                return ResponseGenerator.errorResponse("Failed to add student");
            }
        } catch (Exception e) {
            return ResponseGenerator.errorResponse("An error occurred while adding student: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable int id) {
        try {
            Optional<Student> student = service.getByStudentId(id);

            if (student.isPresent()) {
                return ResponseGenerator.successResponse("Student found", student.get());
            } else {
                return ResponseGenerator.errorResponse("Student not found with ID: " + id);
            }

        } catch (NoSuchElementException e) {
            return ResponseGenerator.errorResponse("Error: No such student exists");
        } catch (Exception e) {
            return ResponseGenerator.errorResponse("Unexpected error occurred: " + e.getMessage());
        }
    }
    
    @GetMapping("/getByQuery/{id}")
    public ResponseEntity<?> getStudentByQuery(@PathVariable int id) {
        try {
            List<com.example.Students.dto.StudentDTOI> studentList = service.getStudentByQuery(id);

            if (!studentList.isEmpty()) {
                return ResponseGenerator.successResponse("Student found", studentList.get(0)); 
            } else {
                return ResponseGenerator.errorResponse("Student not found with ID: " + id);
            }

        } catch (NoSuchElementException e) {
            return ResponseGenerator.errorResponse("Error: No such student exists");
        } catch (Exception e) {
            return ResponseGenerator.errorResponse("Unexpected error occurred: " + e.getMessage());
        }
    }



    
    @PutMapping("/update")
    public ResponseEntity<?> updateStudent(@Valid @RequestBody StudentDTO requestDto) {
        try {
            Student updatedStudent = service.updateStudent(requestDto);

            if (updatedStudent != null) {
                return ResponseGenerator.successResponse("Student updated successfully", updatedStudent);
            } else {
                return ResponseGenerator.errorResponse("Student ID not found");
            }
        } catch (Exception e) {
            return ResponseGenerator.errorResponse("Error updating student: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable int id) {
        try {
            String result = service.deleteStudent(id);

            if (result.toLowerCase().contains("not found")) {
                return ResponseGenerator.errorResponse(result);
            }

            return ResponseGenerator.successResponse(result, null);
        } catch (Exception e) {
            return ResponseGenerator.errorResponse("An error occurred while deleting the student: " + e.getMessage());
        }
    }
}
