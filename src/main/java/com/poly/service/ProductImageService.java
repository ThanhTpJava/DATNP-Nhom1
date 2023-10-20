package com.poly.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.poly.entity.ProductImage;

public interface ProductImageService {
	List<ProductImage> findAll();
    List<ProductImage> findPaginated(Pageable pageable);
    
    long getTotalProducts();
}
