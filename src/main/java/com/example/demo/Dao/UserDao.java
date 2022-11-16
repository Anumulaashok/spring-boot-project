package com.example.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer>{

//	public User findByUserName(String username);
	public User findByUserName(String userName);
}
