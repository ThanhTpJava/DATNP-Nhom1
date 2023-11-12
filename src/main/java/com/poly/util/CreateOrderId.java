package com.poly.util;

import com.poly.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class CreateOrderId {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 8;

    @Autowired
    OrderService orderService;

    public String generateInvoiceCode() {
        Random random = new Random();
        String code;
        List<String> orderIdExists = orderService.getListOrderId();
        do {
            StringBuilder codeBuilder = new StringBuilder();
            for (int i = 0; i < CODE_LENGTH; i++) {
                int index = random.nextInt(CHARACTERS.length());
                codeBuilder.append(CHARACTERS.charAt(index));
            }
            code = codeBuilder.toString();

        } while (orderIdExists.contains(code));

        return code;
    }

}
