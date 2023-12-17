package com.poly.entity;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Voucher")
public class Voucher {

	@Id
	@Column(name = "voucher_code", columnDefinition = "NVARCHAR(150)")
	private String voucherCode;

	@Column(name = "review", columnDefinition = "NVARCHAR(150)")
	private String review;

	@Column(name ="description", columnDefinition = "NVARCHAR(200)")
	public String description;

	@Column(name = "discount")
	private Double discount; //giảm giá

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "total_requested")
	private Double totalRequested; //tổng tiền hóa đơn yêu cầu

	@Column(name = "startDate")
	private Date startDate;

	@Column(name = "endDate")
	private Date endDate;

	@Column(name ="rate")
	public int rate;

	@Column(name ="status")
	public boolean status;

	@JsonIgnore
	@OneToOne(mappedBy = "voucherCode")
	private LuckySpin luckySpin;

	@JsonIgnore
	@OneToMany(mappedBy = "voucherCode")
	List<VoucherOfUser> listVoucherOfUsers;
	
	@JsonIgnore
	@OneToMany(mappedBy = "voucher",  fetch = FetchType.EAGER)
	List<OrderVoucher> listOrderVouchers;
}