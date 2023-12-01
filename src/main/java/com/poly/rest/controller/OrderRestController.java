package com.poly.rest.controller;

import com.poly.dao.OrderDAO;
import com.poly.dto.OrderShipDTO;
import com.poly.dto.OrdersDTO;
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

import java.util.Collections;
import java.util.List;
import java.util.Map;

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
	public List<OrderShipDTO> GetAll() {
		return orderService.findAll();
	}

	@GetMapping("{id}")
	public Order findByID(@PathVariable("id") String id) {
		return orderService.findById(id);
	}

	@GetMapping("/ship/{id}")
	public OrderShipDTO findShipByID(@PathVariable("id") String id) {
		return orderService.findByIdShip(id);
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

	@PutMapping("updateStatus/{orderId}/{statusId}")
	public void updateStatusShip(@PathVariable String orderId, @PathVariable Integer statusId){
		orderService.updateOrderStatus(orderId, statusId);
	}

	@GetMapping("calculateTotalRevenue")
	public Double findDeliveredOrders(){
		return orderService.calculateTotalRevenue();
	}

	@GetMapping("/calculateTotalRevenueByDate")
	public List<Object[]> calculateTotalRevenueByDate(@RequestParam int day, @RequestParam int month, @RequestParam int year) {
		return orderService.calculateTotalRevenueByDate(day, month, year);
	}

	@GetMapping("/calculateTotalRevenueByMonth")
	public ResponseEntity<List<Object[]>> calculateTotalRevenueByMonth(@RequestParam int month, @RequestParam int year) {
		List<Object[]> result = orderService.calculateTotalRevenueByMonth(month, year);

		// Kiểm tra xem dữ liệu có giá trị hay không
		if (result != null && !result.isEmpty()) {
			return ResponseEntity.ok(result);
		} else {
			// Trả về danh sách rỗng []
			return ResponseEntity.ok(Collections.emptyList());
		}
	}


	@GetMapping("/calculateTotalOrderByMonth")
	public List<Object[]> calculateTotalOrderByMonth(@RequestParam int month, @RequestParam int year) {
		return orderService.calculateTotalOrderByMonth(month, year);
	}
	@GetMapping("/calculateTotalRevenueByYear")
	public Double calculateTotalRevenueByYear(@RequestParam int year) {
		if(orderService.calculateTotalRevenueByYear(year) != null) {
			return orderService.calculateTotalRevenueByYear(year);
		}else {
			return 0.0;
		}	
	}

	@GetMapping("/calculateTotalOrderByYear")
	public Integer calculateTotalOrderByYear(@RequestParam int year) {
		return orderService.calculateTotalOrderByYear(year);
	}


}
