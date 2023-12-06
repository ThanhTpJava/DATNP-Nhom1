package com.poly.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.entity.Account;
import com.poly.entity.Order;
import com.poly.entity.OrderDetail;
import com.poly.entity.Role;
import com.poly.service.AccountService;
import com.poly.service.OrderDetailService;
import com.poly.service.OrderService;
import com.poly.service.ProductService;
import com.poly.service.ResponsibilityService;
import com.poly.service.RoleService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/stock")
public class StockRestController {

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

	@Autowired
	ProductService productService;

	@PutMapping("/updates/status/{orderID}/{username}/{statusID}")
	public ResponseEntity<?> updateStatusStock(@PathVariable("orderID") String orderId,
			@PathVariable("username") String username, @PathVariable("statusID") Integer statusID) {

		System.out.println(orderId + " " + username + " " + statusID);

		if (statusID == 3) {
			Map<Integer, Integer> productQuantiOrder = new HashMap<>();
			List<OrderDetail> orderDetails = orderDetailService.findByOrderId(orderId);
			for (OrderDetail order : orderDetails) {
				// Sử dụng order.getProductId() làm key và order.getQuanty() làm giá trị
				productQuantiOrder.put(order.getProduct().getId(), order.getQuantity());
			}

			// In ra các phần tử trong productQuantiOrder
			for (Map.Entry<Integer, Integer> entry : productQuantiOrder.entrySet()) {
				System.out.println("ProductId: " + entry.getKey() + ", Quanty: " + entry.getValue());
				productService.UpdateReduceProductQuantity(entry.getKey(), entry.getValue());
			}
		}

		Account acc = accService.findById(username);

		Order order = orderService.findById(orderId);

		Role role = roleService.findRole("ROLE_STOCK");

		responsibilityService.saveResponsibility(acc, order, role);

		orderService.updateOrderStatus(orderId, statusID);
		return ResponseEntity.ok().build();
	}
}
