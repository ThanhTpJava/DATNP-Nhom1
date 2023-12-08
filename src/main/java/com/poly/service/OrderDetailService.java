package com.poly.service;

import java.util.List;

import com.poly.entity.OrderDetail;
import com.poly.entity.Product;

public interface OrderDetailService {
	List<OrderDetail> findAll();

	List<OrderDetail> findByOrderId(String orderId);
	
	List<OrderDetail> createOrderDetailPaymentApi(List<OrderDetail> listOrderDetail);
}
