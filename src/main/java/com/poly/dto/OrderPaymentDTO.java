package com.poly.dto;


import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class OrderPaymentDTO {
	private String id;
    private AccountDTOPayApi account;
    private Date createDate;
    private double totalAmount;
    private String address;
    private String delivery_phone;
    private List<orderDetails> orderDetails;
}
