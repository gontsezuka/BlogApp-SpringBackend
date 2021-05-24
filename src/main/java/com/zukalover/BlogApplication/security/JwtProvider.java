package com.zukalover.BlogApplication.security;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.Instant;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.zukalover.BlogApplication.exceptions.BlogApplicationException;
import com.zukalover.BlogApplication.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JwtProvider {

	
	
	private KeyStore keyStore;
	
	@PostConstruct
	public void init() {
		try {
		keyStore = KeyStore.getInstance("JKS");
		InputStream resourceAsStream = getClass().getResourceAsStream("/springblog.jks");
		keyStore.load(resourceAsStream,"secret".toCharArray());

		}catch( Exception e)
		{
			throw new BlogApplicationException("Error Loading Keystore");
		}
	}
	
	//43
	public String generateToken(Authentication authentication)
	{
		User principal = (User) authentication.getPrincipal();
		return Jwts.builder()
				.setSubject(principal.getUsername())
				.signWith(getPrivateKey())
				.compact();
	}
	
	private PrivateKey getPrivateKey()
	{
		try {
			return (PrivateKey)keyStore.getKey("springblog", "secret".toCharArray());
		}catch(Exception e)
		{
			throw new BlogApplicationException("Error retrieving public key");
		}
	}
	
	//50
	
	//SEPARATE DATA
	public boolean validateToken(String jwt)
	{
		Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(jwt);
		return true;
	}
	//50
	private PublicKey getPublicKey()
	{
		try {
			return keyStore.getCertificate("springblog").getPublicKey();
		}catch(KeyStoreException e)
		{
			throw new BlogApplicationException("Error occured while retrieving public key");
		}
	}
	
	//GETTING USER DATA FROM TOKEN
	//52
	public String getUsernameFromJwt(String token)
	{
		Claims claims = Jwts.parser()
				.setSigningKey(getPublicKey())
				.parseClaimsJws(token)
				.getBody();
		return claims.getSubject();
	}
}
