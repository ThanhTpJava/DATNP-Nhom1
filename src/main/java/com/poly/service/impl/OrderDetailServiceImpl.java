package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.OrderDetailDAO;
import com.poly.entity.OrderDetail;
import com.poly.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{
	
	@Autowired
	OrderDetailDAO orderDetailDAO;

	@Override
	public List<OrderDetail> findAll() {
		// TODO Auto-generated method stub
		return orderDetailDAO.findAll();
	}

}
