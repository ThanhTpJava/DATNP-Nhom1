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

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
@SuppressWarnings("serial")
@Data
@Entity
@Table(name ="Products")
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	@Column(name = "Name", nullable = false, columnDefinition = "nvarchar(max)")
	private String name;

	//Mô tả
	@Column(name = "Description", columnDefinition = "nvarchar(max)")
	private String Description;

	@Column(name = "Price")
	private double price;

	//giảm giá bao nhiêu phần trăm
	@Column(name = "sale")
	private Double sale;

	@Column(name = "Quantity")
	private Integer Quantity;

	@Column(name = "Product_information", columnDefinition = "nvarchar(max)")
	private String Product_information;

	@Column(name = "Date_import")
	private Date date_import;

	//Thời gian bảo hành
	@Column(name ="warranty_period")
	private Integer warranty_period;

	@Column(name ="main_image")
	private String main_image;

	@ManyToOne
	@JoinColumn(name = "CategoryID")
	private Category category;

	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<OrderDetail> orderDetails;

	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<ProductImage> productImage;
}
