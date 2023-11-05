package com.poly.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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


	/*
	* Note
	*
	* Set
	* BlogPost blogPost = new BlogPost();
	blogPost.setTag("tag1,tag2,tag3");
	*
	* Get
	* String tagString = blogPost.getTag();
	String[] tags = tagString.split(",");
	* */
	@Column(name = "Tag", columnDefinition = "nvarchar(max)")
	private String Tag;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "UserID")
	private Account account;
}
