package com.poly.controller.adminControllers;


import com.poly.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderAdminController {

    @Autowired
    OrderService orderService;
    @RequestMapping("/admin/orders")
    public String findAll(Model model) {
        model.addAttribute("orders",orderService.findAll());
        return "admin/order";
    }

    @RequestMapping("/admin/shipping")
    public String Shipping(Model model){
        model.addAttribute("listShipping",orderService.findAll());
        return "admin/shipping";
    }
}
