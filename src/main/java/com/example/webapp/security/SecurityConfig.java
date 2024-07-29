package com.example.webapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.formLogin(login -> login //  フォーム認証を使う
				.permitAll()
				.defaultSuccessUrl("/", true)
				)
				.authorizeHttpRequests(authz -> authz
						.requestMatchers("/css/**").permitAll() // CSSファイルは認証不要で使えるようにする
						.requestMatchers("/").permitAll() //  トップページは認証不要
						.requestMatchers("/regist").permitAll()
						.requestMatchers("/user-edit").permitAll()
						.requestMatchers("/user-delete/**").hasRole("king")
						.requestMatchers("/100-chan/**").permitAll()
						.requestMatchers("/websocket/**").permitAll()
						.anyRequest().authenticated() //  他のURLはログイン後アクセス可能
				);
		
		return http.build();
	}
}
