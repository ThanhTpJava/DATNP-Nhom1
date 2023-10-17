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
	private int OrderItemID;

	@ManyToOne
	@JoinColumn(name = "OrderID")
	private Order order;

	@ManyToOne
	@JoinColumn(name = "ProductID")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "UserID")
	private Account account;

	@Column(name = "Quantity")
	private Integer Quantity;

	@Column(name = "Subtotal"/*, precision = 10, scale = 2*/)
	private double Subtotal;
}
