package com.zukalover.BlogApplication.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zukalover.BlogApplication.dto.VoteDto;
import com.zukalover.BlogApplication.exceptions.BlogApplicationException;
import com.zukalover.BlogApplication.model.Post;
import com.zukalover.BlogApplication.model.Vote;
import com.zukalover.BlogApplication.model.VoteType;
import com.zukalover.BlogApplication.repo.PostRepository;
import com.zukalover.BlogApplication.repo.VoteRepository;

@Service
@Transactional
public class VoteService {
	
	@Autowired
	private VoteRepository voteRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private AuthService authService;
	

	@Transactional
	public void vote(VoteDto voteDto)
	{
		Post post = postRepository.findPostById(voteDto.getPostId());
		Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());
		
		if(voteByPostAndUser.isPresent())
		{
			throw new BlogApplicationException("You have already voted");
		}
		
		if(VoteType.UPVOTE.equals(voteDto.getVoteType()))
		{
			post.setVoteCount(post.getVoteCount()+1);
		}else {
			post.setVoteCount(post.getVoteCount()-1);
		}
		
		Vote vote = new Vote();
		vote.setPost(post);
		vote.setUser(authService.getCurrentUser());
		vote.setVoteType(voteDto.getVoteType());
		voteRepository.save(vote);
	
		postRepository.save(post);
	}
}
