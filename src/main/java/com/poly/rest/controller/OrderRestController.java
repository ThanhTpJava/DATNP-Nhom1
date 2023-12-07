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
import com.poly.entity.OrderDetail;
import com.poly.entity.OrderStatus;
import com.poly.entity.Status;
import com.poly.service.OrderDetailService;
import com.poly.service.OrderService;
import com.poly.service.OrderStatusService;
import com.poly.service.ProductService;
import com.poly.service.StatusService;

import java.util.Collections;
import java.util.HashMap;
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

	@Autowired
	ProductService productService;
	
	@Autowired
	OrderDetailService orderDetailService;
	
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
		if (statusId == 5) {
			Map<Integer, Integer> productQuantiOrder = new HashMap<>();
			List<OrderDetail> orderDetails = orderDetailService.findByOrderId(orderId);
			for (OrderDetail order : orderDetails) {
				// Sử dụng order.getProductId() làm key và order.getQuanty() làm giá trị
				productQuantiOrder.put(order.getProduct().getId(), order.getQuantity());
			}

			// In ra các phần tử trong productQuantiOrder
			for (Map.Entry<Integer, Integer> entry : productQuantiOrder.entrySet()) {
				System.out.println("ProductId: " + entry.getKey() + ", Quanty: " + entry.getValue());
				productService.updateIncreaseProductQuantity(entry.getKey(), entry.getValue());
			}
		}
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

	//Chua xac nhan
	@GetMapping("/calculateTotalOrderByMonth0")
	public List<Object[]> calculateTotalOrderByMonth0(@RequestParam int month, @RequestParam int year) {
		return orderService.calculateTotalOrderByMonth0(month, year);
	}
	//Dang xac nhan
	@GetMapping("/calculateTotalOrderByMonth1")
	public List<Object[]> calculateTotalOrderByMonth1(@RequestParam int month, @RequestParam int year) {
		return orderService.calculateTotalOrderByMonth1(month, year);
	}

	//Da xac nhan
	@GetMapping("/calculateTotalOrderByMonth2")
	public List<Object[]> calculateTotalOrderByMonth2(@RequestParam int month, @RequestParam int year) {
		return orderService.calculateTotalOrderByMonth2(month, year);
	}

	//Đang giao
	@GetMapping("/calculateTotalOrderByMonth3")
	public List<Object[]> calculateTotalOrderByMonth3(@RequestParam int month, @RequestParam int year) {
		return orderService.calculateTotalOrderByMonth3(month, year);
	}

	//Đa giao
	@GetMapping("/calculateTotalOrderByMonth4")
	public List<Object[]> calculateTotalOrderByMonth4(@RequestParam int month, @RequestParam int year) {
		return orderService.calculateTotalOrderByMonth4(month, year);
	}

	//Trả hàng
	@GetMapping("/calculateTotalOrderByMonth5")
	public List<Object[]> calculateTotalOrderByMonth5(@RequestParam int month, @RequestParam int year) {
		return orderService.calculateTotalOrderByMonth5(month, year);
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
