package com.security.exercise_01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.exercise_01.models.UserPrinciples;
import com.security.exercise_01.models.Users;
import com.security.exercise_01.repository.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = repo.findByUsername(username); 
		
		if(user == null) {
			System.out.println("user not found!");
			throw new UsernameNotFoundException("user not found here!");
		}
		return new UserPrinciples(user);
	}
	
	
	
	
}
