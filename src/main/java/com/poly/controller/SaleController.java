package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.service.OrderService;

@Controller
public class SaleController {
	@Autowired
    OrderService orderService;
	
	@RequestMapping("/admin/sale")
	public String saleOrder(Model model) {
		 model.addAttribute("listSale",orderService.findAll());
	     return "admin/sale";
	}
}
