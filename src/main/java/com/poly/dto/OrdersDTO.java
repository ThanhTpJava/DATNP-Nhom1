package com.poly.dto;

import java.util.Date;

public record OrdersDTO(
		
	String orderId,
	Date createDate,
	double TotalAmount,
	String address,
	int orderStatus
		) {

}
