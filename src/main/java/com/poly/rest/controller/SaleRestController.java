package com.poly.rest.controller;

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
import com.poly.service.AccountService;
import com.poly.service.OrderService;
import com.poly.service.ResponsibilityService;
import com.poly.service.RoleService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest")
public class SaleRestController {

	@Autowired
	AccountService accService;

	@Autowired
	OrderService orderService;

	@Autowired
	RoleService roleService;

	@Autowired
	ResponsibilityService responsibilityService;

	@PutMapping("/sale/updates/status/{orderID}/{username}/{statusID}")
	public ResponseEntity<?> updateStatusStock(@PathVariable("orderID") String orderId,
			@PathVariable("username") String username, @PathVariable("statusID") Integer statusID) {
		System.out.println(orderId + " " +  username+ " " +statusID);
		Account acc = accService.findById(username);
		
		Order order = orderService.findById(orderId);

		Role role = roleService.findRole("ROLE_SALE");

		responsibilityService.saveResponsibility(acc, order, role);
		
		orderService.updateOrderStatus(orderId, statusID);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/stock/updates/status/{orderID}/{username}/{statusID}")
	public ResponseEntity<?> updateStatus(@PathVariable("orderID") String orderId,
			@PathVariable("username") String username, @PathVariable("statusID") Integer statusID) {
		System.out.println(orderId + " " +  username+ " " +statusID);
		Account acc = accService.findById(username);
		
		Order order = orderService.findById(orderId);

		Role role = roleService.findRole("ROLE_STOCK");

		responsibilityService.saveResponsibility(acc, order, role);
		
		orderService.updateOrderStatus(orderId, statusID);
		return ResponseEntity.ok().build();
	}
}
