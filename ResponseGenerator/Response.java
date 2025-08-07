package com.example.Students.ResponseGenerator;

import java.time.LocalDateTime;

public class Response {
    private String status;
    private String message;
    private Object data;
    private LocalDateTime timestamp;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime localDateTime) {
		this.timestamp = LocalDateTime.now();
	}

    
}
