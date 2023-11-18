package com.poly.entity;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name ="Orders")
public class Order {
	@Id
	@Column(name = "orderid", columnDefinition = "varchar(8)")
	private String id;

	@ManyToOne
	@JoinColumn(name = "UserID")
	private Account account;

	@Column(name = "createDate")
	private Date createDate;

	@Column(name = "TotalAmount"/*, precision = 10, scale = 2*/)
	private double TotalAmount;

	@Column(name = "address", columnDefinition = "nvarchar(200)")
	private String address;

	@ManyToOne
	@JoinColumn(name = "voucherId")
	private Voucher voucher; // Trường này để lưu mã voucher áp dụng cho đơn hàng

	@JsonIgnore
	@OneToMany(mappedBy = "order")
	List<OrderDetail> orderDetails;

	@JsonIgnore
	@OneToMany(mappedBy = "order")
	List<OrderStatus> orderStatuses;

	@JsonIgnore
	@OneToMany(mappedBy = "orderResponsi")
	List<Responsibility> listOrderResponsi;

}
