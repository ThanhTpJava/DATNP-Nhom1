package com.poly.dto;

import java.util.Date;

public record LuckySpinDTO(

	Integer id,

	String voucher_code,
	String review,
	String description,

	Date endDate,
	int quantity,
	int rate

	) {

}
