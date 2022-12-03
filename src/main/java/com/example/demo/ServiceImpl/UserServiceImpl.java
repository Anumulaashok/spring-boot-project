package com.example.demo.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO_and_ENUM.AdminDto;
import com.example.demo.Dao.AdminDao;
import com.example.demo.Dao.LogInDao;
import com.example.demo.Dao.UserDao;
import com.example.demo.Exceptions.UserException;
import com.example.demo.Model.Admin;
import com.example.demo.Model.Cart;
import com.example.demo.Model.LogIn;
import com.example.demo.Model.User;
import com.example.demo.Service.UserLoginService;
import com.example.demo.Service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userdao;

	@Autowired
	private UserLoginService loginService;
	
	

	@Autowired
	private LogInDao dao;
	
	@Autowired
	private AdminDao adminDao;
	
	@Override
	public User saveUser(AdminDto User) throws UserException {

		
		User ad= userdao.findByUserName(User.getUserName());
		Admin admin= adminDao.findByUserName(User.getUserName());
		
		if(ad!=null||admin!=null)
			throw new UserException("User AlreadyExit With The UserName ->"+User.getUserName());
			Cart c1= new Cart();
			User user1= new User(User.getUserName().trim(), User.getPassword().trim());
			user1.setName(User.getName());
			user1.setEmail(User.getEmail());
			user1.setCart(c1);
			
			return userdao.save(user1);
		
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
	public String deleteUser(String uuid) throws UserException {
			
		LogIn user1=dao.findByUuid(uuid).orElseThrow(()-> new UserException("User Not found"));
		try {
		String i= loginService.LogOut(uuid);
		User u=userdao.findByUserName(user1.getUsername());
		userdao.delete(u);
		
		}catch (Exception e) {
			throw new UserException(e.getMessage());
			
		}		
		
		return "Admin Deleted ";
	}

	@Override
	public List<User> getAllUsers() {
		return userdao.findAll();
		
	}
	
	
	
	
}
