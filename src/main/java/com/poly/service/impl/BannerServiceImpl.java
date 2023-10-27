package com.poly.service.impl;

import java.util.List;
import java.util.Optional;

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
		return dao.findAll();
	}

	@Override
	public Banner save(Banner banner) {
		return dao.save(banner);
	}

	@Override
	public Banner update(Banner banner) {
		return dao.save(banner);
	}

	@Override
	public Optional<Banner> findByBannerID(Integer BannerID) {
		// TODO Auto-generated method stub
		return dao.findById(BannerID);
	}

	@Override
	public void deleteByBannerID(Integer BannerID) {
		dao.deleteById(BannerID);
	}
}
