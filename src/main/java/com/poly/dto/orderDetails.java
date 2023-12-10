package com.poly.dto;



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
public class orderDetails {
	private int id;
    private ProductDTO product;
    private int quantity;
    private double subtotal;
}
