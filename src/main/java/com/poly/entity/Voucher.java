package com.poly.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Voucher")
public class Voucher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name", columnDefinition = "NVARCHAR(150)")
	private String name;

	@Column(name = "discount")
	private Float discount; //giảm giá

	@Column(name = "startDate")
	private Date startDate;

	@Column(name = "endDate")
	private Date endDate;

	@Column(name ="code_voucher")
	public String code_voucher;

	@Column(name ="description")
	public String description;

	@ManyToOne
	@JoinColumn(name = "VoucherTypeId")
	private VoucherType voucherType;


}
