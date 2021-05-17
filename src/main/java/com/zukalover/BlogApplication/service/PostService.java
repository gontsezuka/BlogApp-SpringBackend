package com.zukalover.BlogApplication.service;

import java.time.Instant;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zukalover.BlogApplication.dto.PostRequest;
import com.zukalover.BlogApplication.model.Post;
import com.zukalover.BlogApplication.model.SubPost;
import com.zukalover.BlogApplication.model.User;
import com.zukalover.BlogApplication.repo.PostRepository;
import com.zukalover.BlogApplication.repo.SubPostRepository;

@Service
@Transactional
public class PostService {
	
	
	@Autowired
	private SubPostRepository subPostRepository;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private PostRepository postRepository;
	
	public void createPost(PostRequest postRequest)
	{
	
		SubPost subPost = subPostRepository.findSubPostByName(postRequest.getSubPostName());	
		User user = authService.getCurrentUser();
		Post postSaved = postRepository.save(new Post(postRequest.getPostName(),postRequest.getUrl(),postRequest.getDescription(),0,user,Instant.now(),subPost)); 
		
	}
}
