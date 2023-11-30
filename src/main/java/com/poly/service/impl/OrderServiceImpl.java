package com.poly.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.poly.dao.OrderStatusDAO;
import com.poly.dao.StatusDAO;
import com.poly.dto.OrderShipDTO;
import com.poly.dto.OrdersShipDTOMapper;
import com.poly.entity.OrderStatus;
import com.poly.entity.Status;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.dao.OrderDAO;
import com.poly.dao.OrderDetailDAO;
import com.poly.dto.OrdersDTO;
import com.poly.dto.OrdersDTOMapper;
import com.poly.entity.Order;
import com.poly.entity.OrderDetail;
import com.poly.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	OrderDAO dao;
	
	@Autowired
	OrderDetailDAO ddao;
	
	@Autowired
	OrdersDTOMapper orderDtoMapper;

	@Autowired
	OrdersShipDTOMapper ordersShipDTOMapper;

	@Autowired
	OrderStatusDAO orderStatusDAO;

	@Autowired
	StatusDAO statusDAO;

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
	
	public Order findById(String id) {
		return dao.findById(id).get();
	}
	
	public List<Order> findByUsername(String username) {
		return dao.findByUsername(username);
	}

	@Override
	public List<OrderShipDTO> findAll() {
		return dao.findAll().stream().map(ordersShipDTOMapper).collect(Collectors.toList());
	}

//	public Order EditbyID(Order updatedOrder) {
//		Optional<Order> optionalOrder = dao.findById(updatedOrder.getId());
//		if (optionalOrder.isPresent()) {
//			Order existingOrder = optionalOrder.get();
//			existingOrder.setStatus(updatedOrder.getStatus());
//			return dao.save(existingOrder);
//		} else {
//			// Xử lý trường hợp không tìm thấy đơn hàng
//			throw new NoSuchElementException("Không tìm thấy đơn hàng với ID: " + updatedOrder.getId());
//		}
//	}

	public void delete(String id) {
		dao.deleteById(id);
	}

//	@Override
//	public List<Object[]> deliveredOrdersByMonth() {
//		return dao.findDeliveredOrdersByMonth();
//	}

	@Override
	public List<String> getListOrderId() {
		return dao.getAllOrderId();
	}

	@Override
	public List<OrdersDTO> findOrdersDTOsByUsername(String username) {
		// TODO Auto-generated method stub
		return dao.findOrderDTOByUsername(username).stream().map(orderDtoMapper).collect(Collectors.toList());
	}
	@Override
	public OrderShipDTO findByIdShip(String id) {
		Order order = dao.findById(id).orElse(null);

		if (order != null) {

Integer statusId =order.getOrderStatuses().get(0).getStatus().getId();
			System.out.println(statusId);
			return new OrderShipDTO(
					order.getId(),
					order.getCreateDate(),
					order.getTotalAmount(),
					order.getAddress(),
					order.getDelivery_phone(),
					order.getAccount(),
					statusId
			);
		} else {
			return null; // or throw an exception or handle accordingly based on your requirements
		}
	}
    @Override
	@Transactional
	public void updateOrderStatus(String orderId, Integer statusId) {
		// Retrieve the Order and Status entities
		Order order = dao.findById(orderId).orElse(null);
		Status status = statusDAO.findById(statusId).orElse(null);

		if (order != null && status != null) {
			orderStatusDAO.deleteByOrderId(order.getId());

			// Create a new OrderStatus entity with the updated status
			OrderStatus orderStatus = new OrderStatus();
			orderStatus.setOrder(order);
			orderStatus.setStatus(status);

			// Save the new OrderStatus
			orderStatusDAO.save(orderStatus);
		} else {
			throw new IllegalArgumentException("Order or Status not found");
		}
	}

	@Override
	public Double calculateTotalRevenue() {
		return dao.calculateTotalRevenue();
	}

	@Override
	public List<Object[]> calculateTotalRevenueByDate(Integer day, Integer month, Integer year) {
		return dao.calculateTotalRevenueByDate(day, month, year);
	}

	@Override
	public List<Object[]> calculateTotalRevenueByMonth(Integer month, Integer year) {
		return dao.calculateTotalRevenueByMonth(month, year);
	}

	@Override
	public List<Object[]> calculateTotalOrderByMonth(Integer month, Integer year) {
		return  dao.calculateTotalOrderByMonth(month, year);
	}

	@Override
	public Double calculateTotalRevenueByYear(Integer year) {
		return dao.calculateTotalRevenueByYear(year);
	}

	@Override
	public Integer calculateTotalOrderByYear(Integer year) {
		return dao.calculateTotalOrderByYear(year);
	}

	@Override
	public List<Order> findAll1() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}


}
