package dev.akinwumi.jpasecurity.config;

import dev.akinwumi.jpasecurity.service.JpaUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JpaUserDetailsService  jpaUserDetailsService;

    public SecurityConfig(JpaUserDetailsService jpaUserDetailsService) {
        this.jpaUserDetailsService = jpaUserDetailsService;
    }



    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(csrf -> csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/api/posts")).permitAll()
                        .anyRequest().authenticated())

                .userDetailsService(jpaUserDetailsService)
                .headers(headers -> {
                    headers.frameOptions(frameOptions -> frameOptions.disable());
                })

                .httpBasic(Customizer.withDefaults())
                .build();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
}
