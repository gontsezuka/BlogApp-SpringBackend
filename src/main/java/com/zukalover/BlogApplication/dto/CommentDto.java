package com.zukalover.BlogApplication.dto;

import java.time.Instant;

import lombok.Data;

@Data
public class CommentDto {

	private Long id;
	private Long postId;
	private Instant createdDate;
	private String text;
	private String username;
	
	
	public CommentDto()
	{
		
	}
	
	public CommentDto(Long postId,Instant createdDate,String text, String username)
	{
		this.postId = postId;
		this.createdDate = createdDate;
		this.text = text;
		this.username = username;
	}
	
	public CommentDto(Long id,Long postId,Instant createdDate,String text, String username)
	{
		this.id = id;
		this.postId = postId;
		this.createdDate = createdDate;
		this.text = text;
		this.username = username;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getPostId() {
		return postId;
	}


	public void setPostId(Long postId) {
		this.postId = postId;
	}


	public Instant getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
