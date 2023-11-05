package com.poly.service;

import com.poly.entity.ProductImage;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProductImageService {
    List<ProductImage> findAll();
    ProductImage findById(Integer id);

    ProductImage createImageForProduct(ProductImage productImage);
    ProductImage editById(ProductImage productImage);
    public void delete(Integer id) ;

//    List<ProductImage> findPaginated(Pageable pageable);
}
