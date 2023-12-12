package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.service.AuthorityService;

@Controller

public class AccountController {
	@Autowired
	AuthorityService authService;
	
	@RequestMapping("/account/detail")
	public String accountDetail() {
		return "user/account-detail-for-user";
	}
	
	@RequestMapping("/staff/account/detail")
	public String staffAccountDetail() {
		return switch (authService.getLoginAuthority()) {
        case "ROLE_SALE" -> "sale/my-account";
        case "ROLE_STOCK" -> "stock/my-account";
        default -> "forward:/auth/login/form";
	};
	}
}
