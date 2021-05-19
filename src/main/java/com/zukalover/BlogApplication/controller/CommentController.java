package com.zukalover.BlogApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zukalover.BlogApplication.dto.CommentDto;
import com.zukalover.BlogApplication.service.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@PostMapping("/create")
	public ResponseEntity<Void> createComment(@RequestBody CommentDto commentDto)
	{
		commentService.createComment(commentDto);
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	@GetMapping("/get-by-post/{postid}")
	public ResponseEntity<List<CommentDto>> getAllCommentsByPost(@PathVariable("postid")Long postId)
	{
		
	}
	
	@GetMapping("/get-by-user/{username}")
	public ResponseEntity<List<CommentDto>> getAllCommentsByUser(@PathVariable("username")String username)
	{
		
	}
	
}
