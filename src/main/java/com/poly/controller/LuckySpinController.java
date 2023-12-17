package com.poly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.dto.ListUsersRewardsDTO;
import com.poly.service.VoucherOfUserService;


@Controller
public class LuckySpinController {

	@Autowired
	VoucherOfUserService voucherOfUserService;
	
	@RequestMapping("/user/luckyspin")
	public String luckySpinPage(Model model) {
		
		List<ListUsersRewardsDTO> listUsersRewards = voucherOfUserService.getListUsersRewards();
		model.addAttribute("listUsersRewards", listUsersRewards);
		return "/user/lucky-spin";
	}
}
