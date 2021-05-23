package com.zukalover.BlogApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zukalover.BlogApplication.dto.VoteDto;
import com.zukalover.BlogApplication.service.VoteService;

@RestController
@RequestMapping("/api/votes")
public class VoteController {

	@Autowired
	private VoteService voteService;
	
	//1-30
	@PostMapping("/vote")
	public ResponseEntity<Void> vote(@RequestBody VoteDto voteDto)
	{
		voteService.vote(voteDto);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
