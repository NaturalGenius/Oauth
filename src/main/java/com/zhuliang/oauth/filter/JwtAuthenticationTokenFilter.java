package com.zhuliang.oauth.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.zhuliang.oauth.security.DbUserDetailsService;
import com.zhuliang.oauth.util.JwtTokenUtil;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter{
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);
    @Autowired
    private DbUserDetailsService userDetailsService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	    if (SecurityContextHolder.getContext().getAuthentication() == null) {
	        String authToken = JwtTokenUtil.getTokenFromRequest(request);
	        if (StringUtils.isNoneBlank(authToken)) {
	            String username = JwtTokenUtil.getUserNameFromToken(authToken);
	            LOGGER.info("checking username:{}", username);
	            if (username != null) {
	                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	                if (JwtTokenUtil.validateToken(authToken, userDetails)) {
	                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                    LOGGER.info("authenticated user:{}", username);
	                    SecurityContextHolder.getContext().setAuthentication(authentication);
	                }
	            }
	        }
        }
        filterChain.doFilter(request, response);
	}

}
