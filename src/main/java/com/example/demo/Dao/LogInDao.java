package com.example.demo.Dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.LogIn;

public interface LogInDao extends JpaRepository<LogIn, String>{
	
	public LogIn findByUsername(String username);
	
	public Optional<LogIn> findByUuid(String uuid);
	

}
