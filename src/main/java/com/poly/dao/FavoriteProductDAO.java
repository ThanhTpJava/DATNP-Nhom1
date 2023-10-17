package com.poly.dao;


import com.poly.entity.FavoriteProduct;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FavoriteProductDAO extends JpaRepository<FavoriteProduct, Integer>{

}
