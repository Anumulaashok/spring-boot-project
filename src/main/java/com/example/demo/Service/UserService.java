package com.example.demo.Service;

import java.util.List;

import com.example.demo.DTO_and_ENUM.AdminDto;
import com.example.demo.Exceptions.UserException;
import com.example.demo.Model.User;

public interface UserService {

	
	public User saveUser(AdminDto User)throws UserException;
	
	public User updateUser(User User) throws UserException;
	
	public String deleteUser(String id) throws UserException;
	
	public List<User> getAllUsers();
}
