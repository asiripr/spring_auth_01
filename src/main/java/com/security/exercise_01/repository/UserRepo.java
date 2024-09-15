package com.security.exercise_01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.exercise_01.models.Users;

public interface UserRepo extends JpaRepository<Users, Integer> {

}
