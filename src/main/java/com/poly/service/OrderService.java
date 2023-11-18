package com.poly.service;

import java.util.List;


import com.fasterxml.jackson.databind.JsonNode;
import com.poly.entity.Order;
import com.poly.entity.Product;

public interface OrderService {
	public Order create(JsonNode orderData) ;
	
	public Order findById(String id) ;
	
	public List<Order> findByUsername(String username) ;

	List<Order> findAll();
}
