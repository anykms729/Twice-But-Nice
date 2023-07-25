//package org.platformlib.platformbackend.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.*;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.headers()
//                .contentSecurityPolicy("default-src 'self' data:; img-src 'self' data:;")
//                .formLogin() // Enable form-based authentication
//                .loginPage("/login") // Specify the custom login page URL (you need to create the view for this page)
//                .defaultSuccessUrl("/") // Redirect to the dashboard page after successful login
//                .and()
//                .logout() // Enable logout functionality
//                .logoutUrl("/logout") // Specify the custom logout URL
//                .logoutSuccessUrl("/") // Redirect to login page with a logout parameter after successful logout
//                .csrf().disable();
//    }
//}
