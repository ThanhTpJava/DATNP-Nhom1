package com.poly.controller;


import java.util.HashMap;
import java.util.Map;

import com.poly.util.CreateOrderId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.dao.AccountDAO;
import com.poly.entity.Order;
import com.poly.service.OrderService;
import com.poly.entity.Account;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	HttpSession session;

	@Autowired
	CreateOrderId createOrderId;
	
	@RequestMapping("/order/checkout")
	public String checkout(HttpServletRequest request, Model model) {
			
		 HttpSession session = request.getSession();
		if(session.getAttribute("authentication") != null){
			String orderId = createOrderId.generateInvoiceCode();
			System.out.println("Mã hóa đơn: " +orderId);
			model.addAttribute("orderId", orderId);
			return "user/order-checkout";
		}
		
		return "redirect:/auth/login/form";
		
	}

	@RequestMapping("/order/detail/{id}")
	public String detail(Model model, @PathVariable("id") String id) {
		model.addAttribute("order", orderService.findById(id));
		return "user/order-detail";
	}
	
//	@RequestMapping("/order/list")
//	public String detail(Model model, HttpServletRequest request) {
//		String username = request.getRemoteUser();
//		
////		String username = (String) session.getAttribute("auth.user.username");
//		System.out.println(username);           	
//		model.addAttribute("orders", orderService.findByUsername(username));
//		return "user/order-list";
//	}
	
	@RequestMapping("/order/list")
	public String detail(Model model, HttpServletRequest request) {

	   Account account = (Account) session.getAttribute("authentication");
		if(account != null){
//			model.addAttribute("orders", orderService.findByUsername(account.getUsername()));
			model.addAttribute("orders", orderService.findOrdersDTOsByUsername(account.getUsername()));
			return "user/order-list";
		}
		else {
			return "/auth/login/form"; //login
		}
	}
}
