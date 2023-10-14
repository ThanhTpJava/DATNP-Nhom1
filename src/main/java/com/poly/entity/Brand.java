package com.poly.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Brands")
public class Brand {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int BrandID;

	@Column(name = "Name", nullable = false, columnDefinition = "nvarchar(150)")
	private String Name;

	@Column(name = "Description", columnDefinition = "nvarchar(max)")
	private String Description;
}
