package com.poly.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
@Data
@Entity
@Table(name ="Categories")
public class Category implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int IdCategory;

	@Column(name = "Name", nullable = false, columnDefinition = "nvarchar(100)")
	private String Name;

	@ManyToOne
	@JoinColumn(name = "BrandID")
	private Brand brand;
}
