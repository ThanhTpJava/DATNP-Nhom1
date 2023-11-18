package com.poly.rest.controller;

import com.poly.dao.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.entity.Order;
import com.poly.entity.OrderStatus;
import com.poly.entity.Status;
import com.poly.service.OrderService;
import com.poly.service.OrderStatusService;
import com.poly.service.StatusService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class OrderRestController {
	@Autowired
	OrderService orderService;

	@Autowired
	StatusService statusService;

	@Autowired
	OrderStatusService orderStatusService;

	@GetMapping
	public List<Order> GetAll() {
		return orderService.findAll();
	}

	@GetMapping("{id}")
	public Order findByID(@PathVariable("id") String id) {
		return orderService.findById(id);
	}

	@PostMapping
	public ResponseEntity<String> purchase(@RequestBody JsonNode orderData) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			orderService.create(orderData);
			
			Order order = objectMapper.treeToValue(orderData, Order.class);
			String orderId = order.getId();
			int statusId = 1;
			
			Order findOrderData = orderService.findById(orderId);
			Status findDttData = statusService.findStatusById(statusId);
			
			OrderStatus orderStatus = new OrderStatus();
			orderStatus.setOrder(findOrderData);
			orderStatus.setStatus(findDttData);
			
			orderStatusService.saveOrderStatus(orderStatus);
			return new ResponseEntity<String>("{\"message\":\"success\"}", HttpStatus.CREATED);
		} catch (JsonProcessingException | IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>("{\"message\":\"errors\"}", HttpStatus.BAD_REQUEST);
		}


	}

//	@PutMapping("{id}")
//	public Order editStatus(@PathVariable("id") Long id, @RequestBody Order order){
//		return orderService.EditbyID(order);
//	}

	@DeleteMapping("{id}")
	public void deleteOrder(@PathVariable String id) {
		orderService.delete(id);
	}

//	@GetMapping("/deliveredOrdersByMonth")
//	public List<Object[]> deliveredOrdersByMonth() {
//		return orderService.deliveredOrdersByMonth();
//	}

	// Endpoint để thay đổi trạng thái của đơn hàng
	@PutMapping("/{orderId}/update-status/{statusId}")
	public ResponseEntity<String> updateOrderStatus(@PathVariable String orderId, @PathVariable Integer statusId) {
		// Lấy đơn hàng từ database
		Order order = orderService.findById(orderId);

		if (order == null) {
			return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
		}

		// Lấy trạng thái từ database
		Status newStatus = statusService.findStatusById(statusId);

		if (newStatus == null) {
			return new ResponseEntity<>("Status not found", HttpStatus.NOT_FOUND);
		}

		Order findOrderData = orderService.findById(orderId);
		Status findDttData = statusService.findStatusById(statusId);

		OrderStatus orderStatus = new OrderStatus();
		orderStatus.setOrder(findOrderData);
		orderStatus.setStatus(findDttData);

		orderStatusService.saveOrderStatus(orderStatus);

		return new ResponseEntity<>("Order status updated successfully", HttpStatus.OK);
	}

	@GetMapping("/orderID/{orderId}")
	public Long getOrderStatusByOrder(@PathVariable String orderId) {
		OrderStatus orderStatus = orderStatusService.findOrderStatusbyOrder(orderId);

		if (orderStatus != null) {
			return orderStatus.getId();
		} else {
			return null;
		}
	}

}
