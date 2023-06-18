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
//                .and()
//                // Other security configurations...
//                .csrf().disable();
//    }
//}
