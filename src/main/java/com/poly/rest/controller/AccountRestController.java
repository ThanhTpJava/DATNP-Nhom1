package com.poly.rest.controller;

import java.io.Console;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poly.dto.AccountDTO;
import com.poly.entity.Account;
import com.poly.entity.Authority;
import com.poly.entity.Role;
import com.poly.service.AccountService;
import com.poly.service.AuthorityService;
import com.poly.service.EmailService;
import com.poly.service.RoleService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
//@RequestMapping("/rest/accounts")
@RequestMapping("/auth")
public class AccountRestController {
	@Autowired
	AccountService accountService;

	@Autowired
	AuthorityService auService;

	@Autowired
	RoleService roleService;

	@Autowired
	EmailService emailService;

	@GetMapping
	public List<Account> getAccounts(@RequestParam("admin") Optional<Boolean> admin) {
		if (admin.orElse(false)) {
			return accountService.getAdministrators();
		}
		return accountService.findAll();
	}

	@GetMapping("{username}")
	public Account getOne(@PathVariable("username") String username) {
		return accountService.findById(username);
	}

	@PostMapping("/signup/newaccount")
	public ResponseEntity<Account> createAccount(@RequestBody @Valid Account acc) {
		if (accountService.userExit(acc.getUsername()) || accountService.emailExit(acc.getEmail())) {
			return ResponseEntity.badRequest().build();
		}

		Account createAccount = accountService.createAccount(acc);

		Role userRole = roleService.findRole("ROLE_USER");

		Authority userAuthorities = new Authority();
		userAuthorities.setAccount(createAccount);
		userAuthorities.setRole(userRole);
		auService.create(userAuthorities);

		return new ResponseEntity<>(createAccount, HttpStatus.CREATED);
	}

	@GetMapping("/checkusernameexits/{username}")
	public Map<String, Boolean> checkUsernameAvailability(@PathVariable("username") String username) {
		Map<String, Boolean> response = new HashMap<>();

		// Kiểm tra xem tên người dùng đã tồn tại trong cơ sở dữ liệu hay chưa
		boolean isAvailable = !accountService.userExit(username);

		response.put("available", isAvailable);

		return response;
	}

	@GetMapping("/checkemailexits/{email}")
	public Map<String, Boolean> checkEmailAvailability(@PathVariable("email") String email) {
		Map<String, Boolean> response = new HashMap<>();

		boolean isAvailable = !accountService.emailExit(email);
		response.put("available", isAvailable);
		return response;
	}

	@PostMapping(value = { "/account/detail/update" })
	public ResponseEntity<Void> updateAccount(@RequestBody AccountDTO accDTO) {
		AccountDTO accountDTO = accDTO;
		System.out.println(accountDTO.deliveryAddress());
		accountService.updateAccount(accDTO);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/account/detail/{username}")
	public ResponseEntity<AccountDTO> getDetailAccount(@PathVariable String username) {

		return ResponseEntity.ok(accountService.getDetailAccountDTO(username));
	}

	@GetMapping("/forgotpassword/{username}")
	public ResponseEntity<Map<String, String>> forgotPassword(@PathVariable String username,
			HttpServletRequest request) {
		System.out.println(username);
		boolean isAvailable = accountService.userExit(username);
		if (isAvailable) {
			Account accForgotPassword = accountService.findById(username);
			String otp = generateOtp();
			HttpSession session = request.getSession();
			session.setAttribute("otp", otp);

			emailService.sendOTPResetPassword(accForgotPassword, otp, "ĐẶT LẠI MẬT KHẨU");

			String maskedEmail = maskEmail(accForgotPassword.getEmail());
			System.out.println(maskedEmail);

			Map<String, String> response = new HashMap<>();
			response.put("message", "success");
			response.put("maskedEmail", maskedEmail);

			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			Map<String, String> response = new HashMap<>();
			response.put("message", "errors");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/forgotpassword/confirmotp")
	public ResponseEntity<?> confirmOtp(@RequestBody Map<String, Object> requestData, HttpServletRequest request) {

		String OTP = (String) requestData.get("OTP");
		String newPassword = (String) requestData.get("newPassword");
		String username = (String) requestData.get("username");

		System.out.println("otp: " + OTP);
		System.out.println("new pass: " + newPassword);
		System.out.println("username: " + username);
		HttpSession session = request.getSession();
		String storedOtp = (String) session.getAttribute("otp");
		if (storedOtp != null && storedOtp.equals(OTP)) {
			accountService.updatePasswordByUsername(newPassword, username);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	private String generateOtp() {
		Random random = new Random();
		int otpValue = 100000 + random.nextInt(900000);
		return String.valueOf(otpValue);
	}

	public static String maskEmail(String email) {

		int atIndex = email.indexOf('@');

		if (atIndex >= 5) {
	        return email.substring(0, atIndex - 5) + "*****" + email.substring(atIndex);
	    } else {
	        return "*****" + email.substring(atIndex);
	    }
	}
}
