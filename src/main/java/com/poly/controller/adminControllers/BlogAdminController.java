package com.poly.controller.adminControllers;

import com.poly.entity.BlogPost;
import com.poly.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BlogAdminController {
    @Autowired
    BlogService blogService;

    private final int pageSize = 3;
    @RequestMapping("/admin/blog")
    public String Blog(@RequestParam(defaultValue = "0") int page, Model model){
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());
        List<BlogPost> list = blogService.findPaginated(pageable);

        int totalPages = (int) Math.ceil((double) blogService.getTotalBlog() / pageSize);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("items", list);
        return  "admin/blog";
    }

    @RequestMapping("admin/blog/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id){

        BlogPost item = blogService.detailBlog(id);
        model.addAttribute("item", item);

        List<BlogPost> list = blogService.findAll();
        model.addAttribute("items", list);

        return "user/blog-detail";
    }


}
