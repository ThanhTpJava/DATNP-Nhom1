package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.service.OrderService;

@Controller
public class StockController {
	@Autowired
    OrderService orderService;
	
	@GetMapping("/admin/stock")
	public String saleOrder(Model model) {
		 model.addAttribute("listStock",orderService.findAllByStock());
	     return "admin/stock";
	}
}
