package com.poly.entity;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name ="OrderDetails")
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "OrderID")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "ProductID")
    private Product product;

    @Column(name = "Quantity")
    private Integer Quantity;

    @Column(name = "Subtotal")
    private double Subtotal;
}
