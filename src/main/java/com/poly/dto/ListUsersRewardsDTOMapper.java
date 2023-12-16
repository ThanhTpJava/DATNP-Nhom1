package com.poly.dto;

import java.util.function.Function;

import com.poly.entity.VoucherOfUser;

public class ListUsersRewardsDTOMapper implements Function<VoucherOfUser, ListUsersRewardsDTO>{

	@Override
	public ListUsersRewardsDTO apply(VoucherOfUser vcus) {
		// TODO Auto-generated method stub
		return new ListUsersRewardsDTO(
				vcus.getUsername().getUsername(),
				vcus.getUsername().getSurname(),
				vcus.getUsername().getName(),
				vcus.getVoucherCode().getReview(),
				vcus.getReceived_Date());
	}

}
