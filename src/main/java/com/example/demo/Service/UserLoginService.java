package com.example.demo.Service;

import com.example.demo.DTO_and_ENUM.UserLoginDto;
import com.example.demo.Exceptions.UserException;
import com.example.demo.Model.LogIn;
public interface UserLoginService {
	public UserLoginDto userLogin(LogIn user) throws  UserException;
	public String LogOut(String key) throws UserException;
}
