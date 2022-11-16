package com.example.demo.Service;

import com.example.demo.DTO_and_ENUM.UserDto;
import com.example.demo.Exceptions.UserException;
import com.example.demo.Model.User;

public interface UserService {

	
	public User saveUser(UserDto User)throws UserException;
	
	public User updateUser(User User) throws UserException;
	
	public String deleteUser(Integer id) throws UserException;
	
}
