package com.example.demo.Service;

import java.util.List;
import java.util.Set;

import com.example.demo.DTO_and_ENUM.Invoice;
import com.example.demo.Exceptions.UserException;
import com.example.demo.Model.Address;

public interface OrderService {
	public Address findAddressbyid(String uuid)throws UserException;
	
	public boolean 	addAddressById(Address address,String uuid)throws UserException;
	
	public boolean deleteAddressbyid(String uuid,Integer id)throws UserException;

	public Invoice orderTheItems(String uuid) throws UserException;
}
