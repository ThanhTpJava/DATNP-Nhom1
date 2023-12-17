package com.poly.controller.adminControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryAdminController {
    @GetMapping("/category")
    public String load(){
        return "admin/category";
    }
}
