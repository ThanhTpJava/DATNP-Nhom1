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
@Table(name = "Banners")
public class Banner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer BannerID;

	@Column(name = "ImageURL", nullable = false, length = 255)
	private String ImageURL;

	@Column(name = "Caption",  columnDefinition = "nvarchar(max)")
	private String Caption;

	@Column(name = "Orderr")
	private int Orderr;

	@Column(name = "CreatedAt")
	private Date CreatedAt;

	@ManyToOne
	@JoinColumn(name = "UserID")
	private Account account;
}
