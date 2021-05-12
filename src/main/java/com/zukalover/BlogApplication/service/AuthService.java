package com.zukalover.BlogApplication.service;

import java.time.Instant;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zukalover.BlogApplication.dto.RegisterRequest;
import com.zukalover.BlogApplication.model.User;
import com.zukalover.BlogApplication.model.VerificationToken;
import com.zukalover.BlogApplication.repo.UserRepository;
import com.zukalover.BlogApplication.repo.VerificationTokenRepository;

/**
 * Class responsible for handling Authentication services
 * @author zukaLover
 *
 */
@Service
public class AuthService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private VerificationTokenRepository verificationTokenRepository;
	
	
	/**
	 * Description: Registering User
	 * @author zukaLover
	 * @param registerRequest
	 */
	@Transactional
	public void signUp(RegisterRequest registerRequest)
	{
		User user = new User();
		user.setEmail(registerRequest.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setUsername(registerRequest.getUsername());
		user.setEnabled(false);
		user.setCreatedDate(Instant.now());
		userRepository.save(user);
		
		
		//SEND VERIFICATION TOKEN
		//SENDING THE TOKEN IN EMAIL WILL REQUIRE THYMELEAF DEPENDENCY
		String token = generateVerificationToken(user);
	}
	
	private String generateVerificationToken(User user)
	{
		String token = UUID.randomUUID().toString();
		VerificationToken verificationToken = new VerificationToken();
		verificationToken.setToken(token);
		verificationToken.setUser(user);
		verificationTokenRepository.save(verificationToken);
		return token;
	}
}
