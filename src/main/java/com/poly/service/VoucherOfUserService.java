package com.poly.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.dto.ListUsersRewardsDTO;
import com.poly.entity.VoucherOfUser;

public interface VoucherOfUserService{
	void saveVoucherForUser(String username, String voucher_code, Date receivedDate, Boolean status);
	
	List<ListUsersRewardsDTO> getListUsersRewards();

	
}
