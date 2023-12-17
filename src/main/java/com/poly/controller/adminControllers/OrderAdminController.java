package com.poly.controller.adminControllers;


import com.poly.entity.OrderStatus;
import com.poly.service.OrderService;
import com.poly.service.OrderStatusService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderAdminController {

    @Autowired
    OrderService orderService;
    
    @Autowired
    OrderStatusService orderStatusService;
    
    @RequestMapping("/admin/orders")
    public String findAll(Model model) {
        model.addAttribute("orders",orderStatusService.findAll());
        return "admin/order";
    }

    @RequestMapping("/admin/shipping")
    public String Shipping(Model model){
        model.addAttribute("listShipping",orderService.findAll());
        return "admin/shipping";
    }
    
    @GetMapping("/searchs")
    public String searchOrderStatus(@RequestParam("keyword") String keyword, Model model) {
    	List<OrderStatus> orderStatus = orderStatusService.searchOrderStatus(keyword);
    	model.addAttribute("orders", orderStatus);
    	return "admin/order";
    }
}
