package com.poly.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.poly.entity.Account;
import com.poly.service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendOtpEmail(Account account, String otp) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("khoisieudeptrai2304@gmail.com");
            helper.setTo(account.getEmail());
            helper.setSubject("Xác nhận đơn hàng");

            String content  = "<!DOCTYPE html>"
                    + "<html lang='en'>"
                    + "<head>"
                    + "<meta charset='UTF-8'>"
                    + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
                    + "<title>Xác nhận đơn hàng</title>"
                    + "<style>"
                    + "body {"
                    + "    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;"
                    + "    line-height: 1.6;"
                    + "    color: #333;"
                    + "    margin: 0;"
                    + "    padding: 0;"
                    + "}"
                    + ".container {"
                    + "    width: 80%;"
                    + "    margin: 0 auto;"
                    + "}"
                    + "header {"
                    + "    background-color: #007bff;"
                    + "    color: #fff;"
                    + "    text-align: center;"
                    + "    padding: 20px 0;"
                    + "}"
                    + "section {"
                    + "    padding: 20px;"
                    + "}"
                    + "footer {"
                    + "    background-color: #f4f4f4;"
                    + "    padding: 10px 0;"
                    + "    text-align: center;"
                    + "}"
                    + "</style>"
                    + "</head>"
                    + "<body>"
                    + "<div class='container'>"
                    + "<header>"
                    + "    <h1>Xác nhận đơn hàng</h1>"
                    + "</header>"
                    + "<section>"
                    + "    <p>Xin chào,</p>"
                    + "    <p>Mã OTP của bạn là: <h2>" + otp + "</h2></p>"
                    + "    <p>Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi.</p>"
                    + "</section>"
                    + "<footer>"
                    + "    <p>Bạn nhận được email này vì bạn đã đăng ký sử dụng dịch vụ của chúng tôi.</p>"
                    + "</footer>"
                    + "</div>"
                    + "</body>"
                    + "</html>";

            helper.setText(content, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
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

