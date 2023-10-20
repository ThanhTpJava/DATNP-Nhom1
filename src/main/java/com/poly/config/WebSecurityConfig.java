package com.poly.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.poly.dao.AccountDAO;
import com.poly.service.UserService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor

public class WebSecurityConfig {
	
	//	private final AccountService accService; //
	
	private final AccountDAO accountDAO;

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserService(accountDAO);
	}

	@Bean
	public PasswordEncoder passEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		String staticResources = "/static/**";

		http.authorizeHttpRequests((authorize) -> 
			authorize
//			.requestMatchers("/product/**").authenticated()
//			.requestMatchers("/order/**").authenticated()
//			.requestMatchers("/qcshop/signup/newaccount").permitAll()
//			.requestMatchers(staticResources).permitAll()
			.anyRequest().permitAll())
			.csrf(csrf -> csrf.disable())
			.formLogin((login) -> 
			login.loginPage("/auth/login/form")
			.loginProcessingUrl("/security/login")
			.defaultSuccessUrl("/auth/login/success", false)
			.failureUrl("/auth/login/error"))
			.logout((logout) ->
			logout.logoutUrl("/auth/logoff/success")
			.logoutSuccessUrl("/auth/login/form")
			.permitAll());

//		http.formLogin((log) -> 
//		log.loginPage("/qcshop/login")
//		.loginProcessingUrl("/security/login")
//		.defaultSuccessUrl("/qcshop/login/success", false)
//		.failureUrl("/qcshop/login/error")
//		);
		return http.build();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auProvider = new DaoAuthenticationProvider();
		auProvider.setUserDetailsService(userDetailsService());
		auProvider.setPasswordEncoder(passEncoder());
		return auProvider;
	}

}