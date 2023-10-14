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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column(columnDefinition = "nvarchar(70)")
	String address;
	@Temporal(TemporalType.DATE)
	@Column(name ="Createdate")
	Date createDate = new Date();
	@ManyToOne
	@JoinColumn(name ="Username")
	Account account;
	@Column(columnDefinition = "bit")
	Boolean status;
	@JsonIgnore
	@OneToMany(mappedBy = "order")
	List<OrderDetail> orderDetails;
}
