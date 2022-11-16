package com.example.demo.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO_and_ENUM.AdminDto;
import com.example.demo.Dao.AdminDao;
import com.example.demo.Exceptions.AdminException;
import com.example.demo.Model.Admin;
import com.example.demo.Service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminDao admindao;

	@Override
	public Admin saveAdmin(AdminDto admin) throws AdminException {
		
		
		
		Admin ad= admindao.findByUserName(admin.getUserName());
		if(ad!=null)
			throw new AdminException("User Already Exist With The UserName ->"+admin.getUserName());
		
			Admin admin1= new Admin(admin.getUserName().trim(), admin.getPassword().trim());
			
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
		Admin admin= new Admin(userName.trim(), newPassword.trim());
		
		return admindao.save(admin);
	}

}