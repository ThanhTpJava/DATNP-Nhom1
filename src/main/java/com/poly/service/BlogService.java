package com.poly.service;

import com.poly.entity.BlogPost;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService {
    List<BlogPost> findAll();

    List<BlogPost> findPaginated(Pageable pageable);

    BlogPost detailBlog(Integer id);

    BlogPost createBlog (BlogPost blog);

    BlogPost uodateBlog (BlogPost blog);

    void deleteBlog(Integer id);

    long getTotalBlog();
}
