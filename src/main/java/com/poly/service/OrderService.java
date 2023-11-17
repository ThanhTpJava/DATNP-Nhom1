package com.poly.service;

import java.util.List;


import com.fasterxml.jackson.databind.JsonNode;
import com.poly.entity.Order;

public interface OrderService {


	public Order create(JsonNode orderData) ;
	
	public Order findById(String id) ;
	
	public List<Order> findByUsername(String username) ;

	public List<Order> findAll();

	public void delete(String id) ;

//	public List<Object[]> deliveredOrdersByMonth();

	List<String> getListOrderId();

	public Order update(Order order);
}
