package com.security.exercise_01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.exercise_01.models.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {
	Users findByUsername(String username);
	
	
}
