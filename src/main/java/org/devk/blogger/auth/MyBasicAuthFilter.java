package org.devk.blogger.auth;

import java.io.IOException;
import java.util.Base64;
import java.util.StringTokenizer;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.devk.blogger.users.User;
import org.devk.blogger.users.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Basic authentication for API with out Spring security
 * @author devinder
 *
 */
//@Component
public class MyBasicAuthFilter extends OncePerRequestFilter{

	@Autowired
	UserRepo userRepo;
	
	private static String authHdr = "Authorization";
	private static String hdrPre ="Basic ";
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String auth =  request.getHeader(authHdr);
		if(auth!=null&&!auth.isEmpty()) {
			
			auth = auth.replaceAll(hdrPre,"");
			auth = new String(Base64.getDecoder().decode(auth.getBytes()));
			StringTokenizer st = new StringTokenizer(auth, ":");
			String username = st.nextToken();
			String pwd = st.nextToken();
			
			if(doAthenticate(username,pwd)) {
				filterChain.doFilter(request, response);
			}else {
				response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unuathrised access");
			}
		}else {
			response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unuathrised access");
		}
		
	}

	private boolean doAthenticate(String username, String pwd) {
		User user = userRepo.findByUsername(username);
		if(user!=null) {
			return user.getPwd().equals(pwd);//assuming no password encryption
		}
		return false;
	}


}
