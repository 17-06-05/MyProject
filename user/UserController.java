package com.example.Students.user;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.example.Students.Security.JwtUtil;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
    private UserService service;
 
 @GetMapping("/getAllUser")
    public ResponseEntity<?> getAllUser() {
	 List<User> user = service.getAllUsers();
	 if (user != null && !user.isEmpty()) {
         return ResponseGenerator.successResponse("User fetched successfully", user);
     } else {
         return ResponseGenerator.errorResponse("No user found");
     }
 }
 
 @GetMapping("/{id}")
 public ResponseEntity<?> getUserById(@PathVariable String id){
	 Optional<User> user = service.getUserById(id);
	 if (user.isPresent()) {
         return ResponseGenerator.successResponse("User found", user.get());
     } else {
         return ResponseGenerator.errorResponse("User not found with ID: " + id);
     }

 }
 
 @PostMapping("/create")
 public ResponseEntity<?> addUser(@RequestBody User user) {
     try {
         User createdUser = service.addUser(user);

         if (createdUser != null) {
             return ResponseGenerator.successResponse("User added successfully", createdUser);
         } else {
             return ResponseGenerator.errorResponse("Failed to add user");
         }
     } catch (Exception e) {
         return ResponseGenerator.errorResponse("Unexpected error occurred: " + e.getMessage());
     }
 }

 
 @PutMapping("/update")
 	public ResponseEntity<?> updateUser(@RequestBody User request) {
	 		User updatedUser = service.updateUser(request);
	 		 if (updatedUser != null) {
	                return ResponseGenerator.successResponse("User updated successfully", updatedUser);
	            } else {
	                return ResponseGenerator.errorResponse("User not found");
	            }
	 	 }
 
 @DeleteMapping("/delete/{id}")
 	public ResponseEntity<?> deleteUser(@PathVariable String id) {
	           String result = service.deleteUser(id);

	            if (result.toLowerCase().contains("not found")) {
	                return ResponseGenerator.errorResponse(result);
	            }

	            return ResponseGenerator.successResponse(result, null);
	 
 }
 
 @PostMapping("/register")
 public ResponseEntity<?> userLogin(@RequestBody User user) {
     String result = service.login(user);

     if (result.equals("Sign-Up")) {
         return ResponseGenerator.successResponse(result, user);
     } else {
         return ResponseGenerator.errorResponse(result);
     }
 }
 
 @PostMapping("/loginDto")
 public ResponseEntity<?> loginUser(@RequestBody UserDTO user) {
     String result = service.userIdAndPassword(user);

     if (result.equals("Login Success")) {
         return ResponseGenerator.successResponse(result, user);
     } else {
         return ResponseGenerator.errorResponse(result);
     }
 }
 
 @Autowired
 private JwtUtil jwtUtil;

 @PostMapping("/loginId")
 public ResponseEntity<?> login(@RequestBody UserDTO loginRequest) {
         UserDTO user = service.loginRequest(loginRequest.getUserName(), loginRequest.getUserPassword());

         if (user != null) {
             String token = jwtUtil.generateToken(user.getUserName());

            return ResponseGenerator.successResponse("Login Success", token);
         } else {
             return ResponseGenerator.errorResponse("Invalid username or password");
         }

 }


 @PostMapping("/login")
	public ResponseEntity<?> loginDetails(@RequestBody User request){
		try {
			User response=service.getUserDetails(request.getUserName(),request.getUserPassword());
			if(response!=null) {
				String token=jwtUtil.generateToken(request.getUserName());
				Map<String,Object> result=new HashMap<String, Object>();
				result.put("token", token);
				result.put("user",response);
			return ResponseGenerator.successResponse("login successfull", result);
			}else {
				return ResponseGenerator.errorResponse("login failed");
			}
		}catch(Exception e)
		{
			
			return ResponseGenerator.errorResponse(e.getMessage());
		}
 }
}
