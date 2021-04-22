package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	private final PasswordEncoder passwordEncoder;
	private final UserDetailsService userDetailsService;
	
	@Autowired
	public SecurityConfig(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
		this.passwordEncoder = passwordEncoder;
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.anyRequest().permitAll()
			//.antMatchers("/home", "/register").permitAll()
			//.antMatchers("/admin").hasRole("ADMIN")
			//.antMatchers("/login").hasRole("USER")
			.and().formLogin()
			.loginPage("/login")
			.failureUrl("/login-err")
			.permitAll()
			.and()
			.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID");

		http.csrf().disable();
	}
	
/*	@Override
	public void configure(WebSecurity web) throws Exception {
		web
			.ignoring()
			.antMatchers("/**");
	}*/
	
}
