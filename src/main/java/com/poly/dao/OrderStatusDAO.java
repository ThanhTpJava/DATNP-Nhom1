package com.poly.dao;

import com.poly.entity.Order;
import com.poly.entity.OrderStatus;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusDAO extends JpaRepository<OrderStatus, Long> {
	@Query("SELECT ordstt FROM OrderStatus ordstt WHERE ordstt.order.id = ?1")
	OrderStatus findOrderStatusByOrderid(String orderid);

	@Modifying
	@Query("DELETE FROM OrderStatus os WHERE os.order.id = :orderId")
	void deleteByOrderId(@Param("orderId") String orderId);

	@Query("SELECT o FROM OrderStatus o WHERE o.status.name LIKE %?1% or o.order.account.name LIKE %?1%")
	List<OrderStatus> searchOrderStatus(String keyword);
}
