package com.poly.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.OrderStatusDAO;
import com.poly.entity.OrderStatus;
import com.poly.service.OrderStatusService;

@Service
public class OrderStatusServiceImpl implements OrderStatusService{

	@Autowired
	OrderStatusDAO odsttDao;
	
	@Override
	public OrderStatus saveOrderStatus(OrderStatus orderStatus) {
		// TODO Auto-generated method stub
		return odsttDao.save(orderStatus);
	}

	@Override
	public OrderStatus findByOrderId(String orderId) {
		// TODO Auto-generated method stub
		return odsttDao.findOrderStatusByOrderid(orderId);
	}

}
