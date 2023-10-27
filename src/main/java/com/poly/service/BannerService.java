package com.poly.service;

import java.util.List;
import java.util.Optional;

import com.poly.entity.Banner;

public interface BannerService {
	List<Banner> findAll();
	
	Banner save(Banner banner);
	Banner update(Banner banner);
	Optional<Banner> findByBannerID(Integer BannerID);
	
	void deleteByBannerID(Integer BannerID);
}
