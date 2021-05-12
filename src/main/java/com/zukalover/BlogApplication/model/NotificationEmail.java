package com.zukalover.BlogApplication.model;

import lombok.Data;

@Data
public class NotificationEmail {

	private String subject;
	private String recipient;
	private String body;
	
	public NotificationEmail()
	{
		
	}
	public NotificationEmail(String subject, String recipient, String body)
	{
		this.subject=subject;
		this.recipient = recipient;
		this.body = body;
	}
	
	public void setSubject(String subject)
	{
		this.subject = subject;
	}
	
	public String getSubject()
	{
		return this.subject;
	}
	
	public void setRecipient(String recipient)
	{
		this.recipient = recipient;
	}
	
	public String getRecipient()
	{
		return this.recipient;
	}
	
	public void setBody(String body)
	{
		this.body = body;
	}
	
	public String getBody()
	{
		return this.body;
	}
	
	
}
