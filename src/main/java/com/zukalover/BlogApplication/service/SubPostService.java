package com.zukalover.BlogApplication.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zukalover.BlogApplication.dto.SubPostDto;
import com.zukalover.BlogApplication.model.SubPost;
import com.zukalover.BlogApplication.repo.SubPostRepository;

@Service
public class SubPostService {

	@Autowired
	private SubPostRepository subPostRepository;
	
	@Transactional
	public SubPostDto createSubPost(SubPostDto subPostDto)
	{
		SubPost subPost = new SubPost();
		subPost.setName(subPostDto.getName());
		subPost.setDescription(subPostDto.getDescription());
		SubPost save = subPostRepository.save(subPost);
		subPostDto.setId(save.getId());
		return subPostDto;
	}
	
	
	@Transactional
	public List<SubPostDto> getAllSubPosts()
	{
		List<SubPost> allSubPost = subPostRepository.findAll();
		List<SubPostDto> dtoList = new ArrayList<>();
		
		for(SubPost post: allSubPost)
		{
			dtoList.add(new SubPostDto(post.getId(),post.getName(),post.getDescription(),post.getPosts().size()));
		}
		
		return dtoList;
	}
	
	
}
