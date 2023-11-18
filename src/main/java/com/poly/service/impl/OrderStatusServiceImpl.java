package com.poly.service.impl;

import com.poly.entity.Order;
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
	public OrderStatus findOrderStatusbyOrder(String orderid) {
		return odsttDao.findOrderStatusByOrder(orderid);
	}


}
