package com.zukalover.BlogApplication.model;

import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String text;
	@ManyToOne(targetEntity=Post.class,cascade=CascadeType.ALL)
	@JoinColumn(name="postId")
	private Post post;
	private Instant createdDate;
	@ManyToOne(targetEntity=User.class,cascade=CascadeType.ALL)
	@JoinColumn(name="userId")
	private User user;
}
