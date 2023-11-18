package com.poly.service;

import com.poly.entity.Order;
import com.poly.entity.OrderStatus;

public interface OrderStatusService {

	OrderStatus saveOrderStatus(OrderStatus orderStatus);

	OrderStatus findOrderStatusbyOrder(String Orderid);
}
