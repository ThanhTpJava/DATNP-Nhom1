package com.poly.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poly.enums.GenderEnum;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Accounts")
@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor

public class Account implements Serializable{
	@Id
	@Size(min = 5, message = "Tên tài khoản quá ngắn")
	@NotBlank(message = "Tài khoản không được trống")
	@Column(name ="username", nullable = false, columnDefinition = "NVARCHAR(30)")
	private String username;

	@Column(name ="name", nullable = false, columnDefinition = "NVARCHAR(20)")
	@NotBlank(message = "Nhập tên của bạn")
	private String name;

	@Column(name ="sur_name", nullable = false, columnDefinition = "NVARCHAR(30)")
	@NotBlank(message = "Nhập họ và tên đệm của bạn")
	private String surname;

	@Column(name ="pass_word", nullable = false, columnDefinition = "NVARCHAR(80)")
	@Size(min = 5, message = "Mật khẩu quá ngắn")
	@NotBlank(message = "Mật khẩu không được trống")
	private String password;

	@Column(name ="home_town", columnDefinition = "NVARCHAR(80)") //quê quán nhập hay ko nhập điều đc
	private String hometown;

	@Column(name ="residential_address", columnDefinition = "NVARCHAR(80)") //địa chỉ thường trú cho nhân viên
	private String residential_address;

	@Column(name ="delivery_address", columnDefinition = "NVARCHAR(80)") //địa chỉ giao hàng cho shipper mặc ịnh
	private String delivery_address;

	@Column(name ="idCard", columnDefinition = "VARCHAR(13)")
	private String idCard;

	@Column(name ="birth_day")
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	@Column(name ="gender")
	@Enumerated(EnumType.ORDINAL)
	private GenderEnum Gender = GenderEnum.UNKNOW;

	@Column(name ="email", nullable = false, columnDefinition = "VARCHAR(50)")
	@NotBlank(message = "Email không được để trống")
	@Email(message = "Email không hợp lệ")
	private String email;

	@Column(name ="phone_number", columnDefinition = "VARCHAR(10)")
	@Pattern(regexp = "^\\d{10}$", message = "Số điện thoại không hợp lệ")
	private String  phonenumber;

	@Column(name ="photo")
	private String photo;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account",  fetch = FetchType.EAGER)
	List<Authority> authorities;

	@JsonIgnore
	@OneToMany(mappedBy = "account")
	List<Order> orders;

	@JsonIgnore
	@OneToMany(mappedBy = "account")
	List<BlogPost> blogPosts;

	@ManyToMany
	@JoinTable(
			name = "account_voucher",
			joinColumns = @JoinColumn(name = "account_username"),
			inverseJoinColumns = @JoinColumn(name = "voucher_id"))
	private List<Voucher> voucherList;
}
