package com.poly.controller;


import java.io.Console;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.poly.entity.Data;
import com.poly.util.CreateOrderId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.dao.AccountDAO;
import com.poly.dto.ListUsersRewardsDTO;
import com.poly.entity.Order;
import com.poly.entity.OrderExcelExcelExporter;
import com.poly.entity.VoucherOfUser;
import com.poly.service.AccountService;
//import com.poly.service.EmailService;
import com.poly.service.OrderService;
import com.poly.service.VoucherOfUserService;
import com.poly.entity.Account;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class OrderController {
	  private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	@Autowired
	OrderService orderService;
	
	@Autowired
	HttpSession session;

	@Autowired
	CreateOrderId createOrderId;
//	  @Autowired
//	    private EmailService emailService;
	 @Autowired
	 private AccountService accountService;
	  
	 @Autowired
	 VoucherOfUserService voucherOfUserService;
	  
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
	
	@GetMapping("/orders/export")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachement; filename=order.xlsx";
		
		response.setHeader(headerKey, headerValue);
		
		List<Order> listOrder = orderService.findAll1();
		
		OrderExcelExcelExporter excelExport = new OrderExcelExcelExporter(listOrder);
		excelExport.export(response);
	}

//	@RequestMapping("/calculateTotalRevenueByMonth/")
//	@ResponseBody
//	public String getDataFromDB(@RequestParam int month, @RequestParam int year) {
//		List<Object[]> dataList = orderService.calculateTotalRevenueByMonth(month, year);
//
//
//
//	}

}
