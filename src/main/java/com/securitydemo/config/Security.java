package com.securitydemo.config;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.securitydemo.service.UserService;


@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter{

	@Bean
	public UserService userservice(){
		return new UserService();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		// TODO Auto-generated method stub
		return new BCryptPasswordEncoder();
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		UserDetailsService uds = userservice();
		
         auth.userDetailsService(uds).passwordEncoder(passwordEncoder());
		
}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.httpBasic()
			.and()
			.authorizeRequests()
			.antMatchers("/loginuser").hasAnyAuthority("user")
			.antMatchers("/loginadmin").hasAnyAuthority("admin")
			.antMatchers("/**","/users").permitAll()
			.anyRequest().authenticated();
					
	
	}
	


	
	
	

}
