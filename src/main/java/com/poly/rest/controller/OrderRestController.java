package com.poly.rest.controller;

import com.poly.dao.OrderDAO;
import com.poly.entity.OrderStatus;
import com.poly.entity.Status;
import com.poly.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.entity.Order;
import com.poly.service.OrderService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class OrderRestController {
	@Autowired
	OrderService orderService;

	@Autowired
	StatusService statusService;

	@GetMapping
	public List<Order> GetAll(){
		return orderService.findAll();
	}
	@GetMapping("{id}")
	public Order findByID(@PathVariable("id") String id){
		return orderService.findById(id);
	}
	
	@PostMapping
	public ResponseEntity<Order> purchase(@RequestBody JsonNode orderData) {
		return new ResponseEntity<Order>(orderService.create(orderData), HttpStatus.OK);
	}

//	@PutMapping("{id}")
//	public Order editStatus(@PathVariable("id") Long id, @RequestBody Order order){
//		return orderService.EditbyID(order);
//	}

	@DeleteMapping("{id}")
	public void deleteOrder(@PathVariable String id){
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
		Status newStatus = statusService.findById(statusId);

		if (newStatus == null) {
			return new ResponseEntity<>("Status not found", HttpStatus.NOT_FOUND);
		}

		// Lấy OrderStatus hiện tại
		OrderStatus orderStatus = order.getOrderStatuses();

		// Cập nhật trạng thái mới
		orderStatus.setStatus(newStatus);

		// Lưu thay đổi
		orderService.update(order);

		return new ResponseEntity<>("Order status updated successfully", HttpStatus.OK);
	}

}
