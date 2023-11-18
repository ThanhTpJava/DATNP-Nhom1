package com.poly.dto;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.poly.entity.Order;

@Component
public class OrdersDTOMapper implements Function<Order, OrdersDTO>{

	@Override
	public OrdersDTO apply(Order od) {
		// TODO Auto-generated method stub
		return new OrdersDTO(
				od.getId(), 
				od.getCreateDate(), 
				od.getTotalAmount(), 
				od.getAddress(), 
				od.getOrderStatuses().stream()
				.map(odstt -> odstt.getStatus().getId())
				.findFirst().orElse(99)
				);
	}

}
