package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.dao.ProductImageRepository;
import com.poly.entity.Product;
import com.poly.entity.ProductImage;
import com.poly.service.ProductImageService;

@Service
public class ProductImageImpl implements ProductImageService{
	@Autowired
	ProductImageRepository dao;

	@Override
	public List<ProductImage> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public List<ProductImage> findPaginated(Pageable pageable) {
		Page<ProductImage> productPage = dao.findAll(pageable);
		return productPage.getContent();
	}
	
	@Override
	public long getTotalProducts() {
		return dao.count();
	}
}
