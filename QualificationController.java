package com.example.Students;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Students.ResponseGenerator.ResponseGenerator;
import com.example.Students.dto.QualificationDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/qualification")


public class QualificationController {
	

		 @Autowired
		    private QualificationService service;
		 
		 @GetMapping("/getAll")
		    public ResponseEntity<?> getAllQualifDetails() {
			 List<Qualification> QualifDetails = service.getAllQualifDetails();
			 if (QualifDetails != null && !QualifDetails.isEmpty()) {
	             return ResponseGenerator.successResponse("Qualifications fetched successfully", QualifDetails);
	         } else {
	             return ResponseGenerator.errorResponse("No Qualification found");
	         }
		 }
		 
		 @GetMapping("/{id}")
		 public ResponseEntity<?> getQualifById(@PathVariable int id){
			 Optional<Qualification> QualifDetails = service.getQualifById(id);
			 if (QualifDetails.isPresent()) {
	             return ResponseGenerator.successResponse("Qualification found", QualifDetails.get());
	         } else {
	             return ResponseGenerator.errorResponse("Qualification not found with ID: " + id);
	         }

		 }
		 
		 @PostMapping("/create")
		    public ResponseEntity<?> addQuaif(@Valid @RequestBody QualificationDTO qualifDTO) {
		            Qualification createdQualification = service.addQualif(qualifDTO);

		            if (createdQualification != null) {
		                return ResponseGenerator.successResponse("Qualification added successfully", createdQualification);
		            } else {
		                return ResponseGenerator.errorResponse("Failed to add Qualification");
		            }
		        	    }
		 
		 @PutMapping("/update")
		 	public ResponseEntity<?> updateQualif(@Valid @RequestBody QualificationDTO qualifDTO) {
			 		Qualification updatedQualif = service.updateQualif(qualifDTO);
			 		 if (updatedQualif != null) {
			                return ResponseGenerator.successResponse("Qualification updated successfully", updatedQualif);
			            } else {
			                return ResponseGenerator.errorResponse("Qualification ID not found");
			            }
			 	 }
		 
		 @DeleteMapping("/delete/{id}")
		 	public ResponseEntity<?> deleteQualif(@PathVariable int id) {
			           String result = service.deleteQualif(id);

			            if (result.toLowerCase().contains("not found")) {
			                return ResponseGenerator.errorResponse(result);
			            }

			            return ResponseGenerator.successResponse(result, null);
			 
		 }
		 
		 
	}
