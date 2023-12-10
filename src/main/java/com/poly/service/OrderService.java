package com.poly.service;

import java.util.List;


import com.fasterxml.jackson.databind.JsonNode;
import com.poly.dto.OrderShipDTO;
import com.poly.dto.OrdersDTO;
import com.poly.entity.Order;

public interface OrderService {


	public Order create(JsonNode orderData) ;
	
	public Order createOrderPaymentApi(Order order);
	
	public Order findById(String id) ;
	
	public List<Order> findByUsername(String username) ;

	public List<OrderShipDTO> findAll();
	
	public List<Order> findAll1();

//	public Order EditbyID(Order order);

	public void delete(String id) ;

//	public List<Object[]> deliveredOrdersByMonth();

	List<String> getListOrderId();
	
	List<OrdersDTO> findOrdersDTOsByUsername(String username);

	public OrderShipDTO findByIdShip(String id);

	public void updateOrderStatus(String orderId, Integer statusId);

	Double calculateTotalRevenue();

	List<Object[]> calculateTotalRevenueByDate(Integer day, Integer month, Integer year);
	List<Object[]> calculateTotalRevenueByMonth(Integer month, Integer year);

	List<Object[]> calculateTotalOrderByMonth(Integer month, Integer year);

	List<Object[]> calculateTotalOrderByMonth0(Integer month, Integer year);

	List<Object[]> calculateTotalOrderByMonth1(Integer month, Integer year);

	List<Object[]> calculateTotalOrderByMonth2(Integer month, Integer year);

	List<Object[]> calculateTotalOrderByMonth3(Integer month, Integer year);

	List<Object[]> calculateTotalOrderByMonth4(Integer month, Integer year);

	List<Object[]> calculateTotalOrderByMonth5(Integer month, Integer year);

	Double calculateTotalRevenueByYear(Integer year);

	Integer calculateTotalOrderByYear(Integer year);
	
	List<Order> findAllByStock();

}
