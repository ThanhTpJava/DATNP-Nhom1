package com.poly.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.StatusDAO;
import com.poly.entity.Status;
import com.poly.service.StatusService;

@Service
public class StatusServiceImpl implements StatusService{

	@Autowired
	StatusDAO statusDao;
	
	@Override
	public Status findStatusById(int id) {
		// TODO Auto-generated method stub
		return statusDao.findById(id).get();
	}
}
