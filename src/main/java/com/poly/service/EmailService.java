package com.poly.service;


import com.poly.entity.Account;

public interface EmailService {
    void sendOtpEmail(Account account, String otp);
}
