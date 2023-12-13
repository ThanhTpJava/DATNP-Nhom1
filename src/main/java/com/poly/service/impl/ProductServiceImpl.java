package com.poly.service.impl;

import java.util.List;

import com.poly.dao.ProductImageDAO;
import com.poly.entity.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.dao.ProductDAO;
import com.poly.entity.Product;
import com.poly.service.ProductService;


@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductDAO dao;

	@Autowired
	ProductImageDAO imageDAO;

	@Override
	public List<Product> findAll(){
		return dao.findAll();
	}
	@Override
	public Product findByID(Integer id){
		return dao.findById(id).get();
	}

	public List<Product> findbyCategoryId(String cid, Pageable pageable) {
		return dao.findByCategoryId(cid, pageable);
	}

	@Override
	public List<Product> findByPriceAndCategoryId(String categoryId, Double minPrice, Double maxPrice) {
		return dao.findByPriceAndCategoryId(categoryId, minPrice, maxPrice);
	}
	@Override
	public List<Product> findPaginated(Pageable pageable) {
		Page<Product> productPage = dao.findAll(pageable);
		return productPage.getContent();
	}

	@Override
	public long getTotalProducts() {
		return dao.count();
	}
	@Override
	public List<Product> searchByKeyword(String keyword, Pageable pageable) {
		return dao.searchByKeyword(keyword, pageable);
	}
	@Override
	public List<Product> getProductsByPriceRange(double minPrice, double maxPrice, Pageable pageable) {
		return dao.findByPriceBetween(minPrice, maxPrice, pageable);
	}

	public Product create(Product product) {
		return dao.save(product);
	}

	public Product update(Product product) {
		return dao.save(product);
	}

	public void delete(Integer id) {
		// Kiểm tra xem có tồn tại hình ảnh sản phẩm không
		List<ProductImage> productImages = imageDAO.findByProduct(id);

		if (!productImages.isEmpty()) {
			// Nếu có hình ảnh, xóa chúng trước
			for (ProductImage productImage : productImages) {
				imageDAO.deleteById(productImage.getId());
			}
		}

		// Sau đó, xóa sản phẩm
		dao.deleteById(id);
	}

	@Override
	public List<Product> findProductsByCategoryId(String categoryId) {
		return dao.idcategoryID(categoryId);
	}
	
	@Override
	public List<Product> findLatestProducts() {
		return dao.findLatestProducts();
	}
	
	@Override
	public void UpdateReduceProductQuantity(Integer productId, Integer quantity) {
		dao.updateReduceProductQuantity(productId, quantity);	
	}
	@Override
	public void updateIncreaseProductQuantity(Integer productId, Integer quantity) {
		dao.updateIncreaseProductQuantity(productId, quantity);
	}

	@Override
	public List<Product> findProductsByName(String name) {
		return dao.findProductsByName(name);
	}


}
