package shop.jtoon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import lombok.RequiredArgsConstructor;
import shop.jtoon.security.filter.AuthenticationFilter;
import shop.jtoon.security.handler.OAuth2FailureHandler;
import shop.jtoon.security.handler.OAuth2SuccessHandler;
import shop.jtoon.security.service.AuthenticationService;
import shop.jtoon.security.service.AuthorizationService;
import shop.jtoon.security.service.CustomOAuth2UserService;
import shop.jtoon.security.service.JwtService;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

	private final AuthorizationService authorizationService;
	private final HandlerExceptionResolver handlerExceptionResolver;
	private final CustomOAuth2UserService customOAuth2UserService;
	private final OAuth2SuccessHandler oAuth2SuccessHandler;
	private final OAuth2FailureHandler oAuth2FailureHandler;
	private final AuthenticationService authenticationService;
	private final JwtService jwtService;

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web.ignoring()
			.requestMatchers("/members/sign-up")
			.requestMatchers("/members/email-authorization")
			.requestMatchers("/members/local-login")
			.requestMatchers("/")
			.requestMatchers("/actuator/prometheus")
			;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(request -> request
				.requestMatchers("/members/**").hasAuthority("USER")
				.anyRequest().permitAll())
			.csrf(AbstractHttpConfigurer::disable)
			.httpBasic(AbstractHttpConfigurer::disable)
			.formLogin(AbstractHttpConfigurer::disable)
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.addFilterBefore(
				new AuthenticationFilter(
					handlerExceptionResolver,
					authorizationService,
					authenticationService,
					jwtService),
				UsernamePasswordAuthenticationFilter.class)
			.exceptionHandling(exceptionHandling -> exceptionHandling
				.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
			.oauth2Login(login -> login
				.userInfoEndpoint(endpoint -> endpoint.userService(customOAuth2UserService))
				.successHandler(oAuth2SuccessHandler)
				.failureHandler(oAuth2FailureHandler))
		;
		return http.build();
	}
}
