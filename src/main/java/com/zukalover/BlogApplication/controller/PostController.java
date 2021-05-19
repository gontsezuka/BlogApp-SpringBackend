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

import com.zukalover.BlogApplication.dto.PostRequest;
import com.zukalover.BlogApplication.dto.PostResponse;
import com.zukalover.BlogApplication.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	//1-15
	@Autowired
	private PostService postService;

	@PostMapping("/create")
	public ResponseEntity<Void> createPost(@RequestBody PostRequest postRequest)
	{
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	@GetMapping("/get-all-posts")
	public ResponseEntity<List<PostResponse>> getAllPosts()
	{
		return ResponseEntity.status(HttpStatus.OK).body(postService.findAllPosts());
	}
	
	@GetMapping("/get-by-subpost/{subpostid}")
	public ResponseEntity<List<PostResponse>> getAllPostsBySubPost(@PathVariable("subpostid")Long subPostId)
	{
		return ResponseEntity.status(HttpStatus.OK).body(postService.getPostsBySubPost(subPostId));
	}
	
	@GetMapping("/get-by-username/{username}")
	public ResponseEntity<List<PostResponse>> getAllPostsByUsername(@PathVariable("username")String username)
	{
		return ResponseEntity.status(HttpStatus.OK).body(postService.getPostsByUsername(username));
	}

	
	@GetMapping("/get-post-by-id/{id}")
	public ResponseEntity<PostResponse> getAllPostById(@PathVariable("id")Long postId)
	{
		return ResponseEntity.status(HttpStatus.OK).body(postService.findPostById(postId));
	}
}
