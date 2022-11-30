package com.example.demo.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO_and_ENUM.UserDto;
import com.example.demo.Dao.UserDao;
import com.example.demo.Exceptions.UserException;
import com.example.demo.Model.User;
import com.example.demo.Service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userdao;

	@Override
	public User saveUser(UserDto User) throws UserException {

		
		User ad= userdao.findByUserName(User.getUserName());
		if(ad!=null)
			throw new UserException("User AlreadyExit With The UserName ->"+User.getUserName());
		
			User User1= new User(User.getUserName().trim(), User.getPassword().trim());
			
			return userdao.save(User1);
		
	}

	@Override
	public User updateUser(User user) throws UserException {
		User user1=userdao.findById(user.getId()).orElseThrow(()-> new UserException("user Not Found"));
		
		if((user1.getUserName().trim()).equals(user1.getUserName().trim())) {
			throw new UserException("User name already Exit Please try with other");
		}
		
		return userdao.save(user);		
	}

	@Override
	public String deleteUser(Integer id) throws UserException {
		
		User user1=userdao.findById(id).orElseThrow(()-> new UserException("Admin Not Found"));
		
		userdao.delete(user1);
		
		
		return "Admin Deleted ";
	}
	
	
	
	
}
