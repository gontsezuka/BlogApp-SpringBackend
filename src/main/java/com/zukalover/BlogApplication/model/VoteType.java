package com.zukalover.BlogApplication.model;

import java.util.Arrays;

import com.zukalover.BlogApplication.exceptions.BlogApplicationException;

public enum VoteType {
	
	UPVOTE(1),DOWNVOTE(-1);
	
	private int direction;
	
	VoteType(int direction)
	{	
		this.direction = direction;
	}
	
	public int getDirection()
	{
		return this.direction;
	}
	
	public void setDirection(int direction)
	{
		this.direction = direction;
	}
	

	public static VoteType lookUp(int direction)
	{
		return Arrays.stream(VoteType.values())
				.filter(value -> value.getDirection()==direction)
				.findAny()
				.orElseThrow(() -> new BlogApplicationException("Vote Not Found"));
	}
}
