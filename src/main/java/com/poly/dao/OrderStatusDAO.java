package com.poly.dao;

import com.poly.entity.Order;
import com.poly.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderStatusDAO extends JpaRepository<OrderStatus, Long> {
    @Query("SELECT os FROM OrderStatus os WHERE os.order.id = :orderid")
    OrderStatus findOrderStatusByOrder(@Param("orderid") String orderid);
}
