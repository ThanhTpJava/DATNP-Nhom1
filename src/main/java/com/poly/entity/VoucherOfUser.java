package com.poly.entity;

import java.util.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "voucher_of_user")
public class VoucherOfUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Integer id;
	
	@ManyToOne
	@JoinColumn(name = "username")
	Account username;
	
	@ManyToOne
	@JoinColumn(name = "voucher_code")
	Voucher voucherCode;

	@Column(name = "received_date")
	Date receivedDate;

	@Column(name = "status")
	private boolean status;
}
