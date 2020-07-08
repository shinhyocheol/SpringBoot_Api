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

import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.filter.GenericFilterBean;

public class JwtAuthFilter extends GenericFilterBean{
	
	private Logger logger = LoggerFactory.getLogger(JwtAuthFilter.class);	
	
	@Autowired
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
			String path = ((HttpServletRequest) request).getRequestURI();
			HttpServletResponse httpRes = (HttpServletResponse) response;
			httpRes.setHeader("Access-Control-Allow-Origin", "*");
			httpRes.setHeader("Access-Control-Allow-Credentials", "true");
			httpRes.setHeader("Access-Control-Allow-Methods","*");
			httpRes.setHeader("Access-Control-Max-Age", "3600");
			httpRes.setHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept, Key, Authorization");
			
			HttpServletRequest httpReqMap = (HttpServletRequest)request;
			if("OPTIONS".equalsIgnoreCase(httpReqMap.getMethod())) {
				httpRes.setStatus(HttpServletResponse.SC_OK);
			} else {
				String token = jwtTokenProvider.resolveToken(httpReqMap); 
				if (token != null && jwtTokenProvider.validateToken(token)) {
					Authentication auth = jwtTokenProvider.authenticate(token, httpReqMap);
					SecurityContextHolder.getContext().setAuthentication(auth);
				}				
				filterChain.doFilter(request, response);
			}				
		} catch (Exception e) {
			logger.debug("Log msg : " + e.getMessage());
		}	
	}
	
}