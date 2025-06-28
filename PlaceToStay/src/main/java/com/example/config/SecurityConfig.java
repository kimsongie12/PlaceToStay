package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.service.CustomOAuth2UserService;

import lombok.RequiredArgsConstructor;
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	

    private final CustomOAuth2UserService customOAuth2UserService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .authorizeHttpRequests(auth -> auth
        	    .requestMatchers("/", "/login", "/loginForm", "/joinForm", "/join", "/css/**", "/js/**").permitAll()
        	    .anyRequest().authenticated() // 나머지는 인증 필요
        	)
            .oauth2Login(oauth2 -> oauth2
                .loginPage("/login")
                .userInfoEndpoint(userInfo -> userInfo
                        .userService(customOAuth2UserService)  // ✅ 이거 반드시!
                    )
                    .defaultSuccessUrl("/home", true)
            )
            .logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/home") // 로그아웃 후 /home 으로 이동
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                );

        return http.build();
    }
}