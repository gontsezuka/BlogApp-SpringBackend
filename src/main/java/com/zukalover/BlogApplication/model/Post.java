package com.zukalover.BlogApplication.model;

import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long postId;
	private String postName;
	@Nullable
	private String url;
	@Nullable
	@Lob
	private String description;
	private Integer voteCount;
	@ManyToOne(targetEntity=User.class,cascade=CascadeType.ALL)
	@JoinColumn(name= "userId")
	private User user;
	private Instant createdDate;
	@ManyToOne(targetEntity=SubPost.class, cascade=CascadeType.ALL)
	@JoinColumn(name = "id", referencedColumnName = "id")
	private SubPost subPost;
	
}
