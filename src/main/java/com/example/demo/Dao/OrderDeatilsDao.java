package com.example.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.OrderDetails;

public interface OrderDeatilsDao extends JpaRepository<OrderDetails, Integer>{

}
