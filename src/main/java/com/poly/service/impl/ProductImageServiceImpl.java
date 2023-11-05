package com.poly.service.impl;

import com.poly.dao.ProductImageDAO;
import com.poly.entity.Product;
import com.poly.entity.ProductImage;
import com.poly.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

//    @Override
//    public List<ProductImage> findPaginated(Pageable pageable) {
//        Page<ProductImage> productPage = productImageDAO.findAll(pageable);
//        return productPage.getContent();
//    }

}

