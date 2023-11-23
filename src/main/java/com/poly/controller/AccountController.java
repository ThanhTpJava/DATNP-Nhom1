package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class AccountController {
	@RequestMapping("/account/detail")
	public String accountDetail() {
		return "user/account-detail-for-user";
	}
	
	@RequestMapping("/staff/account/detail")
	public String staffAccountDetail() {
		return "admin/staffaccountdetail";
	}
}
