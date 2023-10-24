package com.poly.controller;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.security.core.AuthenticationException;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.entity.Account;
import com.poly.service.UserService;

import jakarta.servlet.http.HttpSession;

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
		getLoginAuthority();
		return "redirect:/home/index";
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
	
	public void getLoginAuthority() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.isAuthenticated()) {
            // Người dùng đã được xác thực
            String username = authentication.getName(); // Lấy tên người dùng
            boolean isAdmin = authentication.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

            if (isAdmin) {
                // Thực hiện hành động dựa trên quyền của người dùng (ví dụ: kiểm tra quyền admin)
            	System.out.println("Welcome, " + username + "! You are an admin.");
                
            } else {
            	System.out.println("Welcome, " + username + "! You are a regular user.");
                
            }
        } else {
            // Người dùng chưa xác thực
        	System.out.println("You are not logged in.");
           
        }
	}
}
