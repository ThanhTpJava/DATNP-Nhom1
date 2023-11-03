package com.poly.service.impl;

import com.poly.dao.ProductImageDAO;
import com.poly.entity.ProductImage;
import com.poly.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    ProductImageDAO productImageDAO;
    @Override
    public List<ProductImage> findAll() {
       return productImageDAO.findAll();
    }

    @Override
    public ProductImage findById(Integer id) {
        return productImageDAO.findById(id).get();
    }

    @Override
    public ProductImage createImageForProduct(ProductImage productImage) {
        return productImageDAO.save(productImage);
    }

    @Override
    public ProductImage editById(ProductImage productImage) {
        return productImageDAO.save(productImage);
    }

    @Override
    public void delete(Integer id) {
        productImageDAO.deleteById(id);
    }
}
