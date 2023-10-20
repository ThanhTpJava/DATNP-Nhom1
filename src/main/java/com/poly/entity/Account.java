package com.poly.entity;

import java.io.Serializable;
import java.util.List;



import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
	@Column(columnDefinition = "varchar(30)", name = "username")
	@Size(min = 5, message = "Tên tài khoản quá ngắn")
	@NotBlank(message = "Tài khoản không được trống")
	private String username;
	
	@Column(columnDefinition = "varchar(300)", name = "pass_word")
	@Size(min = 5, message = "Mật khẩu quá ngắn")
	@NotBlank(message = "Mật khẩu không được trống")
	private String password;
	
	@Column(columnDefinition = "nvarchar(40)", name = "fullname")
	@NotBlank(message = "Nhập vào tên đầy đủ của bạn")
	private String fullname;
	
	@Column(columnDefinition = "varchar(100)", name = "email")
	@NotBlank(message = "Email không được để trống")
	@Email(message = "Tài khoản email không hợp lệ")
	private String email;
	
	@Column(columnDefinition = "varchar(225)", name = "photo")
	private String photo;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account",  fetch = FetchType.EAGER)
	List<Authority> authorities;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	List<Order> orders;
}
