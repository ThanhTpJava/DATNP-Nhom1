package com.poly.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.poly.entity.Account;
import com.poly.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService  {
	 @Autowired
	    private JavaMailSender javaMailSender;
	 @Override
	    public void sendOtpEmail(Account account, String otp) {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setFrom("khoisieudeptrai2304@gmail.com");
	        message.setTo(account.getEmail());
	        message.setSubject("Xác nhận đơn hàng");
	        message.setText("Mã OTP của bạn là: " + otp);
	        javaMailSender.send(message);
	    }
	@Override
	public void sendOTPResetPassword(Account account, String otp, String subject) {
		// TODO Auto-generated method stub
		SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("khoisieudeptrai2304@gmail.com");
        message.setTo(account.getEmail());
        message.setSubject(subject);
        message.setText("Mã OTP của bạn là: " + otp);
        javaMailSender.send(message);
	}
}
