package com.zukalover.BlogApplication.exceptions;

public class BlogApplicationException extends RuntimeException {
	
	public BlogApplicationException(String message)
	{
		super(message);
	}

	//Building Rest, we do not expose technical information to user
}
