package com.example.demo.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.DTO_and_ENUM.AddressType;
import com.example.demo.Exceptions.UserException;
import com.example.demo.Model.Address;
import com.example.demo.Model.User;

public interface AddressDao extends JpaRepository<Address,Integer>{
	
	public List<User>	findByUser(Integer id)throws UserException;
	
	@Query(value = "from Address where type=:ty")
	public Address getByType(@Param("ty")AddressType type)throws UserException;
	
}
