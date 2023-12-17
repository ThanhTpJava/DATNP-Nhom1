package com.poly.service;

import java.util.List;

import com.poly.entity.Order;
import com.poly.entity.OrderStatus;

public interface OrderStatusService {
	
	public List<OrderStatus> findAll();

	OrderStatus saveOrderStatus(OrderStatus orderStatus);
	
	OrderStatus findByOrderId(String orderId);
	
	List<OrderStatus> searchOrderStatus(String keyword);
}
