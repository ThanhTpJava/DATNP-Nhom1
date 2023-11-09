package com.poly.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.DeserializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.dao.OrderDAO;
import com.poly.dao.OrderDetailDAO;
import com.poly.entity.Order;
import com.poly.entity.OrderDetail;
import com.poly.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	OrderDAO dao;
	
	@Autowired
	OrderDetailDAO ddao;
	
	public Order create(JsonNode orderData) {
		ObjectMapper mapper = new ObjectMapper();
//		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		Order order = mapper.convertValue(orderData, Order.class);
		dao.save(order);
		
		TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {};
		List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type)
				.stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());
		ddao.saveAll(details);
		
		return order;
	}
	
	public Order findById(Long id) {
		return dao.findById(id).get();
	}
	
	public List<Order> findByUsername(String username) {
		return dao.findByUsername(username);
	}

	@Override
	public List<Order> findAll() {
		return dao.findAll();
	}

	public Order EditbyID(Order updatedOrder) {
		Optional<Order> optionalOrder = dao.findById(updatedOrder.getId());
		if (optionalOrder.isPresent()) {
			Order existingOrder = optionalOrder.get();
			existingOrder.setStatus(updatedOrder.getStatus());
			return dao.save(existingOrder);
		} else {
			// Xử lý trường hợp không tìm thấy đơn hàng
			throw new NoSuchElementException("Không tìm thấy đơn hàng với ID: " + updatedOrder.getId());
		}
	}

	public void delete(Long id) {
		dao.deleteById(id);
	}
}
