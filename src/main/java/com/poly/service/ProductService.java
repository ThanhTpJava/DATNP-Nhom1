package com.poly.service;

import com.poly.entity.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {


    List<Product> findAll();
    List<Product> findPaginated(Pageable pageable);

    long getTotalProducts();

    Product findByID(Integer id);

    List<Product> findbyCategoryId(String cid, Pageable pageable);
    List<Product> findByPriceAndCategoryId(String categoryId, Double minPrice, Double maxPrice);


    List<Product> searchByKeyword(String keyword, Pageable pageable);
    List<Product> getProductsByPriceRange(double minPrice, double maxPrice, Pageable pageable);
    
    public Product create(Product product) ;

	public Product update(Product product) ;
	
	List<Product> findProductsByCategoryId(String categoryId);

	public void delete(Integer id) ;

    List<Product> findLatestProducts();

    List<Product> findProductsByName(String name);
    
    void UpdateReduceProductQuantity(Integer productId, Integer quantity);
    
    void updateIncreaseProductQuantity(Integer productId, Integer quantity);
}
