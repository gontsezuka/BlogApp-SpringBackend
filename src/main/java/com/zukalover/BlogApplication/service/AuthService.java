package com.zukalover.BlogApplication.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zukalover.BlogApplication.dto.AuthenticationResponse;
import com.zukalover.BlogApplication.dto.LoginRequest;
import com.zukalover.BlogApplication.dto.RegisterRequest;
import com.zukalover.BlogApplication.exceptions.BlogApplicationException;
import com.zukalover.BlogApplication.model.NotificationEmail;
import com.zukalover.BlogApplication.model.User;
import com.zukalover.BlogApplication.model.VerificationToken;
import com.zukalover.BlogApplication.repo.UserRepository;
import com.zukalover.BlogApplication.repo.VerificationTokenRepository;
import com.zukalover.BlogApplication.security.JwtProvider;

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
	 * Specify specifically which bean to create in Security Config
	 */
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	
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
	
	/**
	 * @author zukaLover
	 * 
	 * @param loginRequest
	 */
	public AuthenticationResponse login(LoginRequest loginRequest)
	{
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
		//STORE THE AUTHENTICATION OBJECT INSIDE SECURITY CONTEXT
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		//THE SECURITY CONTEXT TO BE USED TO CHECK IF USER IS LOGGED IN
		
		String token = jwtProvider.generateToken(authenticate);
		return new AuthenticationResponse(token,loginRequest.getUsername());
		
	}
}
