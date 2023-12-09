package com.poly.dto;

import com.poly.entity.Account;
import com.poly.entity.OrderDetail;

import java.util.Date;
import java.util.List;

public record OrderShipDTO(

        String orderId,
        Date createDate,
        double TotalAmount,
        String address,
        String delivery_phone,
        Account account,

        List<OrderDetail> orderDetail,
        int orderStatus
) {

}
