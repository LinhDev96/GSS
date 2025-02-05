package com.gss.config;

import com.okta.spring.boot.oauth.Okta;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfiguration {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(auth -> auth.requestMatchers("/order/**").authenticated()
				.requestMatchers("/product/**").permitAll()
				.requestMatchers("/checkout/**").permitAll()
				.anyRequest().permitAll())
				.oauth2ResourceServer(oauth2 -> oauth2.jwt());
		http.cors();
		
		http.csrf(csrf -> csrf
		            .ignoringRequestMatchers("/checkout/**")); // Bỏ qua CSRF cho /checkout/**

		http.setSharedObject(ContentNegotiationStrategy.class, new HeaderContentNegotiationStrategy());

		Okta.configureResourceServer401ResponseBody(http);

		return http.build();
	}
	
//	@Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        // Chỉ định các nguồn gốc (origins) được phép
//        configuration.addAllowedOrigin("https://localhost:8081"); // Angular app
//        configuration.setAllowCredentials(true); // Hỗ trợ cookie hoặc xác thực
//        configuration.addAllowedMethod("*"); // Cho phép tất cả các phương thức HTTP
//        configuration.addAllowedHeader("*"); // Cho phép tất cả các headers
//        configuration.addExposedHeader("Authorization"); // Expose Authorization header nếu cần
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration); // Áp dụng cho tất cả endpoints
//        return source;
//    }
}
