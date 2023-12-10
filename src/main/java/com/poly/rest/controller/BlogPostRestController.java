package com.poly.rest.controller;

import com.poly.dao.AccountDAO;
import com.poly.dao.BlogPostDAO;

import com.poly.entity.BlogPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/blog")
public class BlogPostRestController {
    @Autowired
    BlogPostDAO blogPostDAO;

    @Autowired
    AccountDAO accountDAO;
    @GetMapping
    public List<BlogPost> getAll(Model model) {
        return blogPostDAO.findAll();
    }

    @GetMapping("{id}")
    public BlogPost getOne(@PathVariable("id") Integer id) {
        return blogPostDAO.findById(id).get();
    }

    @PostMapping
    public BlogPost post(@RequestBody BlogPost blogPost) {
        return blogPostDAO.save(blogPost);
    }

    @PutMapping("{id}")
    public BlogPost put(@PathVariable("id") String id, @RequestBody BlogPost blogPost) {
        return blogPostDAO.save(blogPost);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Integer id) {
        blogPostDAO.deleteById(id);
    }
}
