package com.example.usersapi.security;

import com.example.usersapi.service.UsersService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
public class WebSecurity {
	private final Environment environment;

	private final UsersService usersService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;


	public WebSecurity(Environment environment, UsersService usersService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.environment = environment;
		this.usersService = usersService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Bean
	protected SecurityFilterChain configure(HttpSecurity http) throws Exception {

		// Configure AuthenticationManagerBuilder
		AuthenticationManagerBuilder authenticationManagerBuilder =
				http.getSharedObject(AuthenticationManagerBuilder.class);

		authenticationManagerBuilder.userDetailsService(usersService)
				.passwordEncoder(bCryptPasswordEncoder);

		AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

		// Create AuthenticationFilter
		AuthenticationFilter authenticationFilter =
				new AuthenticationFilter(authenticationManager, usersService, environment);
		authenticationFilter.setFilterProcessesUrl(environment.getProperty("login.url.path"));

		http
				.authorizeHttpRequests((authorizeHttpRequests) ->
						authorizeHttpRequests
								/*.requestMatchers(HttpMethod.POST, "/users/").permitAll()*/
								/*.access(new WebExpressionAuthorizationManager("hasIpAddress('" + environment.getProperty("gateway.ip") + "')"))*/
								.requestMatchers(toH2Console()).permitAll()
								.requestMatchers(HttpMethod.GET, "/actuator/health").permitAll()
								.requestMatchers(HttpMethod.GET, "/actuator/circuitbreakerevents").permitAll()
								.requestMatchers("/**").permitAll())
				.addFilter(authenticationFilter)
				.authenticationManager(authenticationManager)
				.sessionManagement((sessionManagement) ->
						sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.csrf(csrf -> {
					csrf.ignoringRequestMatchers(toH2Console());
					csrf.disable();
				})
				.headers((headers) ->
						headers.frameOptions(frameOptionsConfig -> {
						}).disable());

		return http.build();
	}
}
