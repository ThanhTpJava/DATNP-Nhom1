package com.poly.controller.adminControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.service.BannerService;
import com.poly.entity.Banner;

@Controller
public class ProductController {
	
	@Autowired
	BannerService bannerService;

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
    
    @RequestMapping("/admin/orders")

    public String Order(Model model) {
        return "admin/order";
    }
    
    @RequestMapping("/admin/banners")
    public String Banner(Model model) {
    	model.addAttribute("List", bannerService.findAll());
    	model.addAttribute("banners", new Banner());
        return "admin/banner";
    }
    
    @RequestMapping("/user/abouts")
    public String About(Model model) {
        return "user/about";
    }

}
