package kr.co.platform.util.auth;

import java.io.IOException;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.filter.GenericFilterBean;

import kr.co.platform.util.auth.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthFilter extends GenericFilterBean{
		
	private JwtTokenProvider jwtTokenProvider;
	
	public JwtAuthFilter(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}
	
	@Override
	public void doFilter(
			ServletRequest request, 
			ServletResponse response, 
			FilterChain filterChain) throws IOException, ServletException {
		try {
			HttpServletRequest httpReq = (HttpServletRequest)request;
			HttpServletResponse httpRes = (HttpServletResponse) response;
			
			httpRes.setHeader("Access-Control-Allow-Origin", "*");
			httpRes.setHeader("Access-Control-Allow-Methods", "*");
			httpRes.setHeader("Access-Control-Max-Age", "3600");
			httpRes.setHeader("Access-Control-Allow-Credentials", "true");
			httpRes.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
			
			if("OPTIONS".equalsIgnoreCase(httpReq.getMethod())) {
				httpRes.setStatus(HttpServletResponse.SC_OK);
			} else {
				String token = jwtTokenProvider.resolveToken(httpReq); 
				if (token != null && jwtTokenProvider.validateToken(token)) {
					Authentication auth = jwtTokenProvider.getAuthentication(token, httpReq);
					SecurityContextHolder.getContext().setAuthentication(auth);
				}				
			}				
			filterChain.doFilter(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
}