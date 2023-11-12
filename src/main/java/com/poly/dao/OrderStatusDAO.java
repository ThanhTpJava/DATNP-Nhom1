package com.poly.dao;

import com.poly.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusDAO extends JpaRepository<OrderStatus, Long> {
}
