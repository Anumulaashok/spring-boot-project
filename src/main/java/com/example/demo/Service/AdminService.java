package com.example.demo.Service;

import com.example.demo.DTO_and_ENUM.AdminDto;
import com.example.demo.Exceptions.AdminException;
import com.example.demo.Model.Admin;
import com.example.demo.Model.LogIn;

public interface AdminService {
	
	public Admin saveAdmin(AdminDto admin)throws AdminException;
	
	public Admin updateAdminUserName(Admin admin) throws AdminException;
	
	public Admin updateAdminPassword(String userName,String oldPassword,String newPassword ) throws AdminException;
	
	public String deleteAdmin(Integer id) throws AdminException;
	
	public LogIn adminLogIn(AdminDto admin)throws AdminException;
	
	public String adminLogOut(String uuid)throws AdminException;
	
	
}
