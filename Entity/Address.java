package com.example.Students.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "ADDR")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID")
    private int sNo;

    @Column(name = "student_id")
    private int studentId; 

    @Column(name = "Door_No")
    private String doorNo;

    @Column(name = "Street_Name")
    private String streetName;

    @Column(name = "City")
    private String city;

    @NotNull
    @Min(value = 100000, message = "Pincode must be 6 digits")
    @Max(value = 999999, message = "Pincode must be 6 digits")
    @Column(name = "Pincode")
    private int pincode;

    @Column(name = "State")
    private String state;

    @Column(name = "Country")
    private String country;



    public int getsNo() {
        return sNo;
    }

    public void setsNo(int sNo) {
        this.sNo = sNo;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getDoorNo() {
        return doorNo;
    }

    public void setDoorNo(String doorNo) {
        this.doorNo = doorNo;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
