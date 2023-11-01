package com.poly.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
//@JsonIgnoreProperties(ignoreUnknown = true)

public class OrderDetail implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

//	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "OrderID")
	private Order order;

	@ManyToOne
	@JoinColumn(name = "ProductID")
	private Product product;

	@Column(name = "Quantity")
	private Integer Quantity;

	@Column(name = "Subtotal"/*, precision = 10, scale = 2*/)
	private double Subtotal;
}
