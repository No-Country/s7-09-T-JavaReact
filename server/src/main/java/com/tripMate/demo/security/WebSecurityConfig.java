package com.tripMate.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {


    @Autowired
    private PasswordEncoder encoder;

    /*
    SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests().anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .build();


    }


    @Bean
    public UserDetailsService userDetailsService(){
        var user =  User.withUsername("usuario")
                .password("1234")
                .roles("admin")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public AuthenticationManager manager(AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();
    }*/


}
