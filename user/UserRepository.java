package com.example.Students.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findByUserName(String userName);

	User findByUserNameAndUserPassword(String userName, String userPassword);
	
	}
