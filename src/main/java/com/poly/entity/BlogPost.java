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
@Table(name = "BlogPosts")
public class BlogPost {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "Title", nullable = false, columnDefinition = "nvarchar(max)")
	private String Title;

	@Column(name = "Image", nullable = false, columnDefinition = "varchar(max)")
	private String Image;

	@Column(name = "Content", columnDefinition = "nvarchar(max)")
	private String Content;

	@Column(name = "PublishDate")
	private Date PublishDate;

	@ManyToOne
	@JoinColumn(name = "UserID")
	private Account account;
}
