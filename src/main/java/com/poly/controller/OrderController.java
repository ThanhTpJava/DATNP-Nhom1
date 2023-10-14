package com.poly.controller;


import java.util.HashMap;
import java.util.Map;
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
//	@Autowired
//	AccountDAO accountDAO;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	HttpSession session;
	
	@RequestMapping("/order/checkout")
	public String checkout(HttpServletRequest request) {
		 HttpSession session = request.getSession();
		if(session.getAttribute("authentication") != null){
			return "user/order-checkout";
		}
		return "redirect:/auth/login/form";
		
	}

	@RequestMapping("/order/detail/{id}")
	public String detail(Model model, @PathVariable("id") Long id) {
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
	    String username = request.getRemoteUser();

	    HttpSession session = request.getSession();
	    Map<String, Object> authentication = (Map<String, Object>) session.getAttribute("authentication");

	    if (authentication != null && authentication.containsKey("user")) {
	        Account account = (Account) authentication.get("user");
	        System.out.println("Username from session: " + account.getUsername());

	        // Đoạn code xử lý dựa trên thông tin người dùng từ session
	        model.addAttribute("orders", orderService.findByUsername(account.getUsername()));
	    } else {
	    	return "/auth/login/success"; //login
	    }

	    return "user/order-list";
	}

}
