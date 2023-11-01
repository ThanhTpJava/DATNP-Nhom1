package com.poly.rest.controller;

import java.util.List;

import com.poly.dao.CategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.poly.entity.Category;
import com.poly.service.CategoryService;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest/categories")
public class CategoryRestController {
    @Autowired
    CategoryDAO dao;

    @GetMapping()
    public List<Category> getAll(Model model) {
        return dao.findAll();
    }

    @GetMapping("{id}")
    public Category getOne(@PathVariable("id") String id) {
        return dao.findById(id).get();
    }

    @PostMapping()
    public Category post(@RequestBody Category product) {
        return dao.save(product);
    }

    @PutMapping("{id}")
    public Category put(@PathVariable("id") String id, @RequestBody Category product) {
        return dao.save(product);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") String id) {
        dao.deleteById(id);
    }
}
