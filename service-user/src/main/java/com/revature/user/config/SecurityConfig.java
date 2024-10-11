package com.revature.user.config;

import com.revature.user.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests((authz) -> authz
               
                .anyRequest().permitAll()
            )
           // .httpBasic();  // You can also comment out this line if you want to completely disable httpBasic authentication
        .logout()
        .logoutUrl("/logout")  // Custom logout URL
        .logoutSuccessUrl("http://localhost:8080/")  // Redirect here after logout
        .invalidateHttpSession(true)  // Invalidate the session on logout
        .deleteCookies("JSESSIONID")  // Delete session cookies
        .permitAll();
        return http.build();
    }
}
















