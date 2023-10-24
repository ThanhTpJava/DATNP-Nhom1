package com.poly.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
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
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.poly.entity.Account;
import com.poly.entity.Category;
import com.poly.entity.Product;
import com.poly.service.AccountService;
import com.poly.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class AuthController { 
	@Autowired
	UserService userService;

	@GetMapping(value = "/auth/login/form")
	public String loginForm() {
		return "/admin/login.html";
	}

	@Autowired
	HttpSession session;
//	@Autowired
//    private AuthenticationManager authenticationManager;
//
//    @PostMapping("/login")
//    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
//        try {
//            // Create an authentication token with the provided username and password
//            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
//
//            // Authenticate the user
//            Authentication authentication = authenticationManager.authenticate(authRequest);
//
//            // Set the authenticated user in the SecurityContext
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            // Redirect to the successful login page
//            return "redirect:/auth/login/success";
//        } catch (AuthenticationException e) {
//            // Handle authentication failure (invalid credentials)
//            return "redirect:/login?error";
//        }
//    }
//
//	
	
	@GetMapping("/auth/login/success")
	public String x(@RequestParam("username") String username) {
		UserDetails userDetails = userService.loadUserByUsername(username);
		String[] roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.toArray(String[]::new);
		for (String role : roles) { // Loop through the roles retrieved from authorities
			System.out.println(role);
			System.out.println(roles.toString());
			if ("ROLE_ADMIN".equalsIgnoreCase(role)) {
				return "redirect:/admin/products"; // Redirect to admin page
			} else if ("ROLE_USER".equalsIgnoreCase(role)) {
				return "redirect:/"; // Redirect to user's product list page
			}
			System.out.println(role);
			System.out.println(roles.toString());
		}
		return "redirect:/";

	}

//	private  Account  {
//	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//	    if (authentication == null || AnonymousAuthenticationToken.class.
//	      isAssignableFrom(authentication.getClass())) {
//	        return false;
//	    }
//	    return authentication.isAuthenticated();
//	} 

	@RequestMapping("/oauth2/login/success")
	public String OAuthLogin(OAuth2AuthenticationToken oauth2, Model model) {
		userService.loginFormOAuth2(oauth2);
		model.addAttribute("message", "Success");
		return "redirect:/user/home";
	}

	@RequestMapping("/auth/login/error")

	public String error(Model model) {
		model.addAttribute("message", "Sai thông tin đăng nhập!");
		return "forward:/auth/login/form";
	}

	@RequestMapping("/auth/logoff/success")
	public String logoff(Model model) {

		model.addAttribute("message", "Đăng xuất thành công!");
		session.setAttribute("authentication", null);
		return "forward:/auth/login/form";
	}
	

//	@RequestMapping("/auth/access/denied")
//	public String denied(Model model) {
//		model.addAttribute("message", "Bạn không có quyền truy xuất!");
//		return "forward:/auth/login/form";
//	}
	
	@RequestMapping("/user/detail_user/{username}")
    public String showUserDetail(@PathVariable String username, Model model) {
        Account user = userService.findById(username);
        if (user != null) {
            model.addAttribute("user", user);
            return "/user/detail-user";
        } else {
            return "user/user-not-found";
        }
	}
	
	@PostMapping("update")
	private String update(@Valid @ModelAttribute("accounts") Optional<Account> account, Model model) {
		Account save = userService.save(account.get());
		model.addAttribute("accounts", save);
		return "redirect:/user/home";

	}
}
