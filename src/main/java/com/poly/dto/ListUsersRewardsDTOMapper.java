package com.poly.dto;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.poly.entity.VoucherOfUser;

@Component
public class ListUsersRewardsDTOMapper implements Function<VoucherOfUser, ListUsersRewardsDTO>{

	@Override
	public ListUsersRewardsDTO apply(VoucherOfUser vcus) {
		// TODO Auto-generated method stub
		return new ListUsersRewardsDTO(
				vcus.getUsername().getUsername(),
				vcus.getUsername().getSurname(),
				vcus.getUsername().getName(),
				vcus.getVoucherCode().getReview(),
				vcus.getReceivedDate());
	}

}
