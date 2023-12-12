package com.poly.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.dao.AccountDAO;
import com.poly.dto.AccountDTO;
import com.poly.entity.Account;
import com.poly.service.AccountService;
import com.poly.service.AuthorityService;
import com.poly.service.UserService;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.Optional;

@Controller
public class AuthController { 
	@Autowired
	UserService userService;

	@Autowired
	AccountDAO accountDAO;

	@Autowired
	AccountService accService;
	
	@Autowired
	AuthorityService authService;

	@GetMapping(value = "/auth/login/form")
	public String loginForm() {
		System.out.println("------------------------------------------------------------------");
		printAuthenticationInfo();
		System.out.println("------------------------------------------------------------------");
		authService.getLoginAuthority();
		return "/admin/login.html";
	}

	@Autowired
	HttpSession session;

	@GetMapping("/auth/login/success")
	public String loginSuccess() {
		System.out.println("------------------------------------------------------------------");
		printAuthenticationInfo();
		System.out.println("------------------------------------------------------------------");
        return switch (authService.getLoginAuthority()) {
            case "ROLE_ADMIN" -> "redirect:/admin/products";
            case "ROLE_USER" -> "redirect:/user/home";
            case "ROLE_SALE" -> "redirect:/admin/sale";
            case "ROLE_STOCK" -> "redirect:/admin/stock";
			case "ROLE_SHIP" -> "redirect:/admin/shipping";
            default -> "forward:/auth/login/form";
//			default -> throw new IllegalStateException("Unexpected value: " + getLoginAuthority());
			//
		};

	}

	@RequestMapping("/auth/login/error")
	public String error(Model model) {
		System.out.println("Login lỗi");
		return "forward:/auth/login/form";
	}

	@RequestMapping("/auth/logoff/success")
	public String logoff(Model model) {
		model.addAttribute("message", "Đăng xuất thành công!");
		session.setAttribute("authentication",null);
		return "forward:/auth/login/form";
	}
	
	@RequestMapping("/login/oauth2/success")
	public String oauthSuccess(OAuth2AuthenticationToken oauth2) {
		 OAuth2User oauth2User = oauth2.getPrincipal();
		    
		    // In thông tin người dùng ra console
		    System.out.println("User Name: " + oauth2User.getName());
		    System.out.println("User Attributes: " + oauth2User.getAttributes());
		return "redirect:/user/home";
	}

	public void printAuthenticationInfo() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    
	    try {
	        ObjectMapper objectMapper = new ObjectMapper();
	        String authenticationJson = objectMapper.writeValueAsString(authentication);
	        System.out.println("Authentication Info: " + authenticationJson);
	    } catch (JsonProcessingException e) {
	        System.out.println("Failed to convert Authentication to JSON: " + e.getMessage());
	    }
	}

	

	@CrossOrigin("*")
	@ResponseBody
	@RequestMapping("/rest/security/authentication")
	public Object getAuthentication(Model model) {
        return session.getAttribute("authentication");
	}

}
