package com.poly.rest.controller;

import com.poly.dao.ProductDAO;
import com.poly.entity.Product;
import com.poly.entity.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin("*")
public class DetailProductRestController {
    @Autowired
    ProductDAO productRepository;

    @GetMapping("/detail/{id}")
    public ResponseEntity<Product> getOne(@PathVariable("id") int id) {
        Product product = productRepository.findById(id).get();
        if (product != null) {
            List<ProductImage> productImages = product.getProductImage();
            product.setProductImage(productImages);
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
