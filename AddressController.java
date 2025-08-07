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
import com.example.Students.dto.AddressDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	 @Autowired
	    private AddressService service;
	 
	 @GetMapping("/getAll")
	    public ResponseEntity<?> getAllAddress() {
		 List<Address> address = service.getAllAddress();
		 if (address != null && !address.isEmpty()) {
             return ResponseGenerator.successResponse("Address fetched successfully", address);
         } else {
             return ResponseGenerator.errorResponse("No address found");
         }
	 }
	 
	 @GetMapping("/{id}")
	 public ResponseEntity<?> getAddrById(@PathVariable int id){
		 Optional<Address> address = service.getAddrById(id);
		 if (address.isPresent()) {
             return ResponseGenerator.successResponse("Address found", address.get());
         } else {
             return ResponseGenerator.errorResponse("Address not found with ID: " + id);
         }

	 }
	 
	 @PostMapping("/add")
	    public ResponseEntity<?> addAddress(@Valid @RequestBody AddressDTO addressDTO) {
	            Address createdAddress = service.addAddress(addressDTO);

	            if (createdAddress != null) {
	                return ResponseGenerator.successResponse("Address added successfully", createdAddress);
	            } else {
	                return ResponseGenerator.errorResponse("Failed to add address");
	            }
	        	    }
	 
	 @PutMapping("/update")
	 	public ResponseEntity<?> updateAddress(@Valid @RequestBody AddressDTO request) {
		 		Address updatedAddress = service.updateAddress(request);
		 		 if (updatedAddress != null) {
		                return ResponseGenerator.successResponse("Address updated successfully", updatedAddress);
		            } else {
		                return ResponseGenerator.errorResponse("Address ID not found");
		            }
		 	 }
	 
	 @DeleteMapping("/delete/{id}")
	 	public ResponseEntity<?> deleteAddress(@PathVariable int id) {
		           String result = service.deleteAddress(id);

		            if (result.toLowerCase().contains("not found")) {
		                return ResponseGenerator.errorResponse(result);
		            }

		            return ResponseGenerator.successResponse(result, null);
		 
	 }
	 
	 
}
