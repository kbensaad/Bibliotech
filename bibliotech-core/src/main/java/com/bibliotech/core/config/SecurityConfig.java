package com.bibliotech.core.config;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity
			.csrf((csrf) -> csrf.disable()).authorizeHttpRequests((authorizeHttpRequests) ->
				authorizeHttpRequests
					.anyRequest().authenticated()
			).oauth2ResourceServer(oauth2ResourceServer ->
				oauth2ResourceServer
					.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwt->{
						Map<String, Collection<String>> realmAccess = jwt.getClaim("realm_access");
						Collection<String> roles = (Collection<String>) realmAccess.get("roles");
						var grantedAuthorities = roles.stream()
								.map(role -> new SimpleGrantedAuthority("ROLE_" + role))
								.collect(Collectors.toList());
						return new JwtAuthenticationToken(jwt, grantedAuthorities);

					}))
			).sessionManagement((sessionManagement) ->
				sessionManagement
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			);
		return httpSecurity.build();

	}
}
