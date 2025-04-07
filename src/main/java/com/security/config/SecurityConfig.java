package com.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;
    
 
  
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    	
    	httpSecurity.csrf(customizer->customizer.disable())
		.authorizeHttpRequests(	req->req
		.requestMatchers("/register","/login").permitAll()
		.anyRequest().authenticated()
					)
		.httpBasic(Customizer.withDefaults());
		//.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    	
    	
    	return httpSecurity.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        System.out.println("SecurityConfig.authenticationProvider()");
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
    
    // step-2
 	@Bean
 	public AuthenticationManager authenticationManager(AuthenticationConfiguration autheConfig) throws Exception
 	{
 		return autheConfig.getAuthenticationManager();
 	}
}
