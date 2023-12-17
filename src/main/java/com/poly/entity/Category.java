package com.poly.entity;

import java.io.Serializable;


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
@Column(name = "id", nullable = false, columnDefinition = "nvarchar(100)")
private String id;

	@Column(name = "Name", nullable = false, columnDefinition = "nvarchar(100)")
	private String Name;

}
