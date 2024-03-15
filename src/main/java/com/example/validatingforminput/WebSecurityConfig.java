package com.example.validatingforminput;

import com.example.validatingforminput.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/","/index","/films/all","/users/**","/register").permitAll()
                        .requestMatchers("/films/add","/user-profile").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                        .requestMatchers("/actors/**", "/nationality/**").hasAnyAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/index")
                        .usernameParameter("userNameOrEmail")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }
}
