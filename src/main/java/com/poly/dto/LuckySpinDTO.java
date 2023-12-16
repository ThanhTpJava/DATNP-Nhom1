package com.poly.dto;

public record LuckySpinDTO(

	Integer id,

	String voucher_code,
	String review,
	String description,
	int quantity,
	int rate	
	) {

}
