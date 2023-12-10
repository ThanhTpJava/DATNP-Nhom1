package com.poly.dao;


import com.poly.entity.Product;
import com.poly.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductImageDAO extends JpaRepository<ProductImage, Integer>{
    @Query("SELECT pi FROM ProductImage pi WHERE pi.product.id = :product")
    List<ProductImage> findByProduct(@Param("product") Integer product);

}
