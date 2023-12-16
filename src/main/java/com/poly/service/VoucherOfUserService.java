package com.poly.service;

import java.util.Date;
import java.util.List;

import com.poly.dto.ListUsersRewardsDTO;

public interface VoucherOfUserService {
	void saveVoucherForUser(String username, String voucher_code, Date receivedDate, Boolean status);
	
	List<ListUsersRewardsDTO> getListUsersRewards();
}
