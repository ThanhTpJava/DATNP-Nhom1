package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.service.BannerService;

@Controller
public class BannerController {
	@Autowired
	BannerService bannerService;
}
