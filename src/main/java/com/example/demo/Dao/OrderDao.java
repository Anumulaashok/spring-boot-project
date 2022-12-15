package com.example.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.Orders;

public interface OrderDao extends JpaRepository<Orders, Integer>{

}
