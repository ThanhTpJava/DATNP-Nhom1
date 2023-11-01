package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.entity.CountAdmin;
import com.poly.service.CounterAdminService;

@Service
public class CounterAdminServiceImpl implements CounterAdminService{

	@Autowired
	CounterAdminService dao;
	
	@Override
	public List<CountAdmin> findAll() {
		return dao.findAll();
	}

}
