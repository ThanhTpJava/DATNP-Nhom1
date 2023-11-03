package com.poly.controller.adminControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductImageControler {
    @GetMapping("admin/productImage")
    public String Image(){
        return "admin/productImage";
    }
}
