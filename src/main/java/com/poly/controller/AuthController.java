package com.poly.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController { 
	@Autowired
	UserService userService;

	@GetMapping(value = "/auth/login/form")
	public String loginForm() {
		System.out.println("------------------------------------------------------------------");
		printAuthenticationInfo();
		System.out.println("------------------------------------------------------------------");
		getLoginAuthority();
		return "/admin/login.html";
	}

	@Autowired
	HttpSession session;

	@GetMapping("/auth/login/success")
	public String loginSuccess() {
		System.out.println("------------------------------------------------------------------");
		printAuthenticationInfo();
		System.out.println("------------------------------------------------------------------");
        return switch (getLoginAuthority()) {
            case "ROLE_ADMIN" -> "redirect:/admin/products";
            case "ROLE_USER" -> "redirect:/user/home";
            case "ROLE_SALE" -> "redirect:/";
            case "ROLE_STOCK" -> "redirect:/";
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
		session.setAttribute("authentication", null);
		return "forward:/auth/login/form";
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

	public String getLoginAuthority() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName(); // Lấy tên người dùng
		String role = "";

		if (authentication.isAuthenticated()) {
			// Người dùng đã được xác thực
			switch (authentication.getAuthorities().stream().findFirst().orElse(null).getAuthority()) {
				case "ROLE_ADMIN":
					role = "ROLE_ADMIN";
					break;
				case "ROLE_USER":
					role = "ROLE_USER";
					break;
				case "ROLE_SALE":
					role = "ROLE_SALE";
					break;
				case "ROLE_STOCK":
					role = "ROLE_STOCK";
					break;
				default:
					role = "No specific role";
					break;
			}
//			return "Welcome, " + username + "! You are " + role + ".";
			return role;
		} else {
			// Người dùng chưa xác thực
			return null;
		}
	}
}
