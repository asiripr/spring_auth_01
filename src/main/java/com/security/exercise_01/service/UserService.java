package com.security.exercise_01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.exercise_01.models.Users;
import com.security.exercise_01.repository.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo repo;
	
	public Users register(Users user) {
		return repo.save(user);
	}
}
