package com.example.Students;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.Students.ResponseGenerator.ResponseGenerator;
public class ExpHand {
		@ControllerAdvice
		public class ExceptionHandling {
		@ExceptionHandler(Exception.class)
		public ResponseEntity<?> handleException(Exception e){
			return ResponseGenerator.errorResponse(e.getMessage());
			
		}
		}

	}


