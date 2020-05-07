package com.jeonghyeong.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.jeonghyeong.entity.User;
import com.jeonghyeong.entity.UserAuthority;
import com.jeonghyeong.repository.UserAuthorityRepository;
import com.jeonghyeong.repository.UserRepository;

@Transactional
@SuppressWarnings("deprecation")
public class JwtAuthenticationFilter extends BasicAuthenticationFilter{

	private UserRepository userRepo;
	
	private UserAuthorityRepository userAuthorityRepo;
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager,
			UserRepository userRepo, UserAuthorityRepository userAuthorityRepo) {
		super(authenticationManager);
		this.userRepo = userRepo;
		this.userAuthorityRepo = userAuthorityRepo;
		System.out.println("JWTAuthorization Filter is created");
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		if (request.getHeader("Authorization") == null || !request.getHeader("Authorization").startsWith("Bearer ")) {
            chain.doFilter(request, response);
            System.out.println("failed to authorization");
            SecurityContextHolder.getContext().setAuthentication(null);
            return;
        }
		
			
		Authentication authentication = this.getAuthentication(request);
		SecurityContextHolder.getContext().setAuthentication(authentication);;
			
		System.out.println("do next FilterInternal");
		super.doFilterInternal(request, response, chain);
	}

	@Override
	protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			Authentication authResult) throws IOException {
		System.out.println(authResult.getName());
		System.out.println(authResult.getAuthorities());
		System.out.println(authResult.getPrincipal());
		SecurityContextHolder.getContext().setAuthentication(authResult);
	}

	@Override
	protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException {
		System.out.println("fail");
		super.onUnsuccessfulAuthentication(request, response, failed);
	}
	
	
	@SuppressWarnings("unchecked")
	private Authentication getAuthentication(HttpServletRequest request){

		/*
		 * 1. token validation second check (using client_id, and get token_validity)
		 * 2. if valid, find user. if there is no user, throw exception
		 * 3. if user is exist, 
		 */
		String token = request.getHeader("Authorization").replace("Bearer ", "");
		
		String decodedJwt = JwtHelper.decode(token).getClaims();
	
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonJwt = new JSONObject();
		try {
			jsonJwt = (JSONObject) jsonParser.parse(decodedJwt);
		} catch (ParseException e) {
			return new UsernamePasswordAuthenticationToken(null, null, null);
		}
		String username = (String) jsonJwt.get("user_name");
		long exp = (long) jsonJwt.get("exp");
		
		
		
		if(exp<(System.currentTimeMillis()/1000L)) {
			System.out.println("Token expired");
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(null, null, null);
			authToken.setAuthenticated(false);
			return new UsernamePasswordAuthenticationToken(null, null, null);
		}
		
		User user = userRepo.findbyUsername(username);
		
		List<UserAuthority> userAuthorityList = new ArrayList<UserAuthority>(); 
		List<GrantedAuthority> convertedAuthorityList = new ArrayList<GrantedAuthority>();
		
		JSONObject principle = new JSONObject();
		principle.put("userId", user.getUserid());
		principle.put("username", user.getUsername());
		
		userAuthorityList =  userAuthorityRepo.getAuthorityList(user.getUserid());
		
		//create Authentication Object's Authority List
		for(int i=0;i<userAuthorityList.size();i++) {
			convertedAuthorityList.add(new SimpleGrantedAuthority(userAuthorityList.get(i).getAuthority()));
		}

		return new UsernamePasswordAuthenticationToken(principle, null, convertedAuthorityList);
		
    }
	

}
