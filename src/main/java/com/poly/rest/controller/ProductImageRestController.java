package com.poly.rest.controller;

import com.poly.entity.ProductImage;
import com.poly.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/productImage")
public class ProductImageRestController {
    @Autowired
    ProductImageService productImageService;

    @GetMapping()
    public List<ProductImage> findAll(){
        return productImageService.findAll();
    }

    @GetMapping("{id}")
    public ProductImage ProductGetbyID(@PathVariable("id") Integer id){
        return productImageService.findById(id);
    }

    @PostMapping()
    public ProductImage post(@RequestBody ProductImage productImage){
         productImageService.createImageForProduct(productImage);
         return  productImage;
    }

    @PutMapping("{id}")
    public ProductImage editbyId(@PathVariable("id") Integer id, @RequestBody ProductImage productImage){
       return productImageService.editById(productImage);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable("id") Integer id){
        productImageService.delete(id);
    }
}
