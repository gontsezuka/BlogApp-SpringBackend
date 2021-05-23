package com.zukalover.BlogApplication.dto;

import lombok.Data;

@Data
public class PostResponse {
	
	private Long postId;
	private String subPostName;
	private String postName;
	private String url;
	private String userName;
	private String description;
	private int voteCount;
	private int commentCount;
	private String duration;
	
	
	public PostResponse()
	{
		
	}

	public PostResponse(Long postId,String subPostName,String postName,String url,String description)
	{
		this.postId = postId;
		this.subPostName = subPostName;
		this.postName = postName;
		this.url = url;
		this.description = description;
	}
	
	public PostResponse(Long postId,String subPostName,String postName,String url,String description,int commentCount, int voteCount,String duration)
	{
		this.postId = postId;
		this.subPostName = subPostName;
		this.postName = postName;
		this.url = url;
		this.description = description;
		this.commentCount = commentCount;
		this.voteCount = voteCount;
		this.duration = duration;
	}


	public Long getPostId() {
		return postId;
	}


	public void setPostId(Long postId) {
		this.postId = postId;
	}


	public String getSubPostName() {
		return subPostName;
	}


	public void setSubPostName(String subPostName) {
		this.subPostName = subPostName;
	}


	public String getPostName() {
		return postName;
	}


	public void setPostName(String postName) {
		this.postName = postName;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

}
