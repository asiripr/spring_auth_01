package com.security.exercise_01.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import com.security.exercise_01.models.Student;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class StudentController {
	public List<Student> students = new ArrayList<Student>(
			List.of(new Student(1, "shan", 73), new Student(2, "sakuni", 80)));

	@GetMapping("/students")
	public List<Student> getStudents() {
		return students;
	}
	
	@PostMapping("/students")
	public Student addStudent(@RequestBody Student student) {
		students.add(student);
		return student;
	}
	
	@GetMapping("/csrf-token")
	public CsrfToken getCsrfToken(HttpServletRequest request) {
		return (CsrfToken) request.getAttribute("_csrf");
	}
}
