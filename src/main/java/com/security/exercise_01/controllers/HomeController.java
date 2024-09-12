package com.security.exercise_01.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class HomeController {
	@GetMapping("/")
	public String greet(HttpServletRequest request) {
		return "Asiri Pramodaya "+request.getRequestedSessionId();
		
	}

}
