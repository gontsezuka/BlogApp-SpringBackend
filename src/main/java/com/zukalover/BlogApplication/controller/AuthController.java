package com.zukalover.BlogApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zukalover.BlogApplication.dto.AuthenticationResponse;
import com.zukalover.BlogApplication.dto.LoginRequest;
import com.zukalover.BlogApplication.dto.RegisterRequest;
import com.zukalover.BlogApplication.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/signup")
	public ResponseEntity<String> signUp(@RequestBody RegisterRequest registerRequest)
	{
		authService.signUp(registerRequest);
		return new ResponseEntity<>("User Registration successful",HttpStatus.OK);
	}
	//29
	@GetMapping("/accountVerification/{token}")
	public ResponseEntity<String> verifyAccount(@PathVariable("token")String token)
	{
		authService.verifyAccount(token);
		return new ResponseEntity<>("Account Activated Successfully",HttpStatus.OK);
	}
	//47
	@PostMapping("/login")
	public AuthenticationResponse login(@RequestBody LoginRequest loginRequest)
	{
		return authService.login(loginRequest);
	}
}
