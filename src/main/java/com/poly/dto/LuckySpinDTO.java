package com.poly.dto;

public record LuckySpinDTO(
	String voucher_code,
	String review,
	String description,
	int quantity,
	int rate	
	) {

}
