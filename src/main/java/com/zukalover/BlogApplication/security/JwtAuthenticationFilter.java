package com.zukalover.BlogApplication.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.zukalover.BlogApplication.service.UserDetailsServiceImpl;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	//50
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String jwtFromRequest = getJwtFromRequest(request);
		
		//CALLING METHOD IN JWTPROVIDER CLASS
		jwtProvider.validateToken(jwtFromRequest);
		
		//AFTER VALIDATIONS IS COMPLETE WE HAVE TO LOAD USER DETAILS
		//INTO SECURITY CONTEXT
		//52
		if(StringUtils.hasText(jwtFromRequest) && jwtProvider.validateToken(jwtFromRequest))
		{
			String username = jwtProvider.getUsernameFromJwt(jwtFromRequest);
			
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		filterChain.doFilter(request, response);
	}
	
	private String getJwtFromRequest(HttpServletRequest request)
	{
		//Retrieve header from request
		String bearerToken = request.getHeader("Authorization");
		   
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer "))
		{
			return bearerToken.substring(7);
		}
		return bearerToken;
	}

}
