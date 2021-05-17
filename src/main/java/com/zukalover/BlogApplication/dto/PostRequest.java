package com.zukalover.BlogApplication.dto;

import lombok.Data;

@Data
public class PostRequest {

	private Long postId;
	private String subPostName;
	private String postName;
	private String url;
	private String description;
	
	public PostRequest()
	{
		
	}
	public PostRequest(String subPostName,String postName,String url,String description)
	{
		this.subPostName = subPostName;
		this.postName = postName;
		this.url =  url;
		this.description = description;
		
	}
	public PostRequest(Long postId,String subPostName,String postName,String url,String description)
	{
		this.postId = postId;
		this.subPostName = subPostName;
		this.postName = postName;
		this.url =  url;
		this.description = description;
		
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
	
	
}
