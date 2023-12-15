package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.service.CategoryService;
import com.poly.service.OrderService;
import com.poly.service.ProductService;

@Controller
public class StockController {
	@Autowired
    OrderService orderService;
	@Autowired
	ProductService productService;
	@Autowired 
	CategoryService categoryService;
	
	@GetMapping("/stock/index")
	public String stockIndex(Model model) {
	     return "stock/index";
	}
	
	@GetMapping("/stock/order")
	public String stockOrder(Model model) {
		model.addAttribute("links","/stock/order");
		model.addAttribute("listSale",orderService.findAll());
	     return "stock/index";
	}
	
	@GetMapping("/stock/category")
	public String stockCategory(Model model) {
		model.addAttribute("listCategory",productService.findAll());
	     return "stock/index";
	}
	
	@GetMapping("/stock/product")
	public String stock(Model model) {
		model.addAttribute("links","/stock/product");
	     return "stock/index";
	}
}
