package com.poly.controller.adminControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductAdminController {

    @RequestMapping("/admin/index")
    public String Dashboard(Model model) {
        return "admin/index";
    }

    @RequestMapping("/admin/products")

    public String Product(Model model) {
        return "admin/products";
    }

    @RequestMapping("/admin/accounts")

    public String Account(Model model) {

        return "admin/accounts";
//        return "admin/authorize/roles";
    }

}
