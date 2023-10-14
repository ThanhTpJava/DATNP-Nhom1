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
@Table(name = "Voucher")
public class Voucher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int IdVoucher;

	@Column(name = "NameVoucher", columnDefinition = "NVARCHAR(150)")
	private String NameVoucher;

	@Column(name = "discount")
	private Float discount;
}
