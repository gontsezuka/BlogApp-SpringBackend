package com.zukalover.BlogApplication.dto;

import lombok.Data;

@Data
public class AuthenticationResponse {

	private String authenticationToken;
	private String username;
	
	public AuthenticationResponse()
	{
		
	}
	public AuthenticationResponse(String token, String username)
	{
		this.authenticationToken= token;
		this.username= username;
	}
	
	public void setAuthenticationToken(String token)
	{
		this.authenticationToken = token;
	}
	
	public String getAuthenticationToken()
	{
		return this.authenticationToken;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public String getUsername()
	{
		return this.username;
	}
}
