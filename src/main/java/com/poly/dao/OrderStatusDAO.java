package com.poly.dao;

import com.poly.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderStatusDAO extends JpaRepository<OrderStatus, Long> {
	@Query("SELECT ordstt FROM OrderStatus ordstt WHERE ordstt.order.id = ?1")
	OrderStatus findOrderStatusByOrderid(String orderid);
}
