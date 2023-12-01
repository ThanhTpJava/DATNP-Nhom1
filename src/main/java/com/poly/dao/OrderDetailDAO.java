package com.poly.dao;

import com.poly.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.poly.entity.OrderDetail;

import java.util.List;

@Repository
public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long>{
    @Query("SELECT ord FROM OrderDetail ord WHERE ord.order.id=?1")
    List<OrderDetail> findByOrderid(String orderId);
}
