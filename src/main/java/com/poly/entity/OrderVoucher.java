package com.poly.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Table(name = "order_voucher", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"orderid","voucher_code"})
})

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderVoucher implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "orderid")
	private Order order;

	@ManyToOne
	@JoinColumn(name = "voucher_code")
	private Voucher voucher;
}
