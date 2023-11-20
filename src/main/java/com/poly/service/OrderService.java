package com.poly.service;

import java.util.List;


import com.fasterxml.jackson.databind.JsonNode;
import com.poly.dto.OrderShipDTO;
import com.poly.dto.OrdersDTO;
import com.poly.entity.Order;
import com.poly.entity.Product;

public interface OrderService {


	public Order create(JsonNode orderData) ;
	
	public Order findById(String id) ;
	
	public List<Order> findByUsername(String username) ;

	public List<Order> findAll();

//	public Order EditbyID(Order order);

	public void delete(String id) ;

//	public List<Object[]> deliveredOrdersByMonth();

	List<String> getListOrderId();
	
	List<OrdersDTO> findOrdersDTOsByUsername(String username);

	public OrderShipDTO findByIdShip(String id);
}
