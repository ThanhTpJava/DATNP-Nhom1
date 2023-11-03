package com.poly.service;

import com.poly.entity.ProductImage;

import java.util.List;

public interface ProductImageService {
    List<ProductImage> findAll();
    ProductImage findById(Integer id);

    ProductImage createImageForProduct(ProductImage productImage);
    ProductImage editById(ProductImage productImage);
    public void delete(Integer id) ;
}
