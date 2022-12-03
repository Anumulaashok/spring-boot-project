package com.example.demo.ServiceImpl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO_and_ENUM.AdminDto;
import com.example.demo.DTO_and_ENUM.UserDto;
import com.example.demo.DTO_and_ENUM.UserLoginDto;
import com.example.demo.Dao.AdminDao;
import com.example.demo.Dao.LogInDao;
import com.example.demo.Dao.UserDao;
import com.example.demo.Exceptions.AdminException;
import com.example.demo.Model.Admin;
import com.example.demo.Model.LogIn;
import com.example.demo.Model.User;
import com.example.demo.Service.AdminService;

import net.bytebuddy.utility.RandomString;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminDao admindao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private LogInDao logInDao;

	@Override
	public Admin saveAdmin(AdminDto admin) throws AdminException {
		
		User user= userDao.findByUserName(admin.getUserName());
		
		Admin ad= admindao.findByUserName(admin.getUserName());
		if(ad!=null||user!=null)
			throw new AdminException("User Already Exist With The UserName ->"+admin.getUserName());
		
			Admin admin1= new Admin(admin.getEmail(),admin.getName(),admin.getUserName().trim(), admin.getPassword().trim());
			
			return admindao.save(admin1);
		
	}

	
	@Override
	public Admin updateAdminUserName(Admin admin) throws AdminException {
	
		Admin admin1=admindao.findById(admin.getId()).orElseThrow(()-> new AdminException("Admin Not Found"));
		
		
		return admindao.save(admin);		
	}

	
	@Override
	public String deleteAdmin(Integer id) throws AdminException {
		
		Admin admin1=admindao.findById(id).orElseThrow(()-> new AdminException("Admin Not Found"));
		
		admindao.delete(admin1);
		
		
		return "Admin Deleted ";
	}


	@Override
	public Admin updateAdminPassword(String userName, String oldPassword, String newPassword) throws AdminException {
		
		Admin ad= admindao.findByUserName(userName);
		
		
		Admin admin1=admindao.findById(ad.getId()).orElseThrow(()-> new AdminException("Admin Not Found"));
		
		if((ad.getUserName()).equals(userName)) {
			throw new AdminException("User name already Exist Please try with other");
		}
		if(!(ad.getPassword()).equals(oldPassword)) {
			throw new AdminException("Please Enter correct Password ");
		}
		admin1.setPassword(newPassword);
		
		return admindao.save(admin1);
	}


	@Override
	public UserLoginDto adminLogIn(UserDto admindto) throws AdminException {
		
		Admin admin= admindao.findByUserName(admindto.getUserName());
		
		if(admin==null) {
			throw new AdminException("User not Exit");
		}
		LogIn login= new LogIn(admin.getUserName(), admin.getPassword(), RandomString.make(4), LocalDate.now());
		
		LogIn logindetails=  logInDao.save(login);
				UserLoginDto dto=new UserLoginDto(logindetails.getUsername(), logindetails.getUuid());
		return dto;
	}


	@Override
	public String adminLogOut(String uuid) throws AdminException {
		LogIn login=logInDao.findByUuid(uuid).orElseThrow(()-> new AdminException("Check Again"));
		
		logInDao.delete(login);
		
		return "LogOut Successfull";
	}

}
