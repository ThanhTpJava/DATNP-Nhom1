package com.poly.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Banners")
public class Banner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int BannerID;

	@Column(name = "ImageURL", nullable = false, length = 255)
	private String ImageURL;

	@Column(name = "Caption",  columnDefinition = "nvarchar(max)")
	private String Caption;

	@Column(name = "Orderr")
	private int Orderr;

	@CreatedDate
	@Column(name = "CreatedAt", columnDefinition = "date")
	private Date CreatedAt;

	@ManyToOne
	@JoinColumn(name = "UserID")
	private Account account;
}
