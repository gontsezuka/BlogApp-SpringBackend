package com.zukalover.BlogApplication.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zukalover.BlogApplication.dto.RegisterRequest;
import com.zukalover.BlogApplication.exceptions.BlogApplicationException;
import com.zukalover.BlogApplication.model.NotificationEmail;
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
	
	@Autowired
	private MailService mailService;
	
	
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
		
		mailService.sendMail(new NotificationEmail("Please Activate your Account",user.getEmail(),"Thank your for signing up for our Blog"+
		"application, please click link below to activate account: "+
				"http://localhost:8080/api/auth/accountVerification/"+token));
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
	
	/**
	 * @author zukaLover
	 * @param token
	 */
	public void verifyAccount(String token)
	{
		Optional<VerificationToken> fromDBToken = verificationTokenRepository.findByToken(token);
		fromDBToken.orElseThrow(() -> new BlogApplicationException("Invalid Token"));
		fetchAndEnableUser(fromDBToken.get());
	}
	
	//30
	@Transactional
	private void fetchAndEnableUser(VerificationToken verificationToken)
	{
	
		String username = verificationToken.getUser().getUsername();
		User returnedUser = userRepository.findUserByUsername(username).orElseThrow(()->new BlogApplicationException("Invalid User"));
		returnedUser.setEnabled(true);
		userRepository.save(returnedUser);
	}
}
