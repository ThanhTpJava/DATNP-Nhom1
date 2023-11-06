package com.poly.dto;

import java.util.Date;
import java.util.List;

import com.poly.enums.GenderEnum;

public record AccountDTO(
		String username,
		String name,
		String surname,
		String hometown,
		String residentialAddress,
		String deliveryAddress,
		String idCard,
		Date dateOfBirth,
		GenderEnum gender,
		String email,
		String phoneNumber,
		List<String> roleName
	)
{

}
