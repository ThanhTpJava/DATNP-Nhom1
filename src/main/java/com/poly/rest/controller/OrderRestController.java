package com.poly.rest.controller;

import com.poly.dao.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.entity.Order;
import com.poly.service.OrderService;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class OrderRestController {
	@Autowired
	OrderService orderService;

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

	@PutMapping("{id}")
	public Order editStatus(@PathVariable("id") Long id, @RequestBody Order order){
		return orderService.EditbyID(order);
	}

	@DeleteMapping("{id}")
	public void deleteOrder(@PathVariable String id){
		orderService.delete(id);
	}

	@GetMapping("/deliveredOrdersByMonth")
	public List<Object[]> deliveredOrdersByMonth() {
		return orderService.deliveredOrdersByMonth();
	}

}
