package com.poly.rest.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import com.poly.entity.Order;
import com.poly.service.AccountService;
import com.poly.service.EmailService;
import com.poly.entity.Account;
@CrossOrigin("*")
@RequestMapping("/otp")
@RestController
public class OtpRestController {
	@Autowired
	  private AccountService accountService;
	  @Autowired
    private EmailService emailService;
	  
	  @PostMapping("/confirm-otp")
	  public ResponseEntity<?> confirmOtp(@RequestParam("otp") String otp, HttpServletRequest request) {
	      HttpSession session = request.getSession();
	      String storedOtp = (String) session.getAttribute("otp");
	      if (storedOtp != null && storedOtp.equals(otp)) {
	          
	    	  return ResponseEntity.ok().build();
	      } else {
	    	  return ResponseEntity.badRequest().build();
	      }
	  }



    @PostMapping("/send-otp")
    @ResponseBody
    public ResponseEntity<?> sendOtp(@RequestParam("email") String email, HttpServletRequest request) {
        Account account = accountService.findByEmail(email);
        if (account != null) {
            String otp = generateOtp();
            HttpSession session = request.getSession();
            session.setAttribute("otp", otp);
            emailService.sendOtpEmail(account, otp);
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
	
}
