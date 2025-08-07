package com.example.Students;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Students.dto.AddressDTO;

import jakarta.validation.Valid;
@Service
public class AddressService {
	
	@Autowired
    private AddressRepository repo;
	
	public List<Address> getAllAddress() {
        return repo.findAll();
    }
	
	public Optional<Address> getAddrById(int id){
		return repo.findById(id);
	}
	
	public Address addAddress(AddressDTO dto) {
		Address address = new Address();
		address.setsNo(dto.getsNo());
		address.setStudentId(dto.getStudentId());
		address.setDoorNo(dto.getDoorNo());
		address.setStreetName(dto.getStreetName());
		address.setCity(dto.getCity());
		address.setState(dto.getState());
		address.setCountry(dto.getCountry());
		address.setPincode(dto.getPincode());
    	return repo.save(address);
    }
	
	public Address updateAddress(@Valid AddressDTO dto) {
		   Optional<Address> existingAddress = repo.findById(dto.getsNo());
		           
		   if(existingAddress.isPresent()){
			existingAddress.get().setStudentId(dto.getStudentId());
			existingAddress.get().setDoorNo(dto.getDoorNo());
		    existingAddress.get().setStreetName(dto.getStreetName());
		    existingAddress.get().setCity(dto.getCity());
		    existingAddress.get().setState(dto.getState());
		    existingAddress.get().setCountry(dto.getCountry());
		    existingAddress.get().setPincode(dto.getPincode());
		    
		    
		    return repo.saveAndFlush(existingAddress.get());
		   }
		   return null;
		}
	 public String deleteAddress(int id) {
	        if (repo.existsById(id)) {
	            repo.deleteById(id);
	            return "Address with ID " + id + " deleted successfully.";
	        } else {
	            return "Address with ID " + id + " not found.";
	        }
	    }

}
