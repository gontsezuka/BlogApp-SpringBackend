package com.zukalover.BlogApplication.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zukalover.BlogApplication.dto.SubPostDto;
import com.zukalover.BlogApplication.service.SubPostService;

@RestController
@RequestMapping("/api/subpost")
public class SubPostController {
	
	private  Logger LOGGER = LoggerFactory.getLogger(SubPostController.class);
	
	@Autowired
	private SubPostService subPostService;
	
	@PostMapping("/api/subpost/create")
	public ResponseEntity<SubPostDto> createSubPost(@RequestBody SubPostDto subPostDto)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(subPostService.createSubPost(subPostDto));
	}

	@GetMapping("/api/subpost/get-all")
	public ResponseEntity<List<SubPostDto>> getAllSubPosts()
	{
		return ResponseEntity.status(HttpStatus.OK).body(subPostService.getAllSubPosts());
	}
}
