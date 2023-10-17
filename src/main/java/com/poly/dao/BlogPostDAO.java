package com.poly.dao;


import com.poly.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostDAO extends JpaRepository<BlogPost, Integer>{

}
