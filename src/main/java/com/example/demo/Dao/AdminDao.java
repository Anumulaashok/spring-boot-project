package com.example.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Model.Admin;


public interface AdminDao extends JpaRepository<Admin, Integer> {

	@Query(value = "Select a from Admin a where a.UserName=:name")
	public Admin findByUserName(@Param("name") String userName);
}
