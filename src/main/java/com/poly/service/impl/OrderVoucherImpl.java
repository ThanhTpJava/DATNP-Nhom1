package com.poly.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.OrderVoucherDAO;
import com.poly.entity.OrderVoucher;
import com.poly.service.OrderService;
import com.poly.service.OrderVoucherService;
import com.poly.service.VoucherService;

@Service
public class OrderVoucherImpl implements OrderVoucherService{
	@Autowired
	OrderService orderService;
	
	@Autowired
	VoucherService voucherService;
	
	@Autowired
	OrderVoucherDAO orderVoucherDAO;
	
	@Override
	public OrderVoucher saveOrderVoucher(String order, String voucher) {
		// TODO Auto-generated method stub
		OrderVoucher ordvou = new OrderVoucher();
		ordvou.setOrder(orderService.findById(order));
		ordvou.setVoucher(voucherService.findByID(voucher));
		
		return orderVoucherDAO.save(ordvou);
	}

}
