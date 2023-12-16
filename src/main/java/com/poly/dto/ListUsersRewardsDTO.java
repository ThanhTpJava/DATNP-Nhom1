package com.poly.dto;

import java.util.Date;

public record ListUsersRewardsDTO(
	String username,
	String subName,
	String name,
	String review,
	Date receivedDate 
	) {

}
