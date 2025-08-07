
package com.example.Students.dto;


import com.example.Students.Entity.Address;
import com.example.Students.Entity.Qualification;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class StudentDTO {
	
	
	private int studentId;
	
	private int sNo;

	@NotEmpty(message= "Name is required")
    @Size(min=2 , max=100 , message="Name length is more than 3 character")
    private String studentName;

    private String dob;

    private int age;

    private String gender;

    @Size(max=10 , message="Enter correct Phone Number")
    private String phone;

    private String mail;
    
    private Address address;
    private Qualification qualification;
    
    @Valid
    private AddressDTO address1;

    @Valid
    private QualificationDTO qualification2;


    
    public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Qualification getQualification() {
		return qualification;
	}
	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}
	public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getDob() {
        return dob;
    }
    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getsNo() {
		return sNo;
	}
	public void setsNo(int sNo) {
		this.sNo = sNo;
	}
}
