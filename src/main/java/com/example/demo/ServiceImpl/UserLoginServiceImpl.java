package com.example.demo.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO_and_ENUM.UserLoginDto;
import com.example.demo.Dao.LogInDao;
import com.example.demo.Dao.UserDao;
import com.example.demo.Exceptions.UserException;
import com.example.demo.Model.LogIn;
import com.example.demo.Model.User;
import com.example.demo.Service.UserLoginService;

import net.bytebuddy.utility.RandomString;

@Service
public class UserLoginServiceImpl implements UserLoginService{


	@Autowired
	private UserDao signup;
	
	@Autowired
	private LogInDao login;
	
	@Override
	public UserLoginDto userLogin(LogIn user1)  throws UserException{
		
		User user = signup.findByUserName(user1.getUsername());
		
		if(user==null) {
		
			throw new UserException("Sig Up 1st");
		
		}
	
		if(!user1.getPassword().equals(user.getPassword())) {
		
			throw new UserException("Please Enter Correct password");
		
		}
		
		LogIn ul=login.findByUsername(user1.getUsername());
		
		if(ul!=null) {
		
			throw new UserException("User Alredy loged in");
		
		}
		
		user1.setUuid(RandomString.make(4));
		
		LogIn us=login.save(user1);
		UserLoginDto usd=new UserLoginDto(user.getUserName(), user1.getUuid());
	
		return usd;
	}

	@Override
	public String LogOut(String key) throws UserException {
	
		
	LogIn user=	login.findByUuid(key).orElseThrow(()-> new UserException("User Not logeed in"));
	
	login.delete(user);
		
		return "LogOut successful";
	}

	
	


}
