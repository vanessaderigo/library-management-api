package com.vanessa.librarymanagementapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .formLogin(configurer -> configurer.loginPage("/login").permitAll())
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/login/**").permitAll();
                    authorize.requestMatchers(HttpMethod.POST,"/users/**").permitAll();
                    authorize.requestMatchers(HttpMethod.DELETE,"/users/**").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.PUT,"/users/**").hasAnyRole("ADMIN", "USER");
                    authorize.anyRequest().authenticated();
                })
                .build();
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder){
        UserDetails user = User.builder()
                .username("user")
                .password(encoder().encode("123"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(encoder().encode("123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}