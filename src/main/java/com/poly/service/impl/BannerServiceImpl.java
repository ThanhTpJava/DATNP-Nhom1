package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.BannerRepository;
import com.poly.entity.Banner;
import com.poly.service.BannerService;

@Service
public class BannerServiceImpl implements BannerService{

	@Autowired
	BannerRepository dao;
	
	@Override
	public List<Banner> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

}
