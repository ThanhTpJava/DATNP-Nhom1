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
@Table(name = "Reviews")
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ReviewID;

	@ManyToOne
	@JoinColumn(name = "ProductID")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "UserID")
	private Account account;

	@Column(name = "Rating")
	private int Rating;

	@Column(name = "Comment", columnDefinition = "nvarchar(max)")
	private String Comment;
}
