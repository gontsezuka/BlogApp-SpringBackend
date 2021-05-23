package com.zukalover.BlogApplication.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zukalover.BlogApplication.dto.PostRequest;
import com.zukalover.BlogApplication.dto.PostResponse;
import com.zukalover.BlogApplication.model.Comment;
import com.zukalover.BlogApplication.model.Post;
import com.zukalover.BlogApplication.model.SubPost;
import com.zukalover.BlogApplication.model.User;
import com.zukalover.BlogApplication.model.Vote;
import com.zukalover.BlogApplication.repo.CommentRepository;
import com.zukalover.BlogApplication.repo.PostRepository;
import com.zukalover.BlogApplication.repo.SubPostRepository;
import com.zukalover.BlogApplication.repo.UserRepository;
import com.zukalover.BlogApplication.repo.VoteRepository;

@Service
@Transactional
public class PostService {
	
	
	@Autowired
	private SubPostRepository subPostRepository;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private VoteRepository voteRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	public void createPost(PostRequest postRequest)
	{
	
		SubPost subPost = subPostRepository.findSubPostByName(postRequest.getSubPostName());	
		User user = authService.getCurrentUser();
		Post postSaved = postRepository.save(new Post(postRequest.getPostName(),postRequest.getUrl(),postRequest.getDescription(),0,user,Instant.now(),subPost)); 
		
		PostResponse postResponse = new PostResponse(postSaved.getPostId(),postSaved.getSubPost().getName(),postSaved.getPostName(),postSaved.getUrl(),postSaved.getDescription());
		
	}
	
	public List<PostResponse> findAllPosts()
	{
		List<Post> allPosts = postRepository.findAll();
		List<PostResponse> allPostResponses = new ArrayList<>();
		
		for(Post post: allPosts)
		{
			allPostResponses.add(new PostResponse(post.getPostId(),post.getSubPost().getName(),post.getPostName(),post.getUrl(),post.getDescription()));
		}
		
		return allPostResponses;
	}
	
	
	public List<PostResponse> getPostsBySubPost(Long subPostId)
	{
		List<Post> allPosts = new ArrayList<>();
		List<PostResponse> allPostResponses = new ArrayList<>();
		
		allPosts = postRepository.findAllPostsBySubPostId(subPostId);
		
		for(Post post: allPosts)
		{
			allPostResponses.add(new PostResponse(post.getPostId(),post.getSubPost().getName(),post.getPostName(),post.getUrl(),post.getDescription()));
		}
		
		return allPostResponses;
	}
	
	public List<PostResponse> getPostsByUsername(String username)
	{
		
		String te = TimeAgo.using(System.currentTimeMillis());
		Optional<User >user = userRepository.findUserByUsername(username);
		List<Post> allPosts = new ArrayList<>();
		List<PostResponse> allPostResponses= new ArrayList<>();
		allPosts = postRepository.findAllPostsByUserId(user.get().getUserId());
		
		for(Post post: allPosts)
		{
			List<Comment> allComments = commentRepository.findCommentByPostId(post.getPostId());
			List<Vote> allVotes = voteRepository.findAllVotesByPostId(post.getPostId());
			allPostResponses.add(new PostResponse(post.getPostId(),post.getSubPost().getName(),post.getPostName(),post.getUrl(),post.getDescription(),allComments.size(),allVotes.size(),"TIMEAGO"));
			
		}
		
		return allPostResponses;
	}
	
	public PostResponse findPostById(Long postId)
	{
		PostResponse postResponse = new PostResponse();
		Post postReturned = new Post();
		postReturned = postRepository.findPostById(postId);
		
		postResponse.setPostId(postReturned.getPostId());
		postResponse.setDescription(postReturned.getDescription());
		postResponse.setPostName(postReturned.getPostName());
		postResponse.setUrl(postReturned.getUrl());
		postResponse.setSubPostName(postReturned.getSubPost().getName());
		return postResponse;
	}
	
	
	
}
