package com.spring.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {


//    @Bean
//    public UserDetailsManager userDetailsManager(DataSource dataSource) {
//        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
//        return userDetailsManager;
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET,"/employees/getEmployee").hasRole( "MANAGER")
                .requestMatchers(HttpMethod.GET,"/email/getEmail").hasAnyRole("MANAGER","USER");

        http.httpBasic(Customizer.withDefaults());
        return http.build();

    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails userDetails1 = User.withUsername("omar").password("{bcrypt}$2a$12$8WEv910APqOnBfwtc2y95eaZiR7AhgHtr8l5nZwua3cZUNwj2RUP6").roles( "MANAGER").build();  // password : omar0111
        UserDetails userDetails2 = User.withUsername("ahmed").password("{bcrypt}$2a$12$6lMAVVjCOwh1g1kDnneqa.QN7/P09wplCRLS3j7OR5b/Rtenufy5e").roles("ADMIN").build(); // password : ahmed0111
        UserDetails userDetails3 = User.withUsername("ali").password("{noop}ali0111").roles("USER","ADMIN").build();
        UserDetails userDetails4 = User.withUsername("sami").password("{noop}sami0111").roles("USER").build();

      return new InMemoryUserDetailsManager(userDetails1, userDetails2, userDetails3, userDetails4);
    }
}
