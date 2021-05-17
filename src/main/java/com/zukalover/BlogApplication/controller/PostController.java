package com.zukalover.BlogApplication.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zukalover.BlogApplication.dto.PostRequest;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	@PostMapping("/create")
	public ResponseEntity createPost(@RequestBody PostRequest postRequest)
	{
		return new ResponseEntity(HttpStatus.CREATED);
	}
}
