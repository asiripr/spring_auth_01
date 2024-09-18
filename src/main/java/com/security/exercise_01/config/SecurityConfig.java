package com.security.exercise_01.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(customizer -> customizer.disable())
				.authorizeHttpRequests(request -> request
						.requestMatchers("register", "login", "login1")
						.permitAll() 
						.anyRequest().authenticated())
				.formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.build();
	}
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
		
		return config.getAuthenticationManager();
		
	}
//	@Bean
//	public UserDetailsService userDetailsService() {
//		
//		UserDetails user1 = User
//				.withDefaultPasswordEncoder()
//				.username("anura")
//				.password("1234")
//				.roles("USER")
//				.build();
//		
//		UserDetails user2 = User
//				.withDefaultPasswordEncoder()
//				.username("sajith")
//				.password("0000")
//				.roles("ADMIN")
//				.build();
//		
//		return new InMemoryUserDetailsManager(user1, user2);
//	}
}
