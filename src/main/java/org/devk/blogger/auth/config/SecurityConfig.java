package org.devk.blogger.auth.config;

import org.devk.blogger.exceptions.UserNotFoundException;
import org.devk.blogger.models.User;
import org.devk.blogger.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
/**
 * 
 * @author devinder kashyap
 *
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

		@Autowired
		UserRepo userRepo;
				
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.authorizeRequests().anyRequest().fullyAuthenticated().and().httpBasic();
	        http .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable();
	    }

	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService((username) -> {
				User a = userRepo.findByUsername(username);
				if(a!=null) {
					return  new org.springframework.security.core.userdetails.User(a.getUsername(), a.getPwd(), true, true, true, true,
							AuthorityUtils.createAuthorityList("USER", "write"));
				}else throw new UserNotFoundException("could not find the user '"	+ username + "'");
			}
			).passwordEncoder(NoOpPasswordEncoder.getInstance());
	    }
}
