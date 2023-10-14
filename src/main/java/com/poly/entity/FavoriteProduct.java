package com.poly.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FavoriteProducts")
public class FavoriteProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int FavoriteProductID;

	@ManyToOne
	@JoinColumn(name = "ProductID")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "UserID")
	private Account account;
}
