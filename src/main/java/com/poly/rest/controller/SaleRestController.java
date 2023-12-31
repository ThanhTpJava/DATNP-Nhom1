package com.poly.rest.controller;

import com.poly.entity.OrderDetail;
import com.poly.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.entity.Account;
import com.poly.entity.Order;
import com.poly.entity.Role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/sale")
public class SaleRestController {

	@Autowired
	AccountService accService;

	@Autowired
	OrderService orderService;

	@Autowired
	OrderDetailService orderDetailService;
	@Autowired
	RoleService roleService;

	@Autowired
	ResponsibilityService responsibilityService;

	@PutMapping("/updates/status/{orderID}/{username}/{statusID}")
	public ResponseEntity<?> updateStatusSale(@PathVariable("orderID") String orderId,
			@PathVariable("username") String username, @PathVariable("statusID") Integer statusID) {
		System.out.println(orderId + " " +  username+ " " +statusID);
		Account acc = accService.findById(username);
		
		Order order = orderService.findById(orderId);

		Role role = roleService.findRole("ROLE_SALE");

		responsibilityService.saveResponsibility(acc, order, role);
		
		orderService.updateOrderStatus(orderId, statusID);
		return ResponseEntity.ok().build();
	}

	
}
