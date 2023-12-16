package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LuckySpinController {

	@RequestMapping("/user/luckyspin")
	public String luckySpinPage() {
		return "/user/lucky-spin";
	}
}
