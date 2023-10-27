package com.poly.controller;

import com.poly.entity.BlogPost;
import com.poly.entity.Product;
import com.poly.entity.ProductImage;
import com.poly.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BlogController {
    @Autowired
    BlogService blogService;

    private final int pageSize = 3;
    @GetMapping("/blog")
    public String SeeBlog(@RequestParam(defaultValue = "0") int page, Model model){
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());
        List<BlogPost> list = blogService.findPaginated(pageable);

        int totalPages = (int) Math.ceil((double) blogService.getTotalBlog() / pageSize);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("items", list);
        return "/user/blog";
    }

    @RequestMapping("blog/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id){

        BlogPost item = blogService.detailBlog(id);
        model.addAttribute("item", item);

        List<BlogPost> list = blogService.findAll();
        model.addAttribute("items", list);

        return "user/blog-detail";
    }

}
