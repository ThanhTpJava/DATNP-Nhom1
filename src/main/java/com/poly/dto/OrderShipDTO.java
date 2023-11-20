package com.poly.dto;

import com.poly.entity.Account;

import java.util.Date;

public record OrderShipDTO(

        String orderId,
        Date createDate,
        double TotalAmount,
        String address,

        Account account,
        java.util.List<Integer> orderStatus
) {

}
