package com.zukalover.BlogApplication.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vote {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long voteId;
	private VoteType voteType;
	@NotNull
	@ManyToOne(targetEntity=Post.class,cascade=CascadeType.ALL)
	@JoinColumn(name="postId")
	private Post post;
	@ManyToOne(targetEntity=User.class,cascade=CascadeType.ALL)
	@JoinColumn(name="userId")
	private User user;
}
