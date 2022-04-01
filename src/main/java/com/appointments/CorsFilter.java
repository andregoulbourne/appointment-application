package com.appointments;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Profile("production")
@WebFilter("/*")
public class CorsFilter extends OncePerRequestFilter {
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		//response.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
		//response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		chain.doFilter(request, response);
		
	}

}