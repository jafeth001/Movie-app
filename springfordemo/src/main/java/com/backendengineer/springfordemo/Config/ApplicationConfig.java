package com.backendengineer.springfordemo.Config;

import com.backendengineer.springfordemo.Entity.User;
import com.backendengineer.springfordemo.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Configuration
public class ApplicationConfig {
    private final UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username);
    }

        @Bean
        public AuthenticationProvider authenticationProvider () {
            DaoAuthenticationProvider dauauthprd = new DaoAuthenticationProvider();
            dauauthprd.setPasswordEncoder(passwordEncoder());
            dauauthprd.setUserDetailsService(userDetailsService());
            return dauauthprd;
        }

        @Bean
        public AuthenticationManager authenticationManager (AuthenticationConfiguration configuration) throws Exception
        {
            return configuration.getAuthenticationManager();
        }

        @Bean
        public PasswordEncoder passwordEncoder () {
            return new BCryptPasswordEncoder();
        }
    }
