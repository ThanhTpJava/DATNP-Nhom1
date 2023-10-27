package com.poly.service.impl;

import com.poly.dao.BlogPostDAO;
import com.poly.entity.BlogPost;
import com.poly.entity.Product;
import com.poly.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogPostDAO dao;


    @Override
    public List<BlogPost> findAll() {
        return dao.findAll();
    }

    @Override
    public List<BlogPost> findPaginated(Pageable pageable) {
        Page<BlogPost> BlogPostPage = dao.findAll(pageable);
        return BlogPostPage.getContent();
    }

    @Override
    public BlogPost detailBlog(Integer id) {
        return dao.findById(id).get();
    }

    @Override
    public BlogPost createBlog(BlogPost blog) {
       return dao.save(blog);
    }

    @Override
    public BlogPost uodateBlog(BlogPost blog) {
        return  dao.save(blog);
    }

    @Override
    public void deleteBlog(Integer id) {
        dao.deleteById(id);
    }

    @Override
    public long getTotalBlog() {
        return dao.count();
    }


}
