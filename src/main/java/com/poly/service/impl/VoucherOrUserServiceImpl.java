package com.poly.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.VoucherOfUserDAO;
import com.poly.dto.ListUsersRewardsDTO;
import com.poly.dto.ListUsersRewardsDTOMapper;
import com.poly.entity.Account;
import com.poly.entity.Voucher;
import com.poly.entity.VoucherOfUser;
import com.poly.service.AccountService;
import com.poly.service.VoucherOfUserService;
import com.poly.service.VoucherService;

@Service
public class VoucherOrUserServiceImpl implements VoucherOfUserService{
	
	@Autowired
	VoucherService voucherService;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	VoucherOfUserDAO voucherOfUserDAO;

	@Autowired
	ListUsersRewardsDTOMapper listUsersRewardsDTOMapper;
	@Override
	public List<ListUsersRewardsDTO> getListUsersRewards() {
		// TODO Auto-generated method stub
		return voucherOfUserDAO.findAllListVoucherOrUserReceivedDateDesc()
				.stream().map(listUsersRewardsDTOMapper).collect(Collectors.toList());
	}

	@Override
	public void saveVoucherForUser(String username, String voucher_code, Date receivedDate, Boolean status) {
		// TODO Auto-generated method stub
		Account acc = accountService.findById(username);
		Voucher voucher = voucherService.findByID(voucher_code);
		
		VoucherOfUser vcus = new VoucherOfUser();
		vcus.setUsername(acc);
		vcus.setVoucherCode(voucher);
		vcus.setReceivedDate(receivedDate);
		vcus.setStatus(status);
		
		voucherOfUserDAO.save(vcus);
	}

}
