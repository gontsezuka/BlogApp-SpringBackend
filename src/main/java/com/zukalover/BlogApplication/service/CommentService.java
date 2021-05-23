package com.zukalover.BlogApplication.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zukalover.BlogApplication.dto.CommentDto;
import com.zukalover.BlogApplication.model.Comment;
import com.zukalover.BlogApplication.model.NotificationEmail;
import com.zukalover.BlogApplication.model.Post;
import com.zukalover.BlogApplication.model.User;
import com.zukalover.BlogApplication.repo.CommentRepository;
import com.zukalover.BlogApplication.repo.PostRepository;
import com.zukalover.BlogApplication.repo.UserRepository;

@Service
@Transactional
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MailContentBuilder mailContentBuilder;
	
	@Autowired
	private MailService mailService;
	
	
	public void createComment(CommentDto commentDto)
	{
		Post post = new Post();
		post = postRepository.findPostById(commentDto.getPostId());
		Optional<User> user = userRepository.findUserByUsername(commentDto.getUsername());
		commentRepository.save(new Comment(commentDto.getText(),post,Instant.now(),user.get()));
	
		//SEND NOTIFICATION EMAIL FOR FOR COMMENT
		//1-25
		String message = mailContentBuilder.build(post.getUser().getUsername() +" a comment was posted on your post"+ post.getUrl());
		sendCommentNotification(message,post.getUser());
		
	}
	
	private void sendCommentNotification(String message, User user)
	{
		mailService.sendMail(new NotificationEmail(user.getUsername()+"commented on post",user.getEmail(),message));
	}
	
	//1-28
	public List<CommentDto> getAllCommentsByPostId(Long postId)
	{
		List<CommentDto> commentList = new ArrayList<>();
		
		Post post = new Post();
		post = postRepository.findPostById(postId);
		if(post !=null)
		{
			List<Comment> comments = new ArrayList<>();
			comments = commentRepository.findCommentByPostId(postId);
			
			for(Comment comment: comments)
			{
				commentList.add(new CommentDto(comment.getId(),comment.getPost().getPostId(),comment.getCreatedDate(),comment.getText(),comment.getUser().getUsername()));
			}
		}
	
		return commentList;
		
	}
	
	public List<CommentDto> getAllCommentsForUser(String username)
	{
		List<CommentDto> commentList = new ArrayList<>();
		Optional<User> user = userRepository.findUserByUsername(username);
		
		if(user.get() !=null)
		{
			List<Comment> commentsByUser =new  ArrayList<>();
			commentsByUser = commentRepository.findCommentByUser(user.get().getUserId());
			
			for(Comment comment: commentsByUser)
			{
				
				commentList.add(new CommentDto(comment.getId(),comment.getPost().getPostId(),comment.getCreatedDate(),comment.getText(),comment.getUser().getUsername()));
				
			}
			
		}
		
		return commentList;
	}

}
