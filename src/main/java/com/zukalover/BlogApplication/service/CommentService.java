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
		
		String message = mailContentBuilder.build(post.getUser().getUsername() +" a comment was posted on your post"+ post.getUrl());
		sendCommentNotification(message,post.getUser());
		
		
	}
	
	private void sendCommentNotification(String message, User user)
	{
		mailService.sendMail(new NotificationEmail(user.getUsername()+"commented on post",user.getEmail(),message));
	}
	public List<CommentDto> getAllCommentsByPostId(Long postId)
	{
		List<Comment> comments = new ArrayList<>();
		comments = commentRepository.findCommentByPostId(postId);
		
		
		
		
	}

}
