package com.example.Students.ResponseGenerator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class ResponseGenerator {
	
    public static ResponseEntity<Response> successResponse(String message, Object data) { 		
        Response response = new Response();
        response.setData(data);
        response.setMessage(message);
        response.setStatus("Success");
        response.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
    public static ResponseEntity<Response> errorResponse(String message) {
        Response response = new Response();
        response.setData(null);
        response.setMessage(message);
        response.setStatus("Error");
        response.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
