package com.quimbaya.clientes_api.security.infraestructure.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.quimbaya.clientes_api.security.aplication.service.JwtService;
import com.quimbaya.clientes_api.security.domain.filter.JwtTokenFilter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private JwtService jwtService;	
	
    public SecurityConfig(JwtService jwtService) {
		this.jwtService = jwtService;
	}
    
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	http
    	.cors()
    	.and()
    	.csrf()
    	.disable()
        .authorizeHttpRequests((authz) -> authz
            .requestMatchers(
            		"/public/**",
            		"/swagger-ui/**",
            		"/v3/api-docs/**",
            		"/swagger-ui.html",
            		"/swagger-ui/index.html",
            		"/webjars/**").permitAll()
            .anyRequest().authenticated()
        )  .addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    	return http.build();
    }
	
	@Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter(jwtService);
    }
	 
	 @Bean
	 public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
	        return configuration.getAuthenticationManager();
	 }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}