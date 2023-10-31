package com.poly.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
@Table(name = "Authorities", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"username","roleid"})
})

public class Authority implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "username")
	private Account account;

	@ManyToOne
	@JoinColumn(name = "roleid")
	private Role role;
}
