package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entity.OrderVoucher;

public interface OrderVoucherDAO extends JpaRepository<OrderVoucher, Integer>{
	
}
