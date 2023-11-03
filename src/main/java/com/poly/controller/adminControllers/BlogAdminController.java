package com.poly.controller.adminControllers;

import com.poly.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BlogAdminController {
    @RequestMapping("/admin/blog")
    public String Blog(){
        return  "admin/blog";
    }

}
