package com.poly.dto;

import java.util.Collections;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.poly.entity.Order;

@Component
public class OrdersShipDTOMapper implements Function<Order, OrderShipDTO>{

	@Override
	public OrderShipDTO apply(Order od) {
		// TODO Auto-generated method stub
		return new OrderShipDTO(
				od.getId(),
				od.getCreateDate(),
				od.getTotalAmount(),
				od.getAddress(),
				od.getDelivery_phone(),
				od.getAccount(),
				od.getOrderDetails().stream().toList(),
				od.getOrderStatuses().stream()
						.map(odstt -> odstt.getStatus().getId())
						.findFirst().orElse(99)
		);
	}

	}


